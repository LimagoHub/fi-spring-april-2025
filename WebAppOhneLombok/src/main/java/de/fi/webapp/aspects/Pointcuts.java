package de.fi.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {


    @Pointcut(value = "execution(public * de.fi.webapp.presention.controller.v1.PersonenController.*(..))")
    public void personenControllerMethods(){}
}
