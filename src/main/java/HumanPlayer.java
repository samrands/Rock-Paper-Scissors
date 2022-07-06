import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner inputScanner = new Scanner(System.in);
    private static final String MOVE_PROMPT = "%s, please enter a move ('r' for rock, 's' for scissors, 'p' for paper).";

    public HumanPlayer(String name) {
        super(name);
    }

    public Move getMove() {
        System.out.println(String.format(MOVE_PROMPT, getName()));

        String input = inputScanner.next();

        Move inputMove = Move.getMoveFromInput(input);

        if (inputMove != null) return inputMove;

        System.out.println(String.format("%s is not valid input. Please provide valid input.", input));
        return null;
    }

}
