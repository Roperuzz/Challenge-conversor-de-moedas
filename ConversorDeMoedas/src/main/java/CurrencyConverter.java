import com.google.gson.*;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        System.out.println("Bem-vindo ao Conversor de Moedas!");
        System.out.println("Escolha uma opção de conversão:");
        System.out.println("1. USD para BRL");
        System.out.println("2. BRL para USD");
        System.out.println("3. EUR para BRL");
        System.out.println("4. BRL para EUR");
        System.out.println("5. USD para EUR");
        System.out.println("6. EUR para USD");

        System.out.print("Digite o número da sua opção: ");
        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > 6) {
            System.out.println("Opção inválida. Por favor, escolha entre 1 e 6.");
            return;
        }

        System.out.print("Digite o valor a ser convertido: ");
        double valor = scanner.nextDouble();

        try {
            String json = APIClient.getRates();
            ExchangeRates exchangeRates = gson.fromJson(json, ExchangeRates.class);

            double resultado = 0;

            switch (opcao) {
                case 1: // USD para BRL
                    resultado = valor * (1 / exchangeRates.getRate("USD", "BRL"));
                    break;
                case 2: // BRL para USD
                    resultado = valor * exchangeRates.getRate("USD", "BRL");
                    break;
                case 3: // EUR para BRL
                    resultado = valor * (1 / exchangeRates.getRate("EUR", "BRL"));
                    break;
                case 4: // BRL para EUR
                    resultado = valor * exchangeRates.getRate("EUR", "BRL");
                    break;
                case 5: // USD para EUR
                    double usdToBrl = exchangeRates.getRate("USD", "BRL");
                    double eurToBrl = exchangeRates.getRate("EUR", "BRL");
                    resultado = valor * (eurToBrl / usdToBrl);
                    break;
                case 6: // EUR para USD
                    usdToBrl = exchangeRates.getRate("USD", "BRL");
                    eurToBrl = exchangeRates.getRate("EUR", "BRL");
                    resultado = valor * (usdToBrl / eurToBrl);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    return;
            }

            System.out.printf("O valor convertido é: %.2f\n", resultado);

        } catch (Exception e) {
            System.out.println("Erro ao obter as taxas de câmbio: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

