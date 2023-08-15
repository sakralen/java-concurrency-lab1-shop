package edu.hsai.shop;

import java.util.Random;

public class Shop {
    private final int MIN_TICKET_NUMBER = 1;
    private final int MAX_TICKET_NUMBER;
    private final int MAX_SERVICE_TIME;
    private final int S_TO_MS_MULTIPLIER = 1000;
    private int currentTicket = 1;
    private int displayedTicket = 1;

    public Shop(int maxTicket, int maxServiceTime) {
        MAX_TICKET_NUMBER = maxTicket;
        MAX_SERVICE_TIME = maxServiceTime;
    }

    public synchronized int getNextTicket(int ticket) {
        if (ticket != MAX_TICKET_NUMBER) {
            ticket++;
        } else {
            ticket = MIN_TICKET_NUMBER;
        }
        return ticket;
    }

    public synchronized int getTicket() {
        int ticket = currentTicket;
        currentTicket = getNextTicket(currentTicket);
        return ticket;
    }

    public void serveCustomer(int ticket) {
        try {
            synchronized (this) {
                while (ticket != displayedTicket) {
                    wait();
                }

                System.out.printf("Serving customer with ticket № %d...%n", ticket);
                Thread.sleep((new Random().nextInt(MAX_SERVICE_TIME) + 1) * S_TO_MS_MULTIPLIER);

                System.out.printf("Finished serving customer with ticket № %d!%n", ticket);
                displayedTicket = getNextTicket(displayedTicket);
                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
