package com.evandhardspace.yacca.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Users : Table("users") {
    val id = uuid("id")
    val email = varchar("username", 255).uniqueIndex()
    val hashedPassword = varchar("hashed_password", 255)  // Store the hashed password
    val salt = text("salt")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)
}

object FavoriteCurrencies : Table("favorite_currencies") {
    val id = integer("id").autoIncrement()
    val userId = uuid("user_id").references(Users.id)
    val currencyId = varchar("currency_id", 50)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey = PrimaryKey(id)
}

object RefreshTokens : Table("refresh_tokens") {
    val id = uuid("id").autoGenerate()
    val userId = uuid("user_id").references(Users.id)
    val token = varchar("token", 512)
    val expiresAt = datetime("expires_at")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
