package com.example.memefon.data.network

import android.content.Context
import android.util.Log
import com.example.memefon.data.model.Memefon
import com.example.memefon.util.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Entry point for Marvel API
 */
interface MemefonAPI {

    suspend fun getMemefonData(context: Context): Memefon {
        val jsonFileString = getJsonDataFromAsset(context, "memefon.json")

        Log.i("data", "MEMEFON JSON DATA: "+jsonFileString.orEmpty())

        val gson = Gson()
        val listPersonType = object : TypeToken<Memefon>() {}.type

        return gson.fromJson(jsonFileString, listPersonType)
    }
}