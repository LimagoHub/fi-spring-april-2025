package de.fi.simplespring.demo;

import de.fi.simplespring.translator.Translator;
import de.fi.simplespring.translator.internal.ToLowerTranslator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy
@Scope("prototype")
public class Demo {


    private final Translator translator;



    public Demo(final  Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("Ctor Demo"));

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(translator.translate("PostConstruct Demo"));
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy Demo");
    }

    public void foo() {
        System.out.println("Foo");
    }
}
