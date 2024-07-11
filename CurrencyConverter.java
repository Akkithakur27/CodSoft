import java.io.BufferedReader;
import java.io.IOException;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;


import java.net.URL;
import java.net.URLEncoder;


import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        System.out.print("Enter the amount you want to convert: ");
        double amount = scanner.nextDouble();

        System.out.print("Enter the base currency code (e.g., USD, EUR, INR): ");
        String fromCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency code (e.g., USD, EUR, INR): ");
        String toCurrency = scanner.next().toUpperCase();

        scanner.close();

        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

        if (convertedAmount != -1) {
            System.out.printf("%.2f %s is equal to %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
        } else {
            System.out.println("Error converting currency. Please check your inputs.");
        }
    }

    @SuppressWarnings("deprecation")
    private static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        try {
            // Constructing URL for API request
            String urlStr = "https://www.calculator.net/currency-calculator.html?evalue=" + amount + "&efrom=" + URLEncoder.encode(fromCurrency, StandardCharsets.UTF_8) + "&eto=" + URLEncoder.encode(toCurrency, StandardCharsets.UTF_8);
            URL url = new URL(urlStr);

           
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

         
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String result = response.toString();
            String[] splitResult = result.split("<div id=\"currency\" class=\"cc-result\">");
            String[] splitResult2 = splitResult[1].split("<br>");
            String convertedValue = splitResult2[0].trim();
            return Double.parseDouble(convertedValue.replaceAll(",", ""));
        } catch (IOException e) {
            System.out.println("Error making HTTP request: " + e.getMessage());
            return -1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing response: " + e.getMessage());
            return -1;
        }
    }
}