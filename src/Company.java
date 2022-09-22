import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Company {
    private final String PATH = "Data.csv";

    private String name;
    private int numberOfProducts = 0;

    public Company(String name) {
        try {
            // Очищаем содержимое файла
            FileWriter object = new FileWriter(PATH, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    public boolean addProduct(Product product) {
        try (FileWriter writer = new FileWriter(new File(PATH), true)) {

            writer.write(product.toString() + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(PATH));) {
            while (scanner.hasNextLine()) {
                Product p = getProductFromLine(scanner.nextLine());

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    public String findProduct(String s) {
        boolean succes = false;
        Product product = new Product();
        try (Scanner scanner = new Scanner(new File(PATH));) {
            while (scanner.hasNextLine()) {
                product = getProductFromLine(scanner.nextLine());
                if(product.findSubstr(s)) {
                    succes = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        if(!succes) {
            return "Product not found";
        } else {
            return "Product: " + product.toString();
        }
    }

    public static Product getProductFromLine(String line) {
        List<String> str = new ArrayList<>();
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(", ");
            while (scanner.hasNext()) {
                str.add(scanner.next());
            }
        }
        return new Product(str);
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPATH() {
        return PATH;
    }
}
