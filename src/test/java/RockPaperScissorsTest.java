import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class RockPaperScissorsTest {
    private Game game;
    private Player otherComputer;
    private Player computer;
    private Player.Move rock = Player.Move.ROCK;
    private Player.Move paper = Player.Move.PAPER;
    private Player.Move scissors = Player.Move.SCISSORS;
    private InputStream sysInBackup;
    private PrintStream sysOutBackup;

    @Before
    public void setUp() {
        computer = new ComputerPlayer("test1");
        otherComputer = new ComputerPlayer("test2");
        game = new Game(computer, otherComputer);
        sysInBackup = System.in; // backup System.in to restore it later
        sysOutBackup = System.out;
    }

    @After
    public void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test
    public void testComputerCanPlay() {
        Player.Move move = computer.getMove();
        assertTrue(move != null);
    }

    @Test
    public void testMovesAreSomewhatRandom() {
        int n = 100;
        int threshold = 70;

        int[] moveCounts = calculateOddsOfMoves(n);
        for (int moveCount : moveCounts) {
            assertTrue(moveCount <= threshold);
        }
    }

    @Test
    public void testRockBeatsScissors() {
        assertTrue(game.compare(rock, scissors) > 0);
        assertTrue(game.compare(scissors, rock) < 0);
    }

    @Test
    public void testScissorsBeatsPaper() {
        assertTrue(game.compare(scissors, paper) > 0);
        assertTrue(game.compare(paper, scissors) < 0);
    }

    @Test
    public void testPaperBeatsRock() {
        assertTrue(game.compare(rock, paper) < 0);
        assertTrue(game.compare(paper, rock) > 0);
    }

    @Test
    public void testSameMoveCancelsOut() {
        assertTrue(game.compare(rock, rock) == 0);
        assertTrue(game.compare(paper, paper) == 0);
        assertTrue(game.compare(scissors, scissors) == 0);
    }

    @Test
    public void testUserInputCorrespondsToMove() {
        ByteArrayInputStream in = new ByteArrayInputStream("s".getBytes());
        System.setIn(in);
        // Thanks to KrzyH (https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input)

        Player humanAfterSettingSystemIn = new HumanPlayer("human");

        Player.Move inputMove = humanAfterSettingSystemIn.getMove();

        assertEquals(inputMove, Player.Move.SCISSORS);
    }

    @Test
    public void testBadUserInput() {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        Player humanAfterSettingSystemIn = new HumanPlayer("human");

        Player.Move inputMove = humanAfterSettingSystemIn.getMove();

        assertNull(inputMove);
        String systemOut = StringUtils.trim(out.toString());
        assertTrue(StringUtils.endsWith(systemOut, "Please provide valid input."));
    }

    @Test
    public void testTwoComputers() {
        Player winner = game.play();
        assertEquals(2, winner.getScore());
    }

    private int[] calculateOddsOfMoves(int n) {
        int[] oddsOfEachMove = new int[3];
        for (int i = 0; i < n; i++) {
            Player.Move thisMove = computer.getMove();
            oddsOfEachMove[thisMove.getIdentifier() - 1]++;
        }

        return oddsOfEachMove;
    }


}
