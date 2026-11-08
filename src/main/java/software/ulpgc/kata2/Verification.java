package software.ulpgc.kata2;

import software.ulpgc.kata2.io.TextSummaryWriter;
import software.ulpgc.kata2.io.TsvLaptopLoader;
import software.ulpgc.kata2.model.LaptopSummarizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Verification {
    public static void main(String[] args) {
        LaptopReport report = new LaptopReport(new TsvLaptopLoader(), new LaptopSummarizer(), new TextSummaryWriter());
        check(report, "Ruta inexistente", "samples/missing.tsv");
        check(report, "Fichero vacío", "samples/empty.tsv");
        check(report, "Fila mal formada", "samples/malformed.tsv");
        check(report, "Fichero reordenado", "samples/shuffled.tsv");
    }

    private static void check(LaptopReport report, String title, String path) {
        System.out.println("== " + title + " ==");
        try (InputStream input = new FileInputStream(path)) {
            report.generate(input, System.out);
        } catch (FileNotFoundException e) {
            System.out.println("No existe el fichero: " + path);
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}