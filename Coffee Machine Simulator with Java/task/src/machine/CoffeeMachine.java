package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static int waterStock;
    public static int milkStock;
    public static int beansStock;
    public static int cupsStock;
    public static int cash;

    public static void setWaterStock(int waterStock) {
        CoffeeMachine.waterStock = waterStock;
    }

    public static void setMilkStock(int milkStock) {
        CoffeeMachine.milkStock = milkStock;
    }

    public static void setBeansStock(int beansStock) {
        CoffeeMachine.beansStock = beansStock;
    }
    public static void setCupsStock(int cupsStock) {
        CoffeeMachine.cupsStock = cupsStock;
    }

    public static void setCash(int cash) {
        CoffeeMachine.cash = cash;
    }

    public static int getWaterStock() {
        return waterStock;
    }

    public static int getMilkStock() {
        return milkStock;
    }
    public static int getBeansStock() {
        return beansStock;
    }

    public static int getCupsStock() {
        return cupsStock;
    }

    public static int getCash() {
        return cash;
    }
    public static void action() {
        Scanner sc = new Scanner(System.in);
        String actionInput = " ";
        while (!actionInput.equals("exit")){
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            actionInput = sc.next();
            if (actionInput.equals("buy")) {
                buy();
            } else if (actionInput.equals("fill")) {
                fill();
            } else if (actionInput.equals("take")) {
                take();
            } else if (actionInput.equals("remaining")){
                printInventory();
            } else if (actionInput.equals("exit")){

            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }
//        printInventory();
    }

    public static void useStock(int water, int milk, int beans, int cups) {
        setWaterStock(getWaterStock()-water);
        setMilkStock(getMilkStock()-milk);
        setBeansStock(getBeansStock()-beans);
        setCupsStock(getCupsStock()-cups);
    }

    public static void buy() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String selection = " ";
        boolean idx = true;
        while (idx) {
            selection = sc.next();
            if (selection.equals("1")) {
                Espresso esp = new Espresso();
                boolean tmp = esp.enoughStock(1);
                if (tmp){
                    useStock(esp.getWater(), esp.getMilk(), esp.getBeans(), 1);
                    setCash(getCash()+esp.price);
                }
                idx = false;
            } else if (selection.equals("2")) {
                Latte lat = new Latte();
                boolean tmp = lat.enoughStock(1);
                if (tmp) {
                    useStock(lat.getWater(), lat.getMilk(), lat.getBeans(), 1);
                    setCash(getCash()+lat.price);
                }
                idx = false;
            } else if (selection.equals("3")) {
                Cappuccino cap = new Cappuccino();
                boolean tmp = cap.enoughStock(1);
                if (tmp) {
                    useStock(cap.getWater(), cap.getMilk(), cap.getBeans(), 1);
                    setCash(getCash()+cap.price);
                }
                idx = false;
            } else if (selection.equals("back")) {
                return;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }
    }
    public static void fill() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        int waterStock = sc.nextInt();
        CoffeeMachine.setWaterStock(getWaterStock() + waterStock);
        System.out.println("Write how many ml of milk you want to add:");
        int milkStock = sc.nextInt();
        CoffeeMachine.setMilkStock(getMilkStock() + milkStock);
        System.out.println("Write how many grams of coffee beans you want to add:");
        int beansStock = sc.nextInt();
        CoffeeMachine.setBeansStock(getBeansStock() + beansStock);
        System.out.println("Write how many disposable cups you want to add:");
        int cupsStock = sc.nextInt();
        CoffeeMachine.setCupsStock(getCupsStock() + cupsStock);
    }
    public static void take() {
        System.out.println(String.format("I gave you $%d", getCash()));
        setCash(0);
    }

    public static void printInventory() {
        System.out.println("The coffee machine has:");
        System.out.println(String.format("%d ml of water", getWaterStock()));
        System.out.println(String.format("%d ml of milk", getMilkStock()));
        System.out.println(String.format("%d g of coffee beans", getBeansStock()));
        System.out.println(String.format("%d disposable cups", getCupsStock()));
        System.out.println(String.format("$%d of money", getCash()));
    }


    public static void starting() {
        System.out.println("Starting to make a coffee");
    }
    public static void grinding() {
        System.out.println("Grinding coffee beans");
    }
    public static void boiling() {
        System.out.println("Boiling water");
    }
    public static void mixing() {
        System.out.println("Mixing boiled water with crushed coffee beans");
    }
    public static void pourCoffee() {
        System.out.println("Pouring coffee into the cup");
    }
    public static void pourMilk() {
        System.out.println("Pouring some milk into the cup");
    }
    public static void ready() {
        System.out.println("Coffee is ready!");
    }
}

