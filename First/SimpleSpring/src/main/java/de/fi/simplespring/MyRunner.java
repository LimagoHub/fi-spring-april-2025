package de.fi.simplespring;

import de.fi.simplespring.demo.Demo;
import de.fi.simplespring.math.Calculator;
import de.fi.simplespring.pojo.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {


    @Qualifier("logger")
    private final Calculator calculator;



    // Calculator injecten

    @Override
    public void run(final String... args) throws Exception {



        System.out.println("Hello World");
        System.out.println(calculator.add(1, 2));
    }
}
