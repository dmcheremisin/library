package com.dmch.math.service;

import lombok.SneakyThrows;

public class SleepUtil {

    @SneakyThrows
    public static void sleepSeconds(int seconds) {
        Thread.sleep(seconds * 1000);
    }
}
