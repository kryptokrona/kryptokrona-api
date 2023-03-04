package org.kryptokrona.api.plugins

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.ktorm.database.Database

object DatabaseConnection {

    private val config = HoconApplicationConfig(ConfigFactory.load())

    val database = Database.connect(
        url = config.property("db.url").getString(),
        driver = config.property("db.driver").getString(),
        user = config.property("db.user").getString(),
        password = config.property("db.password").getString()
    )
}