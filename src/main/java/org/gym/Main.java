package org.gym;

import org.gym.domain.Trainer;
import org.gym.domain.TrainingType;
import org.gym.service.TraineeService;
import org.gym.service.TrainerService;
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

        Trainer trainer = context.getBean(Trainer.class);
        trainer.setFirstName("John");
        trainer.setLastName("Doe");
        trainer.setIsActive(true);
        trainer.setSpecialization(TrainingType.yoga);
        TrainerService trainerService = context.getBean(TrainerService.class);
        trainerService.save(trainer);

        System.out.println(ts.findAll());
        System.out.println(trainer);





    }
}

