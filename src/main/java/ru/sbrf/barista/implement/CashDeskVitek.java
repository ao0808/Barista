package ru.sbrf.barista.implement;

import lombok.Builder;
import lombok.Data;
import ru.sbrf.barista.api.cashDesk.CashDesk;

@Data
@Builder
public class CashDeskVitek implements CashDesk {
    private Integer cashProfit;

    @Override
    public void addCashProfit(Integer cashProfit) {
        this.cashProfit =+ cashProfit;
    }
}
