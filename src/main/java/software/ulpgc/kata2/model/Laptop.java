package software.ulpgc.kata2.model;

public record Laptop(String model, String brand, int ram, int storage, double price) {
    public Laptop {
        if (model.isBlank() || brand.isBlank()) {
            throw new IllegalArgumentException("El modelo y la marca no pueden estar vacíos");
        }

        if (ram <= 0 || storage <= 0) {
            throw new IllegalArgumentException("La RAM y el almacenamiento deben ser positivos");
        }

        if (price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }


    }
}
