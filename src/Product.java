import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String name;
    private Double price;
    private String description;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Product() {}

    public Product(List<String> list) {
        this.name = list.get(0);
        this.price = Double.parseDouble(list.get(1));
        this.description = list.get(2);
    }

    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static Product getProductFromConsole() {
        Scanner sc = new Scanner(System.in);
        String name;
        String price;
        String desc;
        boolean err = false;
        do {
            if(err) {
                System.out.println(ANSI_RED + "\nThe product was entered incorrectly, let's try again" + ANSI_RESET);
            }
            System.out.print(ANSI_CYAN + "Enter product name (up to 20 characters): ");
            name = sc.nextLine();
            System.out.print("Enter the cost of the product: ");
            price = sc.nextLine();
            System.out.print("Enter product description (up to 50 characters): " + ANSI_RESET);
            desc = sc.nextLine();
            err = true;
        } while(name.length() > 20 || !isNumber(price) || desc.length() > 50);
        System.out.println();
        return new Product(name, Double.parseDouble(price), desc);
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public boolean findSubstr(String s) {
        return (name.contains(s) || description.contains(s));
    }

    @Override
    public String toString() {
        return name + ", " + price + ", " + description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || hashCode() != o.hashCode()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }
}
