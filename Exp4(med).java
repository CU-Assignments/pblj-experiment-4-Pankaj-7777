//Experiment 4 medium level part Pankaj(22bcs13842)
import java.util.*;

public class CardCollection {
    static HashMap<String, List<String>> cards = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void addCard() {
        System.out.print("Enter Card Symbol (Hearts, Spades, Diamonds, Clubs): ");
        String symbol = sc.nextLine();
        System.out.print("Enter Card Value (e.g., Ace, 2, King, etc.): ");
        String value = sc.nextLine();

        cards.putIfAbsent(symbol, new ArrayList<>());
        cards.get(symbol).add(value);

        System.out.println("Card added successfully!");
    }

    public static void displayCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards in the collection.");
            return;
        }
        for (Map.Entry<String, List<String>> entry : cards.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }

    public static void searchCards() {
        System.out.print("Enter Symbol to search (Hearts, Spades, Diamonds, Clubs): ");
        String symbol = sc.nextLine();

        if (cards.containsKey(symbol)) {
            System.out.println(symbol + " → " + cards.get(symbol));
        } else {
            System.out.println("No cards found for this symbol.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Card\n2. Display Cards\n3. Search by Symbol\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCard();
                case 2 -> displayCards();
                case 3 -> searchCards();
                case 4 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
