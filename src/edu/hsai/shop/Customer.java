package edu.hsai.shop;

public class Customer implements Runnable {
    private final Shop shop;
    private final int ticket;

    public Customer(Shop shop) {
        this.shop = shop;
        ticket = shop.getNextTicket();
        System.out.printf("Customer has entered the shop and grabbed ticket № %d!%n", ticket);
    }

    public void run() {
        shop.serveCustomer(ticket);
    }
}
