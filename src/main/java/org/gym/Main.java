package org.gym;

import org.gym.domain.Trainee;
import org.gym.domain.Trainer;
import org.gym.dto.TrainerDto;
import org.gym.domain.TrainingType;
import org.gym.dto.TraineeDto;
import org.gym.service.TraineeService;
import org.gym.service.TrainerService;
import org.gym.storage.TrainerStorage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

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
        TraineeService traineeService = context.getBean(TraineeService.class);
        //TraineeDto traineeDto = traineeService.getById(0L);
        //System.out.println(traineeDto);
        System.out.println("================");
        Trainee trainee = context.getBean(Trainee.class);
        trainee.setFirstName("Maria");
        trainee.setLastName("Ivanenko");
        trainee.setIsActive(true);
        trainee.setAddress("Vinnitsya, Soborna str. 15, ap. 6");
        trainee.setDateOfBirth(LocalDate.of(1990, 2, 23));
        traineeService.save(trainee);

        System.out.println(traineeService.getAll());
        System.out.println(trainee);

//        System.out.println("================");
//        TraineeService traineeService = context.getBean(TraineeService.class);
//        //TraineeDto traineeDto = traineeService.getById(0L);
//        //System.out.println(traineeDto);
//        System.out.println("================");
//        Trainee trainee = context.getBean(Trainee.class);
//        trainee.setFirstName("Maria");
//        trainee.setLastName("Ivanenko");
//        trainee.setIsActive(true);
//        trainee.setAddress("Vinnitsya, Soborna str. 15, ap. 6");
//        trainee.setDateOfBirth(LocalDate.of(1990, 2, 23));
//        traineeService.save(trainee);
//
//        System.out.println(traineeService.getAll());
//        System.out.println(trainee);

//        System.out.println("================");
//        TrainerService trainerService = context.getBean(TrainerService.class);
//        TrainerDto trainerDto = trainerService.getById(0L);
//        System.out.println(trainerDto);
//        System.out.println("================");
//        Trainer trainer = context.getBean(Trainer.class);
//        trainer.setFirstName("John");
//        trainer.setLastName("Doe");
//        trainer.setIsActive(true);
//        trainer.setSpecialization(TrainingType.yoga);
//        trainerService.save(trainer);
//
//        System.out.println(trainerService.getAll());
//        System.out.println(trainer);


    }

}

