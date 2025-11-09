package sale;

import product.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ProductRepository products = new ProductRepository();
    private static SaleRepository sales = new SaleRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            showMenu();

            option = Integer.parseInt(scanner.nextLine());
            switch (option) {

                case 1:
                    System.out.println("Enter product name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter product code:");
                    int code = inputInt(scanner);

                    System.out.println("Enter product price:");
                    double price = inputDouble(scanner);

                    products.registerProduct(name, code, price);
                    System.out.println();
                    break;

                case 2:
                    products.listProducts();
                    System.out.println();
                    break;

                case 3:
                    while (true) {
                        System.out.println("Enter product code:");
                        int code3 = inputInt(scanner);

                        if (products.findProduct(code3) == null) {
                            System.out.println("Please enter a valid product.");
                            continue;
                        }

                        System.out.println("Enter quantity to add to stock:");
                        int quantity = inputInt(scanner);

                        products.addStock(code3, quantity);
                        System.out.println();
                        break;
                    }
                    break;

                case 4:
                    SaleType saleType;
                    String address = null;
                    ArrayList<Product> wishList = new ArrayList<>();
                    ArrayList<Integer> qntList = new ArrayList<>();

                    while (true) {
                        System.out.println("Choose purchase location:");
                        System.out.println("1 - Store");
                        System.out.println("2 - Web");
                        int location = inputInt(scanner);

                        saleType = (location == 1) ? SaleType.STORE : SaleType.WEB;

                        if (saleType == SaleType.WEB) {
                            System.out.println("Enter your address:");
                            address = scanner.nextLine();
                        }
                        break;
                    }

                    while (true) {
                        products.listProducts();
                        System.out.println("Enter product code or 0 to complete sale:");
                        int code4 = inputInt(scanner);

                        if (code4 == 0) break;

                        Product p = products.findProduct(code4);
                        if (p == null) {
                            System.out.println("Product not found.");
                            continue;
                        }

                        System.out.println("Enter quantity:");
                        int qnt = inputInt(scanner);

                        wishList.add(p);
                        qntList.add(qnt);

                        for (int i = 0; i < wishList.size(); i++) {
                            System.out.println("\t" + wishList.get(i).getName() + " - x" + qntList.get(i));
                        }
                    }

                    SaleRequest saleReq = new SaleRequest(saleType, address, wishList, qntList);
                    sales.registerSale(saleReq, products);
                    break;

                case 5:
                    sales.listSales();
                    break;

                case 6:
                    sales.listInfoForEachSale();
                    System.out.println("Total items sold: " + sales.totalItemsSold());
                    System.out.println("Total sales value: $" + sales.totalPrice());
                    break;

                case 7:
                    scanner.close();
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Choose a valid option.");
                    break;
            }
        }
    }

    // ---------- Utility Methods ----------

    public static Integer convertToInt(String x) {
        try {
            return Integer.parseInt(x);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static int inputInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            Integer result = convertToInt(input);
            if (result == null) {
                System.out.println("Enter an integer value.");
                continue;
            }
            return result;
        }
    }

    public static Double convertToDouble(String x) {
        try {
            return Double.parseDouble(x);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static double inputDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            Double result = convertToDouble(input);
            if (result == null) {
                System.out.println("Enter a real number value.");
                continue;
            }
            return result;
        }
    }

    public static void showMenu() {
        System.out.println("Select an option:");
        System.out.println("1 - Register product");
        System.out.println("2 - List products");
        System.out.println("3 - Add product stock");
        System.out.println("4 - Register sale");
        System.out.println("5 - List sales");
        System.out.println("6 - Report");
        System.out.println("7 - Exit");
    }
}