using System.Text.Json;

namespace KotlinBasics_API;

public static class SolarForecastAPI_4_11
{
    // Мокована база користувачів (початкові дані)
    private static List<User> users = new List<User>
    {
        new User { Username = "admin", Password = "admin123", Role = "ADMIN" },
        new User { Username = "user", Password = "user123", Role = "USER" }
    };

// Моковані дані сонячних електростанцій
    private static List<SolarStation> stations = new()
    {
        new(1, "Solar Plant 1", 5),
        new(2, "Solar Plant 2", 3)
    };

    public static void Initialize(WebApplication app)
    {
        // Легка реєстрація користувача
        app.MapPost("/register", async (HttpContext context) =>
        {
            var request = await JsonSerializer.DeserializeAsync<LoginRequest>(context.Request.Body);
            if (request == null || string.IsNullOrEmpty(request.Username) || string.IsNullOrEmpty(request.Password))
            {
                context.Response.StatusCode = 400;
                await context.Response.WriteAsync("Invalid registration data");
                return;
            }

            if (users.Any(u => u.Username == request.Username))
            {
                context.Response.StatusCode = 400;
                await context.Response.WriteAsync("Username already exists");
                return;
            }

            // Додаємо користувача з роллю USER за замовчуванням
            users.Add(new User { Username = request.Username, Password = request.Password, Role = "USER" });
            context.Response.StatusCode = 201;
            await context.Response.WriteAsync("User registered successfully");
        });

// Логін
        app.MapPost("/login", async (HttpContext context) =>
        {
            var request = await JsonSerializer.DeserializeAsync<LoginRequest>(context.Request.Body);
            if (request == null || string.IsNullOrEmpty(request.Username) || string.IsNullOrEmpty(request.Password))
            {
                context.Response.StatusCode = 400;
                await context.Response.WriteAsync("Invalid request");
                return;
            }

            var user = users.FirstOrDefault(u => u.Username == request.Username && u.Password == request.Password);
            if (user == null)
            {
                context.Response.StatusCode = 401;
                await context.Response.WriteAsync("Invalid credentials");
                return;
            }

            // Генеруємо моканий JWT-токен (спрощено)
            var token = Convert.ToBase64String(
                System.Text.Encoding.UTF8.GetBytes($"{user.Username}:{user.Role}"));

            var response = new { token, role = user.Role };
            context.Response.ContentType = "application/json";
            await context.Response.WriteAsync(JsonSerializer.Serialize(response));
        });

// Захищений ендпоінт для всіх авторизованих
        app.MapGet("/protected", async (HttpContext context) =>
        {
            var user = ValidateToken(context);
            if (user == null) return;
            var response = new { message = $"Hello {user.Username}, your role is {user.Role}" };
            context.Response.ContentType = "application/json";
            await context.Response.WriteAsJsonAsync(response);
        });

// Адмінський ендпоінт
        app.MapGet("/admin-only", async (HttpContext context) =>
        {
            var user = ValidateToken(context);
            if (user == null) return;

            if (user.Role != "ADMIN")
            {
                context.Response.StatusCode = 403;
                await context.Response.WriteAsync("Forbidden: Admins only");
                return;
            }

            await context.Response.WriteAsync(JsonSerializer.Serialize(stations));
        });

    }

    static User? ValidateToken(HttpContext context)
    {
        if (!context.Request.Headers.ContainsKey("Authorization"))
        {
            context.Response.StatusCode = 401;
            context.Response.WriteAsync("Missing token").Wait();
            return null;
        }

        var authHeader = context.Request.Headers["Authorization"].ToString();
        if (!authHeader.StartsWith("Bearer "))
        {
            context.Response.StatusCode = 401;
            context.Response.WriteAsync("Invalid token format").Wait();
            return null;
        }

        var token = authHeader.Substring("Bearer ".Length).Trim();

        try
        {
            var decoded = System.Text.Encoding.UTF8.GetString(Convert.FromBase64String(token));
            var parts = decoded.Split(':');
            var username = parts[0];
            var role = parts[1];

            return new User { Username = username, Role = role };
        }
        catch
        {
            context.Response.StatusCode = 401;
            context.Response.WriteAsync("Invalid token").Wait();
            return null;
        }
    }
}

class LoginRequest(string Username, string Password)
{
    public string Username { get; init; } = Username;
    public string Password { get; init; } = Password;

    public void Deconstruct(out string Username, out string Password)
    {
        Username = this.Username;
        Password = this.Password;
    }
}

class SolarStation(int Id, string Name, double CapacityMW)
{
    public int Id { get; init; } = Id;
    public string Name { get; init; } = Name;
    public double CapacityMW { get; init; } = CapacityMW;

    public void Deconstruct(out int Id, out string Name, out double CapacityMW)
    {
        Id = this.Id;
        Name = this.Name;
        CapacityMW = this.CapacityMW;
    }
}

class User
{
    public string Username { get; set; }
    public string Password { get; set; }
    public string Role { get; set; }
}


// Функція для валідації токена