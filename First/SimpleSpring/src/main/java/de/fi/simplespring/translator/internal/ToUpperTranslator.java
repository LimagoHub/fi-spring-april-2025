package de.fi.simplespring.translator.internal;

import de.fi.simplespring.translator.Translator;
//import jakarta.inject.Named;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Primary
//@Qualifier("upper")
@Profile({"dev","test"})
public class ToUpperTranslator implements Translator {
    @Override
    public String translate(final String text) {
        return text.toUpperCase();
    }
}
