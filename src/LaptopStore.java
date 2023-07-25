import java.util.*;

class Laptop {
    private final String brand;
    private final int ramSize;
    private final int storageSize;
    private final String operatingSystem;
    private final String color;

    public Laptop(String brand, int ramSize, int storageSize, String operatingSystem, String color) {
        this.brand = brand;
        this.ramSize = ramSize;
        this.storageSize = storageSize;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    // Геттеры для доступа к полям
    public String getBrand() {
        return brand;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Brand: " + brand +
                ", RAM: " + ramSize + "GB" +
                ", Storage: " + storageSize + "GB" +
                ", OS: " + operatingSystem +
                ", Color: " + color;
    }
}

public class LaptopStore {
    public static void main(String[] args) {
        Set<Laptop> laptops = createLaptops();
        filterLaptops(laptops);
    }

    private static Set<Laptop> createLaptops() {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 8, 512, "Windows 10", "Silver"));
        laptops.add(new Laptop("Lenovo", 16, 256, "Windows 11", "Black"));
        laptops.add(new Laptop("HP", 8, 256, "Windows 10", "White"));
        laptops.add(new Laptop("Asus", 12, 512, "Windows 10", "Gray"));
        laptops.add(new Laptop("Apple", 16, 512, "macOS", "Silver"));
        return laptops;
    }

    private static void filterLaptops(Set<Laptop> laptops) {
        Map<Integer, String> criteria = new HashMap<>();
        criteria.put(1, "RAM");
        criteria.put(2, "Storage");
        criteria.put(3, "Operating System");
        criteria.put(4, "Color");

        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> filters = new HashMap<>();

        for (Map.Entry<Integer, String> entry : criteria.entrySet()) {
            System.out.println("Введите минимальное значение для " + entry.getValue() + ":");
            if (scanner.hasNextInt()) {
                filters.put(entry.getKey(), scanner.nextInt());
            }
        }

        System.out.println("Результаты фильтрации:");
        for (Laptop laptop : laptops) {
            if (checkFilters(laptop, filters)) {
                System.out.println(laptop);
            }
        }
    }

    private static boolean checkFilters(Laptop laptop, Map<Integer, Object> filters) {
        if (filters.containsKey(1) && laptop.getRamSize() < (int) filters.get(1)) {
            return false;
        }
        if (filters.containsKey(2) && laptop.getStorageSize() < (int) filters.get(2)) {
            return false;
        }
        if (filters.containsKey(3) && !laptop.getOperatingSystem().equalsIgnoreCase((String) filters.get(3))) {
            return false;
        }
        if (filters.containsKey(4) && !laptop.getColor().equalsIgnoreCase((String) filters.get(4))) {
            return false;
        }
        return true;
    }
}