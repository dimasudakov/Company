import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Manager {
    Company company;

    // Цвета для красивого вывода
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";


    public void run(Company company) {
        this.company = company;
        System.out.println("You have launched " + company.getName() + " store management");

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println(ANSI_GREEN + "\nSelect an action:" + ANSI_RESET);
            System.out.println("1) Add product");
            System.out.println("2) Find product");
            System.out.println("3) Show all products");
            System.out.println("4) Finish work with company");
            System.out.print(ANSI_CYAN + "Enter action number: " + ANSI_RESET);

            int command;
            while(true) {
                String userCommand = sc.nextLine();
                command = getCorrectCommand(userCommand);
                if (command == 0) {
                    System.out.print("Invalid command, please re-enter: ");
                } else {
                    break;
                }
            }
            System.out.println();

            if(command == 4) {
                System.out.println("Goodbye!");
                break;
            }

            switch(command) {
                case 1:
                    company.addProduct(Product.getProductFromConsole());
                    break;
                case 2:
                    System.out.print("Enter a search string: ");
                    System.out.println(ANSI_YELLOW + company.findProduct(sc.nextLine()) + ANSI_RESET);
                    System.out.print(ANSI_CYAN + "Press enter to continue: ");
                    sc.nextLine();
                    break;
                case 3:
                    showAllProducts();
            }
        }
    }

    private void showAllProducts() {
        List<Product> products = company.getAllProducts();
        System.out.println(ANSI_YELLOW + "All company products of " + company.getName() + ":" + ANSI_RESET);

        try (Scanner scanner = new Scanner(new File(company.getPATH()));) {
            while (scanner.hasNextLine()) {
                Product p = Company.getProductFromLine(scanner.nextLine());
                products.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        for(int i = 0; i < 60; i++) System.out.print(ANSI_RED + '-');
        System.out.println(ANSI_RESET);

        for(Product p : products) {
            System.out.format("%-25s%-20s%-55s", p.getName(), p.getPrice(), p.getDescription());
            System.out.println();
        }

        for(int i = 0; i < 60; i++) System.out.print(ANSI_RED + '-');
        System.out.print(ANSI_CYAN + "\nPress enter to continue: ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    private int getCorrectCommand(String userCommand) {
        int n = 0;
        try {
            n = Integer.parseInt(userCommand);
        } catch (Exception e) {
            return 0;
        }
        if(n < 1 || n > 4) return 0;
        return n;
    }
}
