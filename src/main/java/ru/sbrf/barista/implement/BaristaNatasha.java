package ru.sbrf.barista.implement;

import lombok.Builder;
import lombok.Data;
import ru.sbrf.barista.api.barista.Barista;
import ru.sbrf.barista.api.generalInterface.AddIngredientInCoffeeMachine;
import ru.sbrf.barista.model.CoffeeOrder;
import ru.sbrf.coffee.impl.CoffeeMachineVitek;
import ru.sbrf.coffee.model.CupOfCoffee;
import ru.sbrf.coffee.model.Order;

@Data
@Builder
public class BaristaNatasha implements Barista, AddIngredientInCoffeeMachine {
    private CoffeeMachineVitek coffeeMachine;
    private CashDeskVitek cashDesk;

    public BaristaNatasha(CoffeeMachineVitek coffeeMachine, CashDeskVitek cashDesk) {
        this.coffeeMachine = coffeeMachine;
        this.cashDesk = cashDesk;
    }

    public CupOfCoffee makeCoffee(CoffeeOrder newOrder) {
        addCashProfit(newOrder.getPay());
        return coffeeMachine.makeCoffee(Order.builder().typeOfCoffee(newOrder.getTypeOfCoffee()).
                volumeOfCup(newOrder.getVolumeOfCup()).build());
    }

    private void addCashProfit(Integer pay) {
        cashDesk.addCashProfit(pay);
    }
    public Integer getCashProfit(){
        return cashDesk.getCashProfit();
    }

    @Override
    public void cleanMachine() {
        coffeeMachine.clean();
    }

    @Override
    public void addWater() {
        coffeeMachine.addWater();
    }

    @Override
    public void addCoffeeBeans() {
        coffeeMachine.addCoffeeBeans();
    }

    @Override
    public void addAllIngredients() {
        coffeeMachine.addAllIngredients();
    }
}
