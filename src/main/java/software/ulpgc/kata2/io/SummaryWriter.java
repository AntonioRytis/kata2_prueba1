package software.ulpgc.kata2.io;

import software.ulpgc.kata2.model.BrandSummary;

import java.io.OutputStream;
import java.util.List;

public interface SummaryWriter {
    void write(List<BrandSummary> summary, OutputStream outputStream);
}