namespace KotlinBasics_API;

public static class SolarForecastAPI_4_10
{
    // === Фейкові дані ===
    private static List<Station> stations = new()
    {
        new Station(1, "SolarOne", "Kyiv", 50.0),
        new Station(2, "SunPower", "Lviv", 75.0)
    };

    private static List<Forecast> forecasts = new()
    {
        new Forecast(1, 1, DateTime.Today, 40.5, 22.3, 20),
        new Forecast(2, 1, DateTime.Today.AddDays(1), 45.2, 24.0, 15),
        new Forecast(3, 2, DateTime.Today, 60.1, 21.5, 30)
    };

    public static void Initialize(WebApplication app)
    {

// GET /stations — список станцій
        app.MapGet("/stations", () => Results.Ok(stations));

// GET /stations/{id} — одна станція
        app.MapGet("/stations/{id}", (int id) =>
        {
            var station = stations.FirstOrDefault(s => s.Id == id);
            return station is not null ? Results.Ok(station) : Results.NotFound();
        });

// POST /stations — додати нову станцію
        app.MapPost("/stations", (Station station) =>
        {
            stations.Add(station);
            return Results.Created($"/stations/{station.Id}", station);
        });

// === Ендпоінти для прогнозів ===

// GET /stations/{id}/forecasts — всі прогнози по станції
        app.MapGet("/stations/{id}/forecasts", (int id) =>
        {
            var result = forecasts.Where(f => f.StationId == id).ToList();
            return result.Any()
                ? Results.Ok(result)
                : Results.NotFound(new { message = "No forecasts for this station" });
        });

// POST /stations/{id}/forecasts — додати прогноз
        app.MapPost("/stations/{id}/forecasts", (int id, Forecast forecast) =>
        {
            if (!stations.Any(s => s.Id == id))
                return Results.NotFound(new { message = "Station not found" });

            forecasts.Add(forecast with { StationId = id });
            return Results.Created($"/stations/{id}/forecasts/{forecast.Id}", forecast);
        });

// GET /stations/{id}/forecasts/stats — агреговані показники
        app.MapGet("/stations/{id}/forecasts/stats", (int id) =>
        {
            var stationForecasts = forecasts.Where(f => f.StationId == id).ToList();
            if (!stationForecasts.Any())
                return Results.NotFound(new { message = "No forecasts to calculate stats" });

            var avgPower = stationForecasts.Average(f => f.PredictedPowerKw);
            var avgTemp = stationForecasts.Average(f => f.TemperatureC);
            var avgCloud = stationForecasts.Average(f => f.CloudCoveragePercent);

            return Results.Ok(new
            {
                StationId = id,
                AveragePowerKw = Math.Round(avgPower, 2),
                AverageTemperatureC = Math.Round(avgTemp, 2),
                AverageCloudCoverage = Math.Round(avgCloud, 2),
                Efficiency = Math.Round((avgPower / stations.First(s => s.Id == id).CapacityKw) * 100, 2)
            });
        });

// GET /stations/{id}/forecasts/generate/{days} — згенерувати випадкові прогнози
        app.MapGet("/stations/{id}/forecasts/generate/{days}", (int id, int days) =>
        {
            if (!stations.Any(s => s.Id == id))
                return Results.NotFound(new { message = "Station not found" });

            var rand = new Random();
            var newForecasts = Enumerable.Range(1, days).Select(i =>
                new Forecast(
                    forecasts.Count + i,
                    id,
                    DateTime.Today.AddDays(i),
                    Math.Round(rand.NextDouble() * stations.First(s => s.Id == id).CapacityKw, 2),
                    Math.Round(rand.Next(10, 35) + rand.NextDouble(), 1),
                    rand.Next(0, 100)
                )
            ).ToList();

            forecasts.AddRange(newForecasts);
            return Results.Ok(newForecasts);
        });

// Health-check
        app.MapGet("/", () => "☀️ Solar Power Station API is running 🚀");
    }
}

// === DTO ===
record Station(int Id, string Name, string Location, double CapacityKw);

record Forecast(
    int Id,
    int StationId,
    DateTime Date,
    double PredictedPowerKw,
    double TemperatureC,
    double CloudCoveragePercent);