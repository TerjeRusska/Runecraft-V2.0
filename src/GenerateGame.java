import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by Terje on 19.03.2017.
 */
public class GenerateGame {

    /**
     * Game player.
     */
    private Player player;
    /**
     * Computer for the game.
     */
    private Computer computer;
    /**
     * Map of rune objects for the game.
     */
    private Map<String, Rune> gameRunes;
    /**
     * Map of environment objects for the game.
     */
    private Map<String, Environment> gameEnvironments;
    /**
     * Constant number 5.
     */
    private static final int FIVE = 5;

    /**
     * Generates the runes, environments for the game.
     * @param rounds number of rounds in the game
     */
    public GenerateGame(int rounds) {
        this.player = new Player();
        this.computer = new Computer();
        this.gameRunes = generateRunes(rounds * 2);
        this.computer.setGameRunes(this.gameRunes);
        this.gameEnvironments = generateEnvironments(FIVE);
    }

    /**
     * Returns the generated rune objects of the game.
     * @return Map of rune objects
     */
    public Map<String, Rune> getGameRunes() {
        return this.gameRunes;
    }

    /**
     * Returns the player object of the game.
     * @return player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the computer object of the game.
     * @return computer
     */
    public Computer getComputer() {
        return this.computer;
    }

    /**
     * Returns the generated environmetn objects of the game.
     * @return Map of environment objects.
     */
    public Map<String, Environment> getGameEnvironments() {
        return this.gameEnvironments;
    }

    /**
     * Generates environments for the game.
     * @param number number of environments
     * @return Map of environment objects
     */
    public Map<String, Environment> generateEnvironments(int number) {
        List<String> environmentList = new ArrayList<>();
        IntStream.range(0, number).forEach(n -> {
                    environmentList.add("env" + n);
                }
        );
        Map<String, Environment> gameEnvironments = new HashMap<>();
        for (String name : environmentList) {
            gameEnvironments.put(name, new Environment(name));
        }
        return gameEnvironments;
    }

    /**
     * Generates the rune objects for the games and gives half to each player.
     * @param rounds Number of rounds in the game
     * @return Map of all rune objects
     */
    public Map<String, Rune> generateRunes(int rounds) {
        List<String> runeList = new ArrayList<>();
        IntStream.range(0, rounds).forEach(n -> runeList.add("rune" + n));
        Map<String, Rune> gameRunes = new HashMap<>();
        int counter = 1;
        for (String name : runeList) {
            gameRunes.put(name, new Rune(name));
            gameRunes.get(name).giveType();
            if (counter % 2 == 0) {
                if (this.computer.getCurrentRunes().contains("Essence")) {
                    gameRunes.get(name).giveType();
                    String falseRune = gameRunes.get(name).getType();
                    while (falseRune.equals("Essence")) {
                        gameRunes.get(name).giveType();
                        falseRune = gameRunes.get(name).getType();
                    }
                    this.computer.addRuneId(name);
                    this.computer.addRune(gameRunes.get(name).getType());
                    counter += 1;
                } else {
                    this.computer.addRuneId(name);
                    this.computer.addRune(gameRunes.get(name).getType());
                    counter += 1;
                }
            } else {
                if (this.player.getCurrentRunes().contains("Essence")) {
                    gameRunes.get(name).giveType();
                    String falseRune = gameRunes.get(name).getType();
                    while (falseRune.equals("Essence")) {
                        gameRunes.get(name).giveType();
                        falseRune = gameRunes.get(name).getType();
                    }
                    this.player.addRuneId(name);
                    this.player.addRune(gameRunes.get(name).getType());
                    counter += 1;
                } else {
                this.player.addRuneId(name);
                this.player.addRune(gameRunes.get(name).getType());
                counter += 1;
            }
        }
    }
        return gameRunes;
}

    /**
     * Starts the Battle.
     * @return Battle object
     */
    public Battle startBattle() {
        return new Battle(this.gameRunes, this.player, this.computer);
    }
}
