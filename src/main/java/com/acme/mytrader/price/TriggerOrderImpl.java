package com.acme.mytrader.price;

import com.acme.mytrader.model.DIRECTION;
import com.acme.mytrader.model.Order;

import java.util.Objects;
import java.util.Optional;

public class TriggerOrderImpl implements TriggerOrder {

    private final Order order;

    public TriggerOrderImpl(Order order) {
        this.order = order;
    }

    @Override
    public Optional<Order> monitor(String security, double price) {
        if (!Objects.equals(security, order.getSecurity()))
            return Optional.empty();
        if (order.getBuySell() == DIRECTION.BUY && price < order.getPrice()) {
            return Optional.of(order);
        } else if (order.getBuySell() == DIRECTION.SELL && price > order.getPrice()) {
            return Optional.of(order);
        }
        return Optional.empty();
    }
}
