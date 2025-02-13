import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadder {
    private static final int BOARD_SIZE = 10;  // 10x10 board
    private static final int MAX_POSITION = 100;
    private static final HashMap<Integer, Integer> snakes = new HashMap<>();
    private static final HashMap<Integer, Integer> ladders = new HashMap<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        setupBoard();
        System.out.println(" Welcome to Snake and Ladder! \n");

        Scanner scanner = new Scanner(System.in);
        int player1 = 1, player2 = 1;
        boolean turn = true;

        while (player1 < MAX_POSITION && player2 < MAX_POSITION) {
            printBoard(player1, player2);
            System.out.println("\n" + (turn ? "Player 1" : "Player 2") + "'s turn. Press Enter to roll the dice...");
            scanner.nextLine();

            int diceRoll = rollDice();
            System.out.println(" Rolled a " + diceRoll);

            if (turn) {
                player1 = movePlayer(player1, diceRoll);
                System.out.println(" Player 1 moves to: " + player1);
                if (player1 == MAX_POSITION) {
                    System.out.println(" Player 1 wins! ");
                    break;
                }
            } else {
                player2 = movePlayer(player2, diceRoll);
                System.out.println(" Player 2 moves to: " + player2);
                if (player2 == MAX_POSITION) {
                    System.out.println(" Player 2 wins! ");
                    break;
                }
            }
            turn = !turn;
        }
        scanner.close();
    }

    private static void setupBoard() {
        // Define Snakes
        snakes.put(99, 7);
        snakes.put(92, 35);
        snakes.put(78, 40);
        snakes.put(65, 21);
        snakes.put(50, 10);

        // Define Ladders
        ladders.put(3, 22);
        ladders.put(6, 25);
        ladders.put(11, 41);
        ladders.put(20, 60);
        ladders.put(57, 95);
    }

    private static int rollDice() {
        return random.nextInt(6) + 1;
    }

    private static int movePlayer(int position, int diceRoll) {
        int newPosition = position + diceRoll;
        if (newPosition > MAX_POSITION) return position;

        if (snakes.containsKey(newPosition)) {
            System.out.println("Oh no! Bitten by a snake at " + newPosition);
            return snakes.get(newPosition);
        } else if (ladders.containsKey(newPosition)) {
            System.out.println("Climbed a ladder at " + newPosition);
            return ladders.get(newPosition);
        }
        return newPosition;
    }

    private static void printBoard(int p1, int p2) {
        System.out.println("\n Game Board:\n");
        for (int row = BOARD_SIZE; row > 0; row--) {
            for (int col = 1; col <= BOARD_SIZE; col++) {
                int position = (row - 1) * BOARD_SIZE + col;

                if (position == p1 && position == p2) {
                    System.out.print(" P1|P2 ");
                } else if (position == p1) {
                    System.out.print("  P1  ");
                } else if (position == p2) {
                    System.out.print("  P2  ");
                } else if (snakes.containsKey(position)) {
                    System.out.print("  \uD83D\uDC0D  ");
                } else if (ladders.containsKey(position)) {
                    System.out.print("  \uD83E\uDE9C  ");
                } else {
                    System.out.printf("%4d  ", position);
                }
            }
            System.out.println();
        }
        System.out.println("\n------------------------------------\n");
    }
}