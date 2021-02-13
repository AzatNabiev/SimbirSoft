package ru.itis.javalab.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.itis.javalab.repositories.WordRepository;
import ru.itis.javalab.repositories.WordRepositoryJdbcImpl;
import ru.itis.javalab.services.TextService;
import ru.itis.javalab.services.TextServiceImpl;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public HikariConfig hikariConfig(){
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.jdbc.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.jdbc.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.jdbc.username"));
        hikariConfig.setPassword(environment.getProperty("db.jdbc.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.jdbc.driver.classname"));
        return hikariConfig;
    }
    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }

}
