package org.gym;

import org.gym.domain.Trainer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.gym");
        Trainer trainer = context.getBean(Trainer.class);
        trainer.setFirstName("Peter");
        System.out.println(trainer.getFirstName());

        Trainer trainer2 = context.getBean(Trainer.class);
        trainer2.setFirstName("Sergey");
        System.out.println(trainer.getFirstName());
        System.out.println(trainer2.getFirstName());
        System.out.println(trainer == trainer2);
    }
}
