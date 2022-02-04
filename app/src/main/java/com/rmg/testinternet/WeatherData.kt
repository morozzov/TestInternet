package com.rmg.testinternet

import com.google.gson.JsonParser
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class WeatherData{
     var date: LocalDate = LocalDate.now()
     var temperature: Int = 0
     var condition: String = ""

    private var conditions: Map<String, String> = mapOf(
        "clear" to "ясно",
        "partly-cloudy" to "малооблачно",
        "cloudy" to "облачно с прояснениями",
        "overcast" to "пасмурно",
        "drizzle" to "морось",
        "light-rain" to "небольшой дождь",
        "rain" to "дождь",
        "moderate-rain" to "умеренно сильный дождь",
        "heavy-rain" to "сильный дождь",
        "continuous-heavy-rain" to "длительный сильный дождь",
        "showers" to "ливень",
        "wet-snow" to "дождь со снегом",
        "light-snow" to "небольшой снег",
        "snow" to "снег",
        "snow-showers" to "снегопад",
        "hail" to "град",
        "thunderstorm" to "гроза",
        "thunderstorm-with-rain" to "дождь с грозой",
        "thunderstorm-with-hail" to "гроза с градом",
    )

    fun parseJson(inputJson: String) {
        var jsonObject = JsonParser.parseString(inputJson).asJsonObject

        var dateInString = jsonObject.get("now_dt").asString
        dateInString = dateInString.substring(0, dateInString.indexOf("T"))

        date = LocalDate.parse(dateInString, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        temperature = jsonObject.getAsJsonObject("fact").get("temp").asInt

        var encodeCondition = jsonObject.getAsJsonObject("fact").get("condition").asString

        condition = conditions[encodeCondition].toString()
    }
}