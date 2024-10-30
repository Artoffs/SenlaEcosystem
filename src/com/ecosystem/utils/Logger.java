package com.ecosystem.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс, который производит вывод событий в консоль и запись в файл
 */

public class Logger {
    private static PrintWriter fileWriter;

    static {
        try {
            fileWriter = new PrintWriter(new FileWriter("logfile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void log(Event event) {
        if (!event.getDescription().isEmpty()) {
            System.out.println(event.getDescription());
            fileWriter.println(event.getDescription());
            fileWriter.flush(); // Сбрасываем буфер
        }
    }

    public static void close() {
        if (fileWriter != null) {
            fileWriter.close();
        }
    }
}
