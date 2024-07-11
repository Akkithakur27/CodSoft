import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int Start = 1;
        int End = 100;
        int attemptsLimit = 10; 

        int score = 0;
        boolean playAgain = true;

        System.out.println("**************  Welcome to the Number Guessing Game  **************************");

        while (playAgain) {
            int generatedNumber = random.nextInt(End - Start + 1) + Start;
            System.out.println("\n       I have picked a number between " + Start + " and " + End + ". guess it?");

            boolean guessedCorrectly = false;
            int attempts = 0;

            while (!guessedCorrectly && attempts < attemptsLimit) {
                System.out.println("\n Enter your guess (attempt " + (attempts + 1) + "/" + attemptsLimit + "): ");
                int userGuess = scanner.nextInt();
                scanner.nextLine(); 
                if (userGuess < generatedNumber) {
                    System.out.println("Too low. Try again!");
                } else if (userGuess > generatedNumber) {
                    System.out.println("Too high. Try again!");
                } else {
                    System.out.println("Congratulations! You've guessed the number " + generatedNumber + " correctly in " + (attempts + 1) + " attempts!");
                    guessedCorrectly = true;
                    score++; 
                }

                attempts++;
            }

            if (!guessedCorrectly) {
                System.out.println("\nSorry, you've used up all your attempts. The number was: " + generatedNumber);
            }

            
            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgainResponse = scanner.nextLine().toLowerCase();

            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nGame Over! Your final score is: " + score);
        scanner.close();
    }
}