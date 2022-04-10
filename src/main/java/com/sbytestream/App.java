package com.sbytestream;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Console;
import java.io.File;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("routes.xml");
        SpringCamelContext camelContext = (SpringCamelContext) appContext
                .getBean("camel");

        System.out.println(true);
        System.out.println(String.format("CWD: %s", System.getProperty("user.dir")));

        try {
            camelContext.start();
            System.out.println("Press <ENTER> to end the application.");
            Scanner sc = new Scanner(System.in);
            sc.next();
        }
        finally {
            camelContext.stop();
        }
    }
}
