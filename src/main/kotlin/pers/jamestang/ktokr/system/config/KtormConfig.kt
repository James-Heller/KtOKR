package pers.jamestang.ktokr.system.config

import com.zaxxer.hikari.HikariDataSource
import org.ktorm.database.Database
import org.ktorm.support.mysql.MySqlDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KtormConfig(
    private val hikariDataSource: HikariDataSource
) {

    @Bean
    fun database(): Database {

        val database = Database.connectWithSpringSupport(hikariDataSource, dialect = MySqlDialect())
        return database
    }

}