class Coffee {
    int water;
    int milk;
    int beans;
    int price;
    String coffeeName;
    public Coffee(String coffeeName, int price, int water, int milk, int beans){
        this.coffeeName = coffeeName;
        this.price = price;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
    }
    public void calcIngredients() {
        Scanner sc = new Scanner(System.in);
        System.out.println(String.format("Write how many cups of %s you will need:", coffeeName));
        boolean idx = true;
        int countCups = 0;
        while (idx) {
            countCups = sc.nextInt();
            if (countCups >= 0) {
                idx = false;
            } else {
                System.out.println("Invalid input, please try again.");
            }
        }
//        System.out.println(String.format("For %d cups of %s you will need:", countCups, coffeeName));
//        System.out.println(String.format("%d ml of water", requiredWater(countCups)));
//        System.out.println(String.format("%d ml of milk", requiredMilk(countCups)));
//        System.out.println(String.format("%d g of coffee beans", requiredBeans(countCups)));
        enoughStock(countCups);
    }
    public int requiredWater(int countCups) {
        return countCups * water;
    }
    public int requiredMilk(int countCups) {
        return countCups * milk;
    }
    public int requiredBeans(int countCups) {
        return countCups * beans;
    }

    public boolean enoughStock(int countCups) {
//        int capacityWater = CoffeeMachine.getWaterStock() / requiredWater(countCups);
//        int capacityMilk = CoffeeMachine.getMilkStock() / requiredMilk(countCups);
//        int capacityBeans = CoffeeMachine.getBeansStock() / requiredBeans(countCups);
//        int capacityOverall = Math.min(Math.min(capacityWater, capacityMilk),capacityBeans);
        int capacityWater = CoffeeMachine.getWaterStock() / getWater();
        int capacityMilk = 999999;
        if (getMilk() != 0) {
            capacityMilk = CoffeeMachine.getMilkStock() / water;
        }
        int capacityBeans = CoffeeMachine.getBeansStock() / getBeans();
        int capacityOverall = Math.min(Math.min(capacityWater, capacityMilk),capacityBeans);

        if (
            capacityOverall == countCups
        )   {
            System.out.println("Yes, I can make that amount of coffee");
            return true;
        } else if (
                capacityOverall > countCups
        ) {
            System.out.println(String.format("Yes, I can make that amount of coffee (and even %d more than that)", capacityOverall-countCups));
            return true;
        } else {
            System.out.println(String.format("No, I can make only %d cup(s) of coffee", capacityOverall));
            return false;
        }
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }
}
class NormalCoffee extends Coffee {
    public NormalCoffee(){
        super( "normalCoffee",1,200, 50, 15);
    }
}
class Espresso extends Coffee {
    public Espresso(){
        super( "Espresso",4,250, 0, 16);
    }
}
class Latte extends Coffee {
    public Latte(){
        super( "Latte",7, 350, 75, 20);
    }
}
class Cappuccino extends Coffee {
    public Cappuccino(){
        super( "Cappuccino",6, 200, 100, 12);
    }
}

class Main{
    public static void main(String[] args) {
        CoffeeMachine.setCash(550);
        CoffeeMachine.setWaterStock(400);
        CoffeeMachine.setMilkStock(540);
        CoffeeMachine.setBeansStock(120);
        CoffeeMachine.setCupsStock(9);
        CoffeeMachine.action();
    }
}
