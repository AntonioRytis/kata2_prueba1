package software.ulpgc.kata2;

import software.ulpgc.kata2.io.TextSummaryWriter;
import software.ulpgc.kata2.io.TsvLaptopLoader;
import software.ulpgc.kata2.model.LaptopSummarizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class Main {
    private static final String DATASET = "laptops.tsv";
    private static final String DATASET_URL = "https://raw.githubusercontent.com/AntonioRytis/kata2_prueba1/master/laptops.tsv";
    private static final String OUTPUT_FILE = "summary.txt";

    public static void main(String[] args) {
        LaptopReport report = new LaptopReport(new TsvLaptopLoader(), new LaptopSummarizer(), new TextSummaryWriter());
        fileToConsole(report);
        fileToFile(report);
        urlToConsole(report);
    }

    private static void fileToConsole(LaptopReport report) {
        System.out.println("== Fichero -> consola ==");
        try (InputStream input = new FileInputStream(DATASET)) {
            report.generate(input, System.out);
        } catch (FileNotFoundException e) {
            System.err.println("No existe el fichero: " + DATASET);
        } catch (IOException | RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void fileToFile(LaptopReport report) {
        System.out.println("== Fichero -> fichero ==");
        try (InputStream input = new FileInputStream(DATASET);
             OutputStream output = new FileOutputStream(OUTPUT_FILE)) {
            report.generate(input, output);
            System.out.println("Resumen guardado en " + OUTPUT_FILE);
        } catch (FileNotFoundException e) {
            System.err.println("No existe el fichero: " + DATASET);
        } catch (IOException | RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void urlToConsole(LaptopReport report) {
        System.out.println("== URL -> consola ==");
        try (InputStream input = URI.create(DATASET_URL).toURL().openStream()) {
            report.generate(input, System.out);
        } catch (IOException | RuntimeException e) {
            System.err.println("No se pudo leer la URL: " + e.getMessage());
        }
    }
}