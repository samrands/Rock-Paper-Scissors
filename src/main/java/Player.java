import java.util.Random;

public abstract class Player {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() { return score; }

    public void incrementScore() {
        score++;
    }

    public abstract Move getMove();

    public enum Move {
        ROCK(1, "r"),
        PAPER(2, "p"),
        SCISSORS(3, "s");

        private int identifier;
        private String inputMarker;
        Move(int identifier, String inputMarker) {
            this.identifier = identifier;
            this.inputMarker = inputMarker;
        }

        public static Move getMove(int identifier) {
            for (Move m : Move.values())
                if (m.identifier == identifier)
                    return m;
            return null;
        }

        public static Move getMoveFromInput(String inputMarker) {
            for (Move m : Move.values())
                if (m.inputMarker.equalsIgnoreCase(inputMarker))
                    return m;
            return null;
        }

        public int getIdentifier() {
            return identifier;
        }
    }
}
