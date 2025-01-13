package com.evandhardspace.yacca.domain.repositories

import com.evandhardspace.yacca.data.datasources.AuthDataSource
import com.evandhardspace.yacca.data.datasources.TokenDataSource

internal class AuthRepository(
    private val authDataSource: AuthDataSource,
    private val tokenDataSource: TokenDataSource,
): Cleanable {
    suspend fun signUp(email: String, password: String): Result<Unit> = runCatching {
        authDataSource.signUp(email, password)
        authDataSource.signIn(email, password)
    }

    suspend fun signIn(email: String, password: String): Result<Unit> = runCatching {
       val accessTokenResult =  authDataSource.signIn(email, password)
        tokenDataSource.saveAccessToken(accessTokenResult.token)
    }

    override suspend fun clear() {
        tokenDataSource.clearTokens()
    }
}