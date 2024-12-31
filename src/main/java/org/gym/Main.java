package org.gym;

import org.gym.storage.TrainerStorage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.gym");


        TrainerStorage ts = context.getBean(TrainerStorage.class);
        System.out.println(ts.findAll());
    }
}

