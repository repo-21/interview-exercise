package com.acme.mytrader.price;

import com.acme.mytrader.model.Order;
import org.junit.Test;

import static com.acme.mytrader.model.DIRECTION.BUY;
import static com.acme.mytrader.model.DIRECTION.SELL;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TriggerOrderImplTest {

    @Test
    public void monitorBuyLevel() {
        TriggerOrderImpl buyIBM = new TriggerOrderImpl(Order.of(BUY, "IBM", 55, 100));
        assertThat(buyIBM.monitor("IBM", 50).isPresent(), is(true)); // BUY here
        assertThat(buyIBM.monitor("IBM", 56).isPresent(), is(false)); // NO Action
    }

    @Test
    public void monitorSellLevel() {
        TriggerOrderImpl buyIBM = new TriggerOrderImpl(Order.of(SELL, "IBM", 100, 100));
        assertThat(buyIBM.monitor("IBM", 99).isPresent(), is(false)); // NO Action
        assertThat(buyIBM.monitor("IBM", 101).isPresent(), is(true)); // SELL here
    }
}