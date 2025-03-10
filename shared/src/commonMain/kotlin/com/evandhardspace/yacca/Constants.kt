package com.evandhardspace.yacca

expect val localHost: String

object Endpoints {
    const val SIGNUP = "signup"
    const val SIGNIN = "signin"
    const val REFRESH = "refresh"

    const val CURRENCIES_PUBLIC_INFO = "currencies/public-info"
    const val CURRENCIES_USER_INFO = "currencies/user-info"

    const val FAVOURITES = "favourites"
    const val USER = "user"
}

object Config {
    val isLocalImplementation: Boolean = BuildKonfig.isLocalImplementation
}
