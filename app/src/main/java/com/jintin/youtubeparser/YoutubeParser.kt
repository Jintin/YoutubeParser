package com.jintin.youtubeparser

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YoutubeParser @Inject constructor() {
    companion object {
        const val URL = "https://www.youtube.com/results?search_query="
        const val START_TAG = "{\"itemSectionRenderer\":{\"contents\":"
        const val START_OFFSET = START_TAG.length
        const val END_TAG = "\"maxOneLine\":false}]}}],\"trackingParams\""
        const val END_OFFSET = "\"maxOneLine\":false}]}}]".length
    }

    suspend fun load(query: String): List<String> = withContext(Dispatchers.IO) {
        val list = mutableListOf<String>()
        try {
            val doc = Jsoup.connect(URL + query).get()

            val source = doc.body().data()
            val jsonString = source.substring(
                source.indexOf(START_TAG) + START_OFFSET,
                source.indexOf(END_TAG) + END_OFFSET
            )
            val array = JSONArray(jsonString)

            for (i in (0 until array.length())) {
                array.optJSONObject(i)
                    ?.optJSONObject("videoRenderer")
                    ?.optString("videoId")
                    ?.let(list::add)
            }
        } catch (ignore: Exception) {
        }
        list.toList()
    }
}