package edu.hsai.shop;

public class Customer implements Runnable {
    private final Shop shop;
    private final int ticket;

    public Customer(Shop shop) {
        this.shop = shop;
        ticket = shop.getTicket();
        System.out.printf("Customer has entered the shop and grabbed ticket № %d!%n", ticket);
    }

    @Override
    public void run() {
        shop.serveCustomer(ticket);
    }
}
