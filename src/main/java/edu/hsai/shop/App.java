package edu.hsai.shop;

import java.util.Random;

public class App {
    private static final int S_TO_MS_MULTIPLIER = 1000;

    public static void main(String[] args) throws InterruptedException {
        int maxTickets = 10;
        int maxCustomers = 15;
        int maxArrivalInterval = 3;
        int maxServeTime = 5;

        if (args.length > 0) {
            maxTickets = Integer.parseInt(args[0]);

            if (args.length > 1) {
                maxCustomers = Integer.parseInt(args[1]);

                if (args.length > 2) {
                    maxArrivalInterval = Integer.parseInt(args[2]);

                    if (args.length > 3) {
                        maxServeTime = Integer.parseInt(args[3]);
                    }
                }
            }
        }

        Shop shop = new Shop(maxTickets, maxServeTime);

        for (int i = 0; i < maxCustomers; i++) {
            new Thread(new Customer(shop)).start();
            Thread.sleep((new Random().nextInt(maxArrivalInterval) + 1) * S_TO_MS_MULTIPLIER);
        }
    }
}
