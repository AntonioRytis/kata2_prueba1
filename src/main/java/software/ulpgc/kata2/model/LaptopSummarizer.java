package software.ulpgc.kata2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LaptopSummarizer {
    public List<BrandSummary> summarizeByBrand(List<Laptop> laptops) {
        Map<String, List<Laptop>> laptopsByBrand = new TreeMap<>();
        for (Laptop laptop : laptops) {
            laptopsByBrand.computeIfAbsent(laptop.brand(), brand -> new ArrayList<>()).add(laptop);
        }

        List<BrandSummary> summary = new ArrayList<>();
        for (Map.Entry<String, List<Laptop>> entry : laptopsByBrand.entrySet()) {
            summary.add(summarize(entry.getKey(), entry.getValue()));
        }
        return summary;
    }

    private BrandSummary summarize(String brand, List<Laptop> laptops) {
        double total = 0;
        for (Laptop laptop : laptops) {
            total += laptop.price();
        }
        return new BrandSummary(brand, laptops.size(), total / laptops.size());
    }
}