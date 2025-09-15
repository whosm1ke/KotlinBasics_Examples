using KotlinBasics_API;

var builder = WebApplication.CreateBuilder(args);
builder.WebHost.ConfigureKestrel(options =>
{
    options.ListenAnyIP(5156); // порт для HTTP
});
// Add services to the container.
// Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
builder.Services.AddOpenApi();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();
}


SolarForecastAPI_3_9.Initialize(app);
SolarForecastAPI_4_10.Initialize(app);
SolarForecastAPI_4_11.Initialize(app);
SolarForecastAPI_4_12.Initialize(app);


app.Run();

