package software.ulpgc.kata2.io;

import software.ulpgc.kata2.model.BrandSummary;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

public class TextSummaryWriter implements SummaryWriter {
    @Override
    public void write(List<BrandSummary> summary, OutputStream outputStream) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        if (summary.isEmpty()) {
            writer.println("No hay portátiles para resumir");
        }
        for (BrandSummary brandSummary : summary) {
            writer.println(format(brandSummary));
        }
        writer.flush();
    }

    private String format(BrandSummary brandSummary) {
        return String.format(Locale.ROOT, "%s: %d portátiles, precio medio %.2f",
                brandSummary.brand(), brandSummary.count(), brandSummary.averagePrice());
    }
}