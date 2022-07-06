import java.util.Scanner;

public class Main {
    private static Scanner userInput = new Scanner(System.in).useDelimiter("\\n");

    public static void main(String[] args) {

        System.out.println("Welcome to Rock-Paper-Scissors by Sam Rands\n");
        System.out.println("Please provide the number of humans that will be playing (0-2, default: 1)");

        int humanCount = userInput.nextInt();
        humanCount = validateHumanCount(humanCount);

        Player player1;
        Player player2;
        if (humanCount == 0) {
            player1 = new ComputerPlayer("computer1");
            player2 = new ComputerPlayer("computer2");
        } else if (humanCount == 1) {
            player1 = new ComputerPlayer("computer1");
            player2 = new HumanPlayer(getPlayerName());
        } else {
            player1 = new HumanPlayer(getPlayerName());
            player2 = new HumanPlayer(getPlayerName());
        }

        System.out.println("Now it's time to play!");
        Game thisGame = new Game(player1, player2);
        Player winner = thisGame.play();

        System.out.println(String.format("Congratulations to %s. Play again next time!", winner.getName()));
    }

    private static int validateHumanCount(int count) {
        if (count < 0 || count > 2) return 1;

        return count;
    }

    private static String getPlayerName() {
        System.out.println("Please provide player name:");
        return userInput.next();
    }
}
