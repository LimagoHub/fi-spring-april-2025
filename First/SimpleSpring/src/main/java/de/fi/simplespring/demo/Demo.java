package de.fi.simplespring.demo;

import de.fi.simplespring.translator.Translator;
import de.fi.simplespring.translator.internal.ToLowerTranslator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy
@Scope("singleton")
//@RequiredArgsConstructor
public class Demo {


    //@Value("${Demo.message}")
    private final String message;
    private final Translator translator;



    public Demo(final  Translator translator, @Value("${Demo.message}")String  message) {
        this.translator = translator;
        this.message = message;

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(translator.translate("PostConstruct Demo"));
        System.out.println(this.message);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy Demo");
    }

    public void foo() {
        System.out.println("Foo");
    }
}
