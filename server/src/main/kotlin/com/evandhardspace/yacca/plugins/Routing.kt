package com.evandhardspace.yacca.plugins

import com.evandhardspace.yacca.data.currency.CurrencyDataSource
import com.evandhardspace.yacca.data.user.UserDataSource
import com.evandhardspace.yacca.routes.currencies
import com.evandhardspace.yacca.routes.deleteUser
import com.evandhardspace.yacca.routes.refreshToken
import com.evandhardspace.yacca.routes.signIn
import com.evandhardspace.yacca.routes.signUp
import com.evandhardspace.yacca.security.hashing.HashingService
import com.evandhardspace.yacca.security.token.TokenService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    hashingService: HashingService,
    tokenService: TokenService,
    userDataSource: UserDataSource,
    currencyDataSource: CurrencyDataSource,
) {
    routing {
        get("/") {
            call.respondText("Hello YACCA!")
        }
        signUp(
            hashingService = hashingService,
            userDataSource = userDataSource,
        )
        signIn(
            userDataSource = userDataSource,
            hashingService = hashingService,
            tokenService = tokenService,
        )
        refreshToken(
            tokenService = tokenService,
        )
        deleteUser(
            userDataSource = userDataSource,
        )
        currencies(
            currencyDataSource = currencyDataSource,
        )
    }
}

