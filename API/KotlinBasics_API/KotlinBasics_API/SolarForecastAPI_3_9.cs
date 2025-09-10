namespace KotlinBasics_API;

public static class SolarForecastAPI_3_9
{
    private static List<SolarForecast> forecasts = new List<SolarForecast>
    {
        new SolarForecast(1, "День 1", 5.5),
        new SolarForecast(2, "День 2", 7.2),
        new SolarForecast(3, "День 3", 6.8),
    };

    public static void Initialize(WebApplication app)
    {
        app.MapGet("/forecasts", () => forecasts);
        app.MapPost("/forecasts", (SolarForecast forecast) =>
        {
            if (forecasts.Any(f => f.Id == forecast.Id))
                return Results.Conflict($"Forecast with Id {forecast.Id} already exists.");

            forecasts.Add(forecast);
            return Results.Ok(forecast);
        });
        app.MapPut("/forecasts/{id}", (int id, SolarForecast updated) =>
        {
            var index = forecasts.FindIndex(f => f.Id == id);
            if (index == -1) return Results.NotFound();
            forecasts[index] = updated;
            return Results.Ok(updated);
        });
        app.MapDelete("/forecasts/{id}", (int id) =>
        {
            forecasts.RemoveAll(f => f.Id == id);
            return Results.NoContent();
        });
    }
}

record SolarForecast(int Id, string Day, double PredictedPower);