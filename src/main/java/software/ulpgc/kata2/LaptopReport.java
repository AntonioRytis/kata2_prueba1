package software.ulpgc.kata2;

import software.ulpgc.kata2.io.LaptopLoader;
import software.ulpgc.kata2.io.SummaryWriter;
import software.ulpgc.kata2.model.BrandSummary;
import software.ulpgc.kata2.model.Laptop;
import software.ulpgc.kata2.model.LaptopSummarizer;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class LaptopReport {
    private final LaptopLoader loader;
    private final LaptopSummarizer summarizer;
    private final SummaryWriter writer;

    public LaptopReport(LaptopLoader loader, LaptopSummarizer summarizer, SummaryWriter writer) {
        this.loader = loader;
        this.summarizer = summarizer;
        this.writer = writer;
    }

    public void generate(InputStream input, OutputStream output) {
        List<Laptop> laptops = loader.load(input);
        List<BrandSummary> summary = summarizer.summarizeByBrand(laptops);
        writer.write(summary, output);
    }
}