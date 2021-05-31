package com.app.marvelapp.utils

import com.app.marvelapp.BuildConfig

object Constants {
    val SERVICE_BASE_URL = BuildConfig.SERVICE_BASE_URL
    const val API_PUBLIC_KEY = "215aef99119fc219a3b984444abd1f2b"
    const val API_PRIVATE_KEY = "efc46eaf682fb11cd5b781df10738a3178acaa54"

    const val PAGE_SIZE = 30
    const val LOAD_SIZE_HINT = 30
    const val FIRST_PAGE = 1

    const val COMICS_LIMIT = 10
    const val COMICS_START_DATE = "2005-01-01"
    const val COMICS_ORDER_BY = "-onsaleDate"

}