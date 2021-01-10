package com.example.himumsaiddadcodingchallenge.data

import android.content.SharedPreferences
import javax.inject.Inject


class PreferenceHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    private val KEY_FAVORITE = "favorites"

    fun storeFavoriteIds(favorites: List<Int>) {

        write(KEY_FAVORITE, favorites.joinToString())
    }

    fun getFavorites(): MutableList<Int> {

        val favorites = read(KEY_FAVORITE, "")
        return if (favorites == "") {
            mutableListOf()
        } else {
            favorites?.split(",")?.toMutableList()?.map { it.trim().toInt() }?.toMutableList()
                ?: mutableListOf()
        }
    }

    fun write(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun read(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }
}