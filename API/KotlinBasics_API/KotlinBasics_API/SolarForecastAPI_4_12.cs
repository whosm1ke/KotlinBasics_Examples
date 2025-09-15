namespace KotlinBasics_API;

public static class SolarForecastAPI_4_12
{
    public static void Initialize(WebApplication app)
    {
// Ендпоінт: прогноз на сьогодні
        app.MapGet("/forecast/today", () =>
        {
            var forecast = new SolarPanelForecast(DateTime.Now.ToString("yyyy-MM-dd"), 1250, "Sunny");
            return Results.Ok(forecast);
        });

// Ендпоінт: прогноз на завтра
        app.MapGet("/forecast/tomorrow", () =>
        {
            var forecast = new SolarPanelForecast(DateTime.Now.AddDays(1).ToString("yyyy-MM-dd"), 1100, "Partly Cloudy");
            return Results.Ok(forecast);
        });

// Ендпоінт: штучні помилки для тестування
        app.MapGet("/forecast/error/{code:int}", (int code) =>
        {
            return code switch
            {
                400 => Results.BadRequest(new Message("Некоректний запит")),
                404 => Results.NotFound(new Message("Прогноз не знайдено")),
                500 => Results.InternalServerError(new Message("Внутрішня помилка сервера")),
                _ => Results.BadRequest(new Message($"Помилка {code}"))
            };
        });
    }
}

// === DTO ===
record SolarPanelForecast(string Date, int PowerKwh, string Status);
record Message(string Msg);