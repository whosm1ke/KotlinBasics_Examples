package ua.kpi.practical_example_27.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_27.models.SolarForecast

@Composable
fun ForecastItem(forecast: SolarForecast) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .animateContentSize(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(forecast.time)
            Text("${forecast.powerKW} kW")
        }
    }
}