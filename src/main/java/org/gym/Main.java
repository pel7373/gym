package org.gym;

import org.gym.domain.Trainer;
import org.gym.domain.TrainingType;
import org.gym.dto.TrainerDto;
import org.gym.service.TraineeService;
import org.gym.service.TrainerService;
import org.gym.storage.TrainerStorage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext("org.gym");
        context.registerShutdownHook();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                context.close();
            }});

        System.out.println("================");
        TrainerService trainerService = context.getBean(TrainerService.class);
        TrainerDto trainerDto = trainerService.getById(0L);
        System.out.println(trainerDto);
        System.out.println("================");
        Trainer trainer = context.getBean(Trainer.class);
        trainer.setFirstName("John");
        trainer.setLastName("Doe");
        trainer.setIsActive(true);
        trainer.setSpecialization(TrainingType.yoga);
        trainerService.save(trainer);

        //TrainerStorage ts = context.getBean(TrainerStorage.class);
        //System.out.println(ts.findAll());

        System.out.println(trainerService.getAll());
        System.out.println(trainer);


    }

}

