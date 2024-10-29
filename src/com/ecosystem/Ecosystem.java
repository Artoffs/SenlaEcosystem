package com.ecosystem;

import com.ecosystem.models.*;
import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;

import java.util.Random;
import java.util.Scanner;

public class Ecosystem {

    private static Simulation currentSimulation;
    private static final Random random = new Random();
    
    public static void showStartMenu(Scanner scanner) {
        System.out.println("Добро пожаловать в систему!");
        System.out.println("Выберите опцию:");
        System.out.println("1. Начать новую симуляцию");
        System.out.println("2. Продолжить существующую");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                currentSimulation = SimulationManager.startNew();
                break;
            case 2:
                System.out.println("Введите имя симуляции: ");
                currentSimulation = SimulationManager.continueExist(scanner.nextLine());
            default:
                System.out.println("Что-то пошло не так! Попробуйте снова!");
        }
    }
    
    public static void showMainMenu(Scanner scanner) {
        System.out.println("Меню: ");
        System.out.println("1. Изменить животных");
        System.out.println("2. Изменить условия");
        System.out.println("3. Получить прогноз");
        System.out.println("4. Запустить симуляцию");
        System.out.println("5. Вернуться");
        int choice = scanner.nextInt();
        
        switch(choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
        
    }

    public static void showAnimalMenu(Scanner scanner) {
        System.out.println("Меню работы с животными: ");
        System.out.println("1. Добавить новых животных");
        System.out.println("2. Изменить животных");
        System.out.println("3. Удалить животное");
        System.out.println("4. Вернуться");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                int amount;
                System.out.println("Введите тип животного:");
                System.out.println("1. Олень");
                System.out.println("2. Волк");
                int typeChoice = scanner.nextInt();
                System.out.println("Сколько животных вы хотите добавить?");
                do {
                    amount = scanner.nextInt();
                } while (amount < 0);

                if(typeChoice == 1) {
                    for (int i = 0; i < amount; i++) {
                        currentSimulation.addAnimal(AnimalSpecies.DEER, 100, random.nextInt(currentSimulation.getWidthEnv()), random.nextInt(currentSimulation.getHeightEnv()));
                    }
                } else if (typeChoice == 2) {
                    for (int i = 0; i < amount; i++) {
                        currentSimulation.addAnimal(AnimalSpecies.WOLF, 100, random.nextInt(currentSimulation.getWidthEnv()), random.nextInt(currentSimulation.getHeightEnv()));
                    }
                } else {
                    System.out.println("Такого варианта нет в списке");
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(currentSimulation == null) {
            showStartMenu(scanner);
        }

        showMainMenu(scanner);
        
        System.out.println(currentSimulation.getSimulationService().getResources());
        currentSimulation.addAnimal(AnimalSpecies.WOLF, 100, 0, 0);
        currentSimulation.addAnimal(AnimalSpecies.WOLF, 100, 0, 0);
        currentSimulation.addAnimal(AnimalSpecies.DEER, 100, 0, 1);
        currentSimulation.addAnimal(AnimalSpecies.DEER, 100, 0, 2);
        currentSimulation.addAnimal(AnimalSpecies.DEER, 100, 0, 3);
        currentSimulation.addPlant(PlantSpecies.SUNFLOWER, 1, 1);
        currentSimulation.addPlant(PlantSpecies.SUNFLOWER, 1, 1);
        try {
            currentSimulation.run();
        } catch (InterruptedException e) {
            System.err.println("Что-то пошло не так! Ваша экосистема больше не может развиваться!");
        }
        finally {
            SimulationManager.saveCurrentSimulation(currentSimulation);
        }
    }
}
