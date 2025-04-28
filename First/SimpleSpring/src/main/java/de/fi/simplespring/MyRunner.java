package de.fi.simplespring;

import de.fi.simplespring.demo.Demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class MyRunner implements CommandLineRunner {


    // Calculator injecten

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Hello World");
        // ergebnis einer Kalkulation in console schreiben
    }
}
