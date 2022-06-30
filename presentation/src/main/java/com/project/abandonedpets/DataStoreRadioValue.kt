package com.project.abandonedpets

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.project.abandonedpets.DataStoreRadioValue.PreferenceKeys.stringKeyGender
import com.project.abandonedpets.DataStoreRadioValue.PreferenceKeys.stringKeyNeuter
import com.project.abandonedpets.DataStoreRadioValue.PreferenceKeys.stringKeySpecies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreRadioValue(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")

    private object PreferenceKeys {
        val stringKeyGender = stringPreferencesKey("gender")
        val stringKeyNeuter = stringPreferencesKey("neuter")
        val stringKeySpecies = stringPreferencesKey("species")
    }

    val gender = get("gender")
    val neuter = get("neuter")
    val species = get("species")

    suspend fun save(value: String, key: String) {
        val stringKey = getKey(key)

        context.dataStore.edit { preferences ->
            preferences[stringKey] = value
        }
    }

    fun get(key: String): Flow<String> {
        val stringKey = getKey(key)

        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[stringKey] ?: ""
            }
    }

    private fun getKey(key: String): Preferences.Key<String> {
        return when (key) {
            "gender" -> stringKeyGender
            "neuter" -> stringKeyNeuter
            "species" -> stringKeySpecies
            else -> stringKeyGender
        }
    }

}