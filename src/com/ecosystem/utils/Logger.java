package com.ecosystem.utils;

public class Logger {
    public synchronized static void log(Event event) {
        if(!event.getDescription().isEmpty()) System.out.println(event.getDescription());
    }
}
