package com.example.memefon.util

import android.content.Context
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open("json/$fileName").bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

/*val jsonFileString = getJsonDataFromAsset(applicationContext, "memefon.json")
Log.i("data", jsonFileString)

val gson = Gson()
val listPersonType = object : TypeToken<List<Person>>() {}.type

var persons: List<Person> = gson.fromJson(jsonFileString, listPersonType)
persons.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }*/