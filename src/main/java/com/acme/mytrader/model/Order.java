package com.acme.mytrader.model;

public class Order {
    private final DIRECTION buySell;
    private final String security;
    private final double price;
    private final int volume;

    private Order(DIRECTION buySell, String security, double price, int volume) {
        this.buySell = buySell;
        this.security = security;
        this.price = price;
        this.volume = volume;
    }

    public static Order of(DIRECTION buySell, String security, double price, int volume) {
        return new Order(buySell, security, price, volume);
    }

    public int getVolume() {
        return volume;
    }

    public double getPrice() {
        return price;
    }

    public String getSecurity() {
        return security;
    }

    public DIRECTION getBuySell() {
        return buySell;
    }

}
