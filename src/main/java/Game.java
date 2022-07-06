import java.util.Scanner;

public class Game {
    private static final String TIE_BOUT_TEMPLATE = "Tie. You both input %s.";
    private static final String WIN_BOUT_TEMPLATE = "%s won bout with %s";
    private static final String WIN_GAME_TEMPLATE = "%s won with %d points";
    private static final int NUM_BOUTS = 3;
    private static final int WIN_THRESHOLD = (NUM_BOUTS / 2) + 1;

    private Player player1;
    private Player player2;

    private Player winner;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public int compare(Player.Move firstMove, Player.Move secondMove) {
        int subtractIdentifiers = firstMove.getIdentifier() - secondMove.getIdentifier();

        if (Math.abs(subtractIdentifiers) == 2) return subtractIdentifiers * -1;

        return subtractIdentifiers;
    }

    public Player play() {
        while (winner == null) {
            Player.Move player1Move = player1.getMove();
            Player.Move player2Move = player2.getMove();

            awardPoints(player1Move, player2Move);
            checkWinner();
        }

        System.out.println(String.format(WIN_GAME_TEMPLATE, winner.getName(), winner.getScore()));
        return winner;
    }

    private void awardPoints(Player.Move player1Move, Player.Move player2Move) {
        int compareIndicator = compare(player1Move, player2Move);

        if (compareIndicator > 0) {
            player1.incrementScore();
            System.out.println(String.format(WIN_BOUT_TEMPLATE, player1.getName(), player1Move.toString()));
        }
        else if (compareIndicator < 0) {
            player2.incrementScore();
            System.out.println(String.format(WIN_BOUT_TEMPLATE, player2.getName(), player1Move.toString()));
        }
        else {
            System.out.println(String.format(TIE_BOUT_TEMPLATE, player1Move.toString()));
        }
    }

    private void checkWinner() {
        if (player1.getScore() == WIN_THRESHOLD) winner = player1;
        else if (player2.getScore() == WIN_THRESHOLD) winner = player2;
    }
}
