import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random = new Random();
    private static final int MOVE_COUNT = 3;

    public ComputerPlayer(String name) {
        super(name);
    }

    public Move getMove() {
        // random.nextInt(int a) gets an int between 0 and a (inclusive). We're looking for a number in the set 1..moveCount,
        // so I use random.nextInt(moveCount - 1) to get 0..moveCount and bump it up by 1 for 1..moveCount.
        return Move.getMove(random.nextInt(MOVE_COUNT - 1) + 1);
    }
}
