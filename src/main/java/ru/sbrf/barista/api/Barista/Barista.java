package ru.sbrf.barista.api.barista;

import ru.sbrf.barista.model.CoffeeOrder;
import ru.sbrf.coffee.model.CupOfCoffee;

public interface Barista {
    CupOfCoffee makeCoffee(CoffeeOrder newOrder);
    void cleanMachine();
}
