package software.ulpgc.kata2.io;

import software.ulpgc.kata2.model.Laptop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TsvLaptopLoader implements LaptopLoader {
    private static final String SEPARATOR = "\t";
    private static final int COLUMNS = 5;

    @Override
    public List<Laptop> load(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return loadFrom(reader.lines().toList());
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer la entrada", e);
        }
    }

    private List<Laptop> loadFrom(List<String> lines) {
        List<Laptop> laptops = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            if (!lines.get(i).isBlank()) {
                laptops.add(loadFrom(lines.get(i), i + 1));
            }
        }
        return laptops;
    }

    private Laptop loadFrom(String line, int lineNumber) {
        try {
            return toLaptop(line.split(SEPARATOR, -1));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Línea " + lineNumber + " inválida: " + e.getMessage());
        }
    }

    private Laptop toLaptop(String[] fields) {
        if (fields.length != COLUMNS) {
            throw new IllegalArgumentException("se esperaban " + COLUMNS + " columnas y hay " + fields.length);
        }
        return new Laptop(
                fields[0].trim(),
                fields[1].trim(),
                Integer.parseInt(fields[2].trim()),
                Integer.parseInt(fields[3].trim()),
                Double.parseDouble(fields[4].trim())
        );
    }
}