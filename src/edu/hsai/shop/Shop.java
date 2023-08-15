package edu.hsai.shop;

import java.util.Random;

public class Shop {
    private final int MIN_TICKET_NUMBER = 1;
    private final int MAX_TICKET_NUMBER;
    private final int MAX_SERVICE_TIME_MS = 5000;
    private int currentTicket = 0;

    public Shop(int maxTicket) {
        MAX_TICKET_NUMBER = maxTicket;
    }

    public int getNextTicket() {
        if (currentTicket != MAX_TICKET_NUMBER) {
            currentTicket++;
        } else {
            currentTicket = MIN_TICKET_NUMBER;
        }
        return currentTicket;
    }

    public void serveCustomer(int ticket) {
        try {
            synchronized(this) {
                while(ticket != currentTicket) {
                   wait();
                }

                System.out.printf("Serving customer with ticket № %d...%n", ticket);
                Thread.sleep(new Random().nextInt(MAX_SERVICE_TIME_MS) + 1);

                System.out.printf("Finished serving customer with ticket № %d!%n", ticket);
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
