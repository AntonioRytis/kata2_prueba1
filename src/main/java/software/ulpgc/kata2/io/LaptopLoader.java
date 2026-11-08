package software.ulpgc.kata2.io;

import software.ulpgc.kata2.model.Laptop;

import java.io.InputStream;
import  java.util.List;

public interface LaptopLoader {
    List<Laptop> load(InputStream inputStream);
}
