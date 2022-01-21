package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int WATER_UNIT = 200;
    private static final int MILK_UNIT = 50;
    private static final int COFFEE_UNIT = 15;
    private static final int WATER_UNIT_ESPRESSO = 250;
    private static final int COFFEE_UNIT_ESPRESSO = 16;
    private static final int PRICE_ESPRESSO = 4;
    private static final int WATER_UNIT_LATTE = 350;
    private static final int MILK_UNIT_LATTE = 75;
    private static final int COFFEE_UNIT_LATTE = 20;
    private static final int PRICE_LATTE = 7;
    private static final int WATER_UNIT_CAPPUCCINO = 200;
    private static final int MILK_UNIT_CAPPUCCINO = 100;
    private static final int COFFEE_UNIT_CAPPUCCINO = 12;
    private static final int PRICE_CAPPUCCINO = 6;
    private static int cupsNeeded;
    private static int waterAmount = 400;
    private static int milkAmount = 540;
    private static int coffeeAmount = 120;
    private static int cupsAmount = 9;
    private static int moneyAmount = 550;

    public static void main(String[] args) {
        //makeCoffee();
        //estimateTheNumberOfServings();
        showMenu();
    }

    public static void makeCoffee() {
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");
    }

    public static void calculateIngredients() {
        System.out.println("Write how many cups of coffee you will need: ");
        cupsNeeded = SCANNER.nextInt();
        /*System.out.println("For " + cupsNeeded + " cups of coffee you will need:");
        System.out.println(cupsNeeded * WATER_UNIT + " ml of water");
        System.out.println(cupsNeeded * MILK_UNIT + " ml of milk");
        System.out.println(cupsNeeded * COFFEE_UNIT + " g of coffee beans");*/
    }

    public static void estimateTheNumberOfServings() {
        System.out.println("Write how many ml of water the coffee machine has: ");
        int waterAmount = SCANNER.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int milkAmount = SCANNER.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int coffeeAmount = SCANNER.nextInt();
        int possibleCupsWater = waterAmount / WATER_UNIT;
        int possibleCupsMilk = milkAmount / MILK_UNIT;
        int possibleCupsCoffee = coffeeAmount / COFFEE_UNIT;
        int maxPossibleCups = min(possibleCupsWater, possibleCupsMilk, possibleCupsCoffee); //calculating the number of cups based on the least abundant resource needed for making one cup of coffee
        calculateIngredients();

        if (cupsNeeded > possibleCupsWater || cupsNeeded > possibleCupsMilk || cupsNeeded > possibleCupsCoffee) {
            System.out.printf("No, I can make only %d cup(s) of coffee\n", maxPossibleCups);
        } else if (cupsNeeded == possibleCupsWater || cupsNeeded == possibleCupsMilk || cupsNeeded == possibleCupsCoffee) {
            System.out.println("Yes, I can make that amount of coffee");
        } else {
            int surplusCups = maxPossibleCups - cupsNeeded;
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)\n", surplusCups);
        }
    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void printState() {
        System.out.printf("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money\n\n", waterAmount, milkAmount, coffeeAmount, cupsAmount, moneyAmount);
    }

    public static void showMenu() {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        String option = SCANNER.next();
        switch (option) {
            case "buy":
                System.out.println();
                buy(); //buying one cup of a specified coffee variety
                break;
            case "fill":
                System.out.println();
                fill(); //filling the machine with the specified amount of resources
                showMenu();
                break;
            case "take":
                System.out.println();
                take(); //collecting the whole amount of the available money from the machine
                showMenu();
                break;
            case "remaining":
                System.out.println();
                printState();
                showMenu();
                break;
            case "exit":
                break;

        }
    }

    public static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                "back - to main menu: ");
        String coffeeOption = SCANNER.next();
        switch (coffeeOption) {
            case "1":
                if (waterAmount >= WATER_UNIT_ESPRESSO && coffeeAmount >= COFFEE_UNIT_ESPRESSO &&
                        cupsAmount > 0) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    waterAmount -= WATER_UNIT_ESPRESSO;
                    coffeeAmount -= COFFEE_UNIT_ESPRESSO;
                    cupsAmount--;
                    moneyAmount += PRICE_ESPRESSO;
                } else if (waterAmount < WATER_UNIT_ESPRESSO) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (coffeeAmount < COFFEE_UNIT_ESPRESSO) {
                    System.out.println("Sorry, not enough coffee!\n");
                } else {
                    System.out.println("Sorry, not enough cups!\n");
                }
                showMenu();
                break;
            case "2":
                if (waterAmount >= WATER_UNIT_LATTE && milkAmount >= MILK_UNIT_LATTE &&
                        coffeeAmount >= COFFEE_UNIT_LATTE && cupsAmount > 0) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    waterAmount -= WATER_UNIT_LATTE;
                    milkAmount -= MILK_UNIT_LATTE;
                    coffeeAmount -= COFFEE_UNIT_LATTE;
                    cupsAmount--;
                    moneyAmount += PRICE_LATTE;
                } else if (waterAmount < WATER_UNIT_LATTE) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (milkAmount < MILK_UNIT_LATTE) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (coffeeAmount < COFFEE_UNIT_LATTE) {
                    System.out.println("Sorry, not enough coffee!\n");
                } else {
                    System.out.println("Sorry, not enough cups!\n");
                }
                showMenu();
                break;
            case "3":
                if (waterAmount >= WATER_UNIT_CAPPUCCINO && milkAmount >= MILK_UNIT_CAPPUCCINO &&
                        coffeeAmount >= COFFEE_UNIT_CAPPUCCINO && cupsAmount > 0) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    waterAmount -= WATER_UNIT_CAPPUCCINO;
                    milkAmount -= MILK_UNIT_CAPPUCCINO;
                    coffeeAmount -= COFFEE_UNIT_CAPPUCCINO;
                    cupsAmount--;
                    moneyAmount += PRICE_CAPPUCCINO;
                } else if (waterAmount < WATER_UNIT_CAPPUCCINO) {
                    System.out.println("Sorry, not enough water!\n");
                } else if (milkAmount < MILK_UNIT_CAPPUCCINO) {
                    System.out.println("Sorry, not enough milk!\n");
                } else if (coffeeAmount < COFFEE_UNIT_CAPPUCCINO) {
                    System.out.println("Sorry, not enough coffee!\n");
                } else {
                    System.out.println("Sorry, not enough cups!\n");
                }
                showMenu();
                break;
            case "back":
                System.out.println();
                showMenu();
        }
    }

    public static void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        waterAmount += SCANNER.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        milkAmount += SCANNER.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        coffeeAmount += SCANNER.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        cupsAmount += SCANNER.nextInt();
        System.out.println();
    }

    public static void take() {
        System.out.println("I gave you $" + moneyAmount + "\n");
        moneyAmount = 0;
    }
}