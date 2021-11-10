package com.example.vaccnow;

import java.util.Optional;
import java.util.Properties;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import com.example.vaccnow.exceptions.LoggingInterceptor;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class AppConfig {
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.<String>of("LoggedInUser");
    }

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.yahoo.com");
        mailSender.setPort(587);

        mailSender.setUsername("<xxx@yahoo.com>");
        mailSender.setPassword("<password>");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    WebMvcConfigurer adapter(LoggingInterceptor loggingInterceptor) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loggingInterceptor);
            }
        };
    }

    @Bean
    @ConditionalOnProperty("spring.liquibase.enabled")
    public SpringLiquibase liquibase(@NotNull DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDatabaseChangeLogLockTable("db_change_log");
        liquibase.setDatabaseChangeLogTable("db_change");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

}