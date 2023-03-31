package org.example;

import org.example.interfaces.MathAssistant;
import org.example.util.springConfig.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getBean(MathAssistant.class).start();
    }
}
