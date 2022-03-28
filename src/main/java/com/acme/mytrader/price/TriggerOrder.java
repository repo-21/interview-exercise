package com.acme.mytrader.price;

import com.acme.mytrader.model.Order;

import java.util.Optional;

@FunctionalInterface
public interface TriggerOrder {
    Optional<Order> monitor(String security, double price);
}
