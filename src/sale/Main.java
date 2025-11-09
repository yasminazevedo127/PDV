package sale;
import product.*;

import java.util.Scanner;


public class Main {
    private static ProductRepository products = new ProductRepository();
    private static SaleRepository sales = new SaleRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        while(true) {
            System.out.println("Digite a opção que deseja: ");
            System.out.println("1- Registrar produto");
            System.out.println("2- Listar produtos");
            System.out.println("3- Adicionar produto no estoque");
            System.out.println("4- Registrar venda");
            System.out.println("5- Listar vendas");
            System.out.println("6- Relatório");
            System.out.println("7- sair");

            opcao = Integer.parseInt(scanner.nextLine());
            if(opcao == 1) {
                System.out.println("Digite o nome do produto:");
                String nome = scanner.nextLine();

                System.out.println("Digite o codigo do produto");
                int code = inputInt(scanner);

                System.out.println("Digite o preço do produto");
                double price = inputDouble(scanner);

                products.registerProduct(nome, code, price);
                System.out.println("");
            }
            else if(opcao == 2) {
                products.listProducts();
                System.out.println("");
            }
            else if(opcao == 3) {
                while (true) {
                    System.out.println("Digite o codigo do produto");
                    int code = inputInt(scanner);
                    if (products.findProduct(code) == null) {
                        System.out.println("Digite um produto válido");
                        continue;
                    }
                    System.out.println("Digite a quantidade para adicionar ao estoque");
                    int quantity = inputInt(scanner);

                    products.addStock(code, quantity);
                    System.out.println("");
                    break;
                }
            }
            else if(opcao == 4) {
                while (true) {
                    System.out.println("Escolha o seu local de compra");
                    System.out.println("1- Loja");
                    System.out.println("2- Web");
                    int code = inputInt(scanner);
                    SaleType tipoDeVenda = (code == 1) ? SaleType.STORE : SaleType.WEB;
                    String address = null;
                    if (tipoDeVenda == SaleType.WEB) {
                        System.out.println("Digite seu endereço");
                        address = scanner.nextLine();
                    }
                    sales.registerSale(tipoDeVenda, address, products, scanner);
                    break;
                }
            }
            else if(opcao == 5) {
                sales.listSales();
            }
            else if(opcao == 6) {
                sales.listInfoForEachSale();

                System.out.println("Total de itens vendidos");
                System.out.println(sales.totalItemsSold());

                System.out.println("Valor total vendido");
                System.out.println(sales.totalPrice());
            }
            else if(opcao == 7) {
                scanner.close();
                System.out.println("Encerrando programa");
                System.exit(0);
            }
            else {
                System.out.println("Escolha uma opção existente");
            }

        }

    }

    public static Integer convertToInt(String x) {
        try {
            int resultado = Integer.parseInt(x);
            return resultado;
        }
        catch(NumberFormatException e){
            return null;
        }

    }

    public static int inputInt(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine();
            Integer resultado = convertToInt(input);
            if(resultado == null) {
                System.out.println("Digite um valor inteiro");
                continue;
            }
            return resultado;
        }
    }

    public static Double convertToDouble (String x) {
        try {
            double resultado = Double.parseDouble(x);
            return resultado;
        }
        catch(NumberFormatException e){
            return null;
        }

    }

    public static double inputDouble(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine();
            Double resultado = convertToDouble(input);
            if(resultado == null) {
                System.out.println("Digite um valor real");
                continue;
            }
            return resultado;
        }
    }
}
