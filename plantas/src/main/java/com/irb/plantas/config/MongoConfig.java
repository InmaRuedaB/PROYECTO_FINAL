package com.irb.plantas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * La anotación "@Configuration" sirve para decirle a Spring que es una clase
 * necesaria para la configuración del proyecto y que debe ejecutarla antes
 */
@Configuration
//le decimos a mongo en que paquetes están mis respositorios de conexión con la BD.
@EnableMongoRepositories(basePackages = "com.irb.plantas.repository")
@EnableMongoAuditing
public class MongoConfig {
}
