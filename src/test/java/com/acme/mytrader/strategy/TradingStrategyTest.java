package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class TradingStrategyTest {

    @Test
    public void testNothing() {

    }

    @Test
    public void testStrategyBUY() {
        ExecutionService executionService = Mockito.mock(ExecutionService.class);
        doNothing().when(executionService).buy("IBM", 55, 100);
        PriceSourceImpl priceSource = new PriceSourceImpl();

        TradingStrategy tradingStrategy = new TradingStrategy(executionService, priceSource);

        priceSource.priceEvent("IBM", 50);

        verify(executionService, times(1)).buy("IBM", 55, 100);
    }

    @Test
    public void testStrategySELL() {
        ExecutionService executionService = Mockito.mock(ExecutionService.class);
        doNothing().when(executionService).sell("IBM", 100, 100);
        PriceSourceImpl priceSource = new PriceSourceImpl();

        TradingStrategy tradingStrategy = new TradingStrategy(executionService, priceSource);

        priceSource.priceEvent("IBM", 101);

        verify(executionService, times(1)).sell("IBM", 100, 100);
    }

    // This impl isn't expected, but I wasn't able to trigger orders without it
    // so a simplest impl is provided to enable this tests
    @Ignore
    public static class PriceSourceImpl implements PriceSource {
        Set<PriceListener> listeners = new HashSet<>();

        @Override
        public void addPriceListener(PriceListener listener) {
            listeners.add(listener);
        }

        @Override
        public void removePriceListener(PriceListener listener) {
            listeners.remove(listener);
        }

        public void priceEvent(String security, double price) {
            listeners.forEach(l -> l.priceUpdate(security, price));
        }
    }

}


