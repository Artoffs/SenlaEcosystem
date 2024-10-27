package com.ecosystem.utils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Serializer {

    public static <T> void serialize(List<T> list, String filename) {
        if (list == null || list.isEmpty()) return;

        try {
            filename = filename.replace(":", "_");
            File file = new File(filename);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Class<?> cls = list.getFirst().getClass();
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                writer.write(field.getName() + ",");
            }
            writer.newLine();

            for (T obj : list) {
                for (Field field : fields) {
                    writer.write(field.get(obj).toString() + ",");
                }
                writer.newLine();
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <K extends Enum<K>, V extends Double> void serialize(Map<K, V> map, String filename) {
        if (map == null || map.isEmpty()) return;

        try {
            filename = filename.replace(":", "_");
            File file = new File(filename);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write headers
            writer.write("Key,Value");
            writer.newLine();

            // Write map entries
            for (Map.Entry<K, V> mapEntry : map.entrySet()) {
                writer.write(mapEntry.getKey().name() + "," + mapEntry.getValue().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> deserializeList(String filename, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Field[] fields = cls.getDeclaredFields();
            reader.readLine();

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Constructor<T> constructor = cls.getConstructor();
                T obj = constructor.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    if(fields[i].getType() == int.class) {
                        fields[i].set(obj, Integer.parseInt(parts[i]));
                    } else if (fields[i].getType() == double.class) {
                        fields[i].set(obj, Double.parseDouble(parts[i]));
                    } else if (fields[i].getType() == UUID.class) {
                        fields[i].set(obj, UUID.fromString(parts[i]));
                    } else if (fields[i].getType().isEnum()) {
                        fields[i].set(obj, Enum.valueOf((Class<Enum>) fields[i].getType(), parts[i]));
                    } else if (fields[i].getType() == boolean.class) {
                        fields[i].set(obj, Boolean.parseBoolean(parts[i]));
                    } else {
                        fields[i].set(obj, parts[i]);
                    }
                }
            list.add(obj);
            }
        } catch (IOException | NoSuchMethodException | InstantiationException | InvocationTargetException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <K extends Enum<K>, V extends Double> Map<K, V> deserializeMap(String filename, Class<K> keyClass) {
        Map<K, V> map = new EnumMap<>(keyClass);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Пропускаем заголовок

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                K key = Enum.valueOf(keyClass, parts[0]);
                V value = (V) Double.valueOf(parts[1]);
                map.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
