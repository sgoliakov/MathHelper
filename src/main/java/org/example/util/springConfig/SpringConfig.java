package org.example.util.springConfig;

import org.example.model.Equation;
import org.example.util.hibernateSolutions.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Scanner;

@Configuration
@ComponentScan("org.example")
public class SpringConfig {
    @Bean
    @Scope("prototype")
    public Equation equation() {
        return new Equation();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtil.getFactory();
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
