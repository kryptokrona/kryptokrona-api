package org.kryptokrona.api.plugins

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.ktorm.database.Database

object DatabaseConnection {

    private val config = HoconApplicationConfig(ConfigFactory.load())

    val database = Database.connect(
        url = config.property("postgres.url").getString(),
        driver = "org.postgresql.Driver",
        user = config.property("postgres.user").getString(),
        password = config.property("postgres.password").getString()
    )
}