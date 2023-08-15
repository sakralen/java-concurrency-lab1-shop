package edu.hsai.shop;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
    private final int MIN_TICKET_NUMBER = 1;
    private final int MAX_TICKET_NUMBER;
    private final int MAX_SERVICE_TIME;
    private final int S_TO_MS_MULTIPLIER = 1000;
    private int currentTicket = 1;
    private Lock lock = new ReentrantLock(true);

    public Shop(int maxTicket, int maxServiceTime) {
        MAX_TICKET_NUMBER = maxTicket;
        MAX_SERVICE_TIME = maxServiceTime;
    }

    public synchronized int getTicket() {
        int ticket = currentTicket;
        currentTicket = currentTicket % MAX_TICKET_NUMBER + 1;
        return ticket;
    }

    public void serveCustomer(int ticket) {
        lock.lock();
        try {
            System.out.printf("Serving customer with ticket № %d...%n", ticket);
            Thread.sleep((new Random().nextInt(MAX_SERVICE_TIME) + 1) * S_TO_MS_MULTIPLIER);
            System.out.printf("Finished serving customer with ticket № %d!%n", ticket);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
