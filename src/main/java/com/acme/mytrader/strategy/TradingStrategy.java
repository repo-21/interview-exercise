package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.model.Order;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.TriggerOrderImpl;

import java.util.HashMap;
import java.util.Map;

import static com.acme.mytrader.model.DIRECTION.BUY;
import static com.acme.mytrader.model.DIRECTION.SELL;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
    final private ExecutionService executionService;
    final private PriceSource priceSource;

    public TradingStrategy(ExecutionService executionService, PriceSource priceSource) {
        this.executionService = executionService;
        this.priceSource = priceSource;
        initStrategies();
    }

    public void initStrategies() {
        priceSource.addPriceListener(PriceListenerImpl.of(executionService, "BUY_IBM@55",
                new TriggerOrderImpl(Order.of(BUY, "IBM", 55.0, 100))));

        priceSource.addPriceListener(PriceListenerImpl.of(executionService, "SELL_IBM@100",
                new TriggerOrderImpl(Order.of(SELL, "IBM", 100.0, 100))));
    }
}
