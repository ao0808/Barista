package ru.sbrf.barista;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.sbrf.barista.implement.BaristaNatasha;
import ru.sbrf.barista.implement.CashDeskVitek;
import ru.sbrf.barista.model.CoffeeOrder;
import ru.sbrf.coffee.impl.CoffeeMachineVitek;
import ru.sbrf.coffee.listingOfOrderParameters.TypeOfCoffee;
import ru.sbrf.coffee.listingOfOrderParameters.VolumeOfCup;
import ru.sbrf.coffee.model.CupOfCoffee;



public class BaristaTest extends Assert {
    @Test
    public void builderTest() {
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(TypeOfCoffee.GLACE).
                volumeOfCup(VolumeOfCup.GRANDE).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();

        assertNotNull(newOrder);
        assertNotNull(bestCoffeeMachine);
        assertNotNull(baristaNatasha);
    }

    @Test
    public void makeCoffeeEspressoBaristaTest() {
        verifyCoffee(TypeOfCoffee.ESPRESSO, VolumeOfCup.GRANDE, 400);
        verifyCoffee(TypeOfCoffee.ESPRESSO, VolumeOfCup.BIG, 300);
        verifyCoffee(TypeOfCoffee.ESPRESSO, VolumeOfCup.MEDIUM, 200);
        verifyCoffee(TypeOfCoffee.ESPRESSO, VolumeOfCup.SMALL, 100);
    }

    @Test
    public void makeCoffeeCappuccinoBaristaTest() {
        verifyCoffee(TypeOfCoffee.CAPPUCCINO, VolumeOfCup.GRANDE, 400);
        verifyCoffee(TypeOfCoffee.CAPPUCCINO, VolumeOfCup.BIG, 300);
        verifyCoffee(TypeOfCoffee.CAPPUCCINO, VolumeOfCup.MEDIUM, 200);
        verifyCoffee(TypeOfCoffee.CAPPUCCINO, VolumeOfCup.SMALL, 100);
    }

    @Test
    public void makeCoffeeGlaceBaristaTest() {
        verifyCoffee(TypeOfCoffee.GLACE, VolumeOfCup.GRANDE, 400);
        verifyCoffee(TypeOfCoffee.GLACE, VolumeOfCup.BIG, 300);
        verifyCoffee(TypeOfCoffee.GLACE, VolumeOfCup.MEDIUM, 200);
        verifyCoffee(TypeOfCoffee.GLACE, VolumeOfCup.SMALL, 100);
    }


    public void verifyCoffee(TypeOfCoffee typeOfCoffee, VolumeOfCup volumeOfCup, Integer cash){
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(typeOfCoffee).
                volumeOfCup(volumeOfCup).pay(cash).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();

        CupOfCoffee cup = baristaNatasha.makeCoffee(newOrder);

        assertEquals(typeOfCoffee, cup.getTypeOfCoffee());
        assertEquals(volumeOfCup, cup.getVolumeOfCup());
        assertEquals(cash, baristaNatasha.getCashProfit());
    }

    @Test
    public void CleanCoffeeMachineTest() {
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(TypeOfCoffee.ESPRESSO).
                volumeOfCup(VolumeOfCup.BIG).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();
        for (int i = 0; i < 5; i++) {
            baristaNatasha.makeCoffee(newOrder);
        }
        assertEquals(bestCoffeeMachine.getCleanCounter(), 5);
        baristaNatasha.cleanMachine();
        assertEquals(bestCoffeeMachine.getCleanCounter(), 0);
    }

    @Test
    public void ParametersCoffeeMachineTest() {
        CoffeeOrder newOrder = CoffeeOrder.builder().
                typeOfCoffee(TypeOfCoffee.GLACE).
                volumeOfCup(VolumeOfCup.GRANDE).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();

        baristaNatasha.makeCoffee(newOrder);

        assertEquals(bestCoffeeMachine.getCleanCounter(), 1);
        assertEquals(bestCoffeeMachine.getAmountOfCoffeeBeans(), 440);
        assertEquals(bestCoffeeMachine.getAmountOfWater(), 1120);
    }

    @Test
    public void AddIngredientsCoffeeMachineTest() {
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(TypeOfCoffee.GLACE).
                volumeOfCup(VolumeOfCup.GRANDE).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();

        baristaNatasha.makeCoffee(newOrder);
        baristaNatasha.addCoffeeBeans();
        baristaNatasha.addWater();

        assertEquals(bestCoffeeMachine.getAmountOfCoffeeBeans(), 500);
        assertEquals(bestCoffeeMachine.getAmountOfWater(), 1500);
    }

    @Test
    public void AddAllIngredientsCoffeeMachineTest() {
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(TypeOfCoffee.GLACE).
                volumeOfCup(VolumeOfCup.GRANDE).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();

        baristaNatasha.makeCoffee(newOrder);

        baristaNatasha.addAllIngredients();
        assertEquals(bestCoffeeMachine.getAmountOfCoffeeBeans(), 500);
        assertEquals(bestCoffeeMachine.getAmountOfWater(), 1500);
    }


    @Test
    public void ExceptionCoffeeMachineOutOfWaterTest(){
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(TypeOfCoffee.CAPPUCCINO).
                volumeOfCup(VolumeOfCup.GRANDE).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();
        String messageException = "0, Out of water";
        for (int i = 0; i < 3; i++) {
            baristaNatasha.makeCoffee(newOrder);
        }
        verifyException(baristaNatasha, newOrder,messageException);
    }

    @Test
    public void ExceptionCoffeeMachineOutOfCoffeeBeansTest(){
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(TypeOfCoffee.GLACE).
                volumeOfCup(VolumeOfCup.GRANDE).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();

        String messageException = "1, Out of coffee beans";
        for (int i = 0; i < 7; i++) {
            baristaNatasha.makeCoffee(newOrder);
            baristaNatasha.addWater();
            baristaNatasha.cleanMachine();
        }

        verifyException(baristaNatasha, newOrder,messageException);
    }

    @Test
    public void ExceptionCoffeeMachineCleanMachineTest(){
        CoffeeOrder newOrder = CoffeeOrder.builder().typeOfCoffee(TypeOfCoffee.GLACE).
                volumeOfCup(VolumeOfCup.GRANDE).pay(500).build();
        CoffeeMachineVitek bestCoffeeMachine = CoffeeMachineVitek.builder().amountOfWater(1500).
                amountOfCoffeeBeans(500).cleanCounter(0).build();
        CashDeskVitek cashDesk = CashDeskVitek.builder().build();
        BaristaNatasha baristaNatasha = BaristaNatasha.builder().cashDesk(cashDesk).
                coffeeMachine(bestCoffeeMachine).build();

        String messageException = "3, Barista, cleaning CoffeeMachine!";
        for (int i = 0; i < 6; i++) {
            baristaNatasha.makeCoffee(newOrder);
            baristaNatasha.addWater();
        }

        verifyException(baristaNatasha, newOrder,messageException);
    }

    public void verifyException(BaristaNatasha baristaNatasha, CoffeeOrder newOrder, String messageException){
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            baristaNatasha.makeCoffee(newOrder);
        });
        Assertions.assertEquals(messageException, exception.getMessage());
    }
}