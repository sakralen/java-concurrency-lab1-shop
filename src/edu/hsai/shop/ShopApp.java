package edu.hsai.shop;

public class ShopApp {
    private static final int MAX_CUSTOMERS = 5;
    private static final int MAX_TICKETS = 10;
    private static final int MAX_ARRIVAL_INTERVAL = 3000;

    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop(MAX_TICKETS);

        for (int i = 0; i < MAX_CUSTOMERS; i++) {
            new Thread(new Customer(shop)).start();
            Thread.sleep(MAX_ARRIVAL_INTERVAL);
        }
    }
}
