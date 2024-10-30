package com.ecosystem;

import com.ecosystem.models.*;
import com.ecosystem.simulation.Simulation;
import com.ecosystem.simulation.SimulationManager;
import com.ecosystem.utils.Logger;

import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

/**
 * Класс, предоставляющий пользовательский интерфейс для взаимодействия с экосистемой.
 */

public class EcosystemApp {

    private static Simulation currentSimulation;
    private static final Random random = new Random();
    
    public static void showStartMenu(Scanner scanner) {
        System.out.println("Добро пожаловать в систему!");
        System.out.println("Выберите опцию:");
        System.out.println("1. Начать новую симуляцию");
        System.out.println("2. Продолжить существующую");
        System.out.println("3. Выйти");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                currentSimulation = SimulationManager.startNew();
                break;
            case 2:
                scanner.nextLine();
                System.out.println("Введите имя симуляции: ");
                String name = scanner.nextLine();
                currentSimulation = SimulationManager.continueExist(name);
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Что-то пошло не так! Попробуйте снова!");
                break;
        }
    }
    
    public static void showMainMenu(Scanner scanner) {
        System.out.println("Меню: ");
        System.out.println("1. Изменить животных");
        System.out.println("2. Изменить растения");
        System.out.println("3. Изменить условия");
        System.out.println("4. Получить прогноз");
        System.out.println("5. Запустить симуляцию");
        System.out.println("6. Cохранить симуляцию");
        System.out.println("7. Выйти");
        int choice = scanner.nextInt();
        
        switch(choice) {
            case 1:
                showAnimalMenu(scanner);
                break;
            case 2:
                showPlantMenu(scanner);
                break;
            case 3:
                showCondMenu(scanner);
                break;
            case 4:
                currentSimulation.getPrediction();
                break;
            case 5:
                try {
                    currentSimulation.run();
                } catch (InterruptedException e) {
                    System.err.println("Что-то пошло не так! Ваша экосистема больше недоступна!");
                    System.exit(0);
                }
                break;
            case 6:
                SimulationManager.saveCurrentSimulation(currentSimulation);
                break;
            case 7:
                currentSimulation = null;
                break;
            default:
                System.out.println("Операция с таким номером не найдена!");
                break;
        }
        
    }

    public static void showAnimalMenu(Scanner scanner) {
        System.out.println("Меню работы с животными: ");
        System.out.println("1. Добавить новых животных");
        System.out.println("2. Удалить животное");
        System.out.println("3. Вывести всех животных");
        System.out.println("4. Вернуться");
        int choice = scanner.nextInt();

        String id;
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
                currentSimulation.getSimulationService().getAllAnimals().forEach(System.out::println);
                scanner.nextLine();
                System.out.println("Введите id животного, которое надо удалить: ");
                id = scanner.nextLine();
                System.out.println(currentSimulation.deleteAnimal(UUID.fromString(id)) ? "Успешно!" : "Такой животного с таким id не найдено!");
                break;
            case 3:
                currentSimulation.getSimulationService().getAllAnimals().forEach(System.out::println);
                break;
            case 4:
                break;
            default:
                System.out.println("Операция с таким номером не найдена!");
                break;
        }
    }

    public static void showPlantMenu(Scanner scanner) {
        System.out.println("Меню работы с растениями: ");
        System.out.println("1. Добавить новых растений");
        System.out.println("2. Удалить растение");
        System.out.println("3. Вывести все растения");
        System.out.println("4. Вернуться");
        int choice = scanner.nextInt();

        String id;
        switch (choice) {
            case 1:
                int amount;
                System.out.println("Сколько животных вы хотите добавить?");
                do {
                    amount = scanner.nextInt();
                } while (amount < 0);
                for (int i = 0; i < amount; i++) {
                    currentSimulation.addPlant(PlantSpecies.SUNFLOWER, random.nextInt(currentSimulation.getWidthEnv()), random.nextInt(currentSimulation.getHeightEnv()));
                }
                break;
            case 2:
                currentSimulation.getSimulationService().getAllPlants().forEach(System.out::println);
                scanner.nextLine();
                System.out.println("Введите id растения, которое надо удалить: ");
                id = scanner.nextLine();
                System.out.println(currentSimulation.deletePlant(UUID.fromString(id)) ? "Успешно!" : "Такой растения с таким id не найдено!");
                break;
            case 3:
                currentSimulation.getSimulationService().getAllPlants().forEach(System.out::println);
                break;
            case 4:
                break;
            default:
                System.out.println("Операция с таким номером не найдена!");
                break;
        }
    }

    public static void showCondMenu(Scanner scanner) {
        System.out.println("Меню работы с условиями: ");
        System.out.println("1. Задать новое значение условию");
        System.out.println("2. Вывести все условия");
        System.out.println("3. Вернуться");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Что вы хотите изменить и какое значение задать?");
                System.out.println("1. Количество воды");
                System.out.println("2. Температуру");
                System.out.println("3. Влажность");
                System.out.println("4. Осадки");
                choice = scanner.nextInt();
                double value = scanner.nextDouble();

                switch (choice) {
                    case 1 -> currentSimulation.setResource(ResourceType.WATER, value);
                    case 2 -> currentSimulation.setCondition(ConditionType.TEMPERATURE, value);
                    case 3 -> currentSimulation.setCondition(ConditionType.HUMIDITY, value);
                    case 4 -> currentSimulation.setCondition(ConditionType.PRECIPITATION, value);
                }
                break;
            case 2:
                System.out.println(currentSimulation.getSimulationService().getResources());
                System.out.println(currentSimulation.getSimulationService().getConditions());
                break;
            case 3:
                break;
            default:
                System.out.println("Операция с таким номером не найдена!");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(currentSimulation == null) {
            showStartMenu(scanner);
        }
        while(currentSimulation != null) {
            showMainMenu(scanner);
        }
        Logger.close();
        scanner.close();
    }
}
