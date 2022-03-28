package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.model.DIRECTION;
import com.acme.mytrader.model.Order;

import java.util.Objects;

public class PriceListenerImpl implements PriceListener {
    private final ExecutionService executionService;
    private final String name;
    private final TriggerOrder trigger;

    private PriceListenerImpl(ExecutionService executionService, String name, TriggerOrder trigger) {
        this.executionService = executionService;
        this.name = name;
        this.trigger = trigger;
    }

    public static PriceListenerImpl of(ExecutionService executionService, String name, TriggerOrder trigger) {
        return new PriceListenerImpl(executionService, name, trigger);
    }

    @Override
    public void priceUpdate(String security, double price) {
        trigger.monitor(security, price).ifPresent(this::submitOrder);
    }

    private void submitOrder(Order order) {
        if (order.getBuySell() == DIRECTION.BUY) {
            executionService.buy(order.getSecurity(), order.getPrice(), order.getVolume());
        } else {
            executionService.sell(order.getSecurity(), order.getPrice(), order.getVolume());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceListenerImpl that = (PriceListenerImpl) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
