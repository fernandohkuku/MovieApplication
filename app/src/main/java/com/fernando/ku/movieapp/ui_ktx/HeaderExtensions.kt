package com.fernando.ku.movieapp.ui_ktx

import okhttp3.Headers

fun headersFrom(headers: Map<String, String>): Headers = Headers.Builder().apply {
    for ((key, value) in headers) {
        add(key, value)
    }
}.build()
