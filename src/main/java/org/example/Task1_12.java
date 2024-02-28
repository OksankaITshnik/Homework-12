package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task1_12 {
    public static void main(String[] args) {
        Thread timeThread = new Thread(() -> {
            try {
                while (true) {
                    SimpleDateFormat   sdf = new SimpleDateFormat("HH:mm:ss") ;
                    System.out.println("Time: " + sdf.format(new Date()));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread messageThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        timeThread.start();
        messageThread.start();

    }
}

