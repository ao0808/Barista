package ru.sbrf.barista.model;

import lombok.Builder;
import lombok.Data;
import ru.sbrf.coffee.listingOfOrderParameters.TypeOfCoffee;
import ru.sbrf.coffee.listingOfOrderParameters.VolumeOfCup;

@Data
@Builder
public class CoffeeOrder {
    private VolumeOfCup volumeOfCup;
    private TypeOfCoffee typeOfCoffee;
    private Integer pay;
}
