package br.com.mazzatech.infrastructure.adapter.output.rest.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public static void delay() {
        try {
            int delay = ThreadLocalRandom.current().nextInt(500, 2000);
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
