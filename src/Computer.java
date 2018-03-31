import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Terje on 19.03.2017.
 */
public class Computer {

    /**
     * List of current runes the computer has.
     */
    private List<String> currentRunes = new ArrayList<>();
    /**
     * List of RuneIDs the computer has.
     */
    private List<String> runeId = new ArrayList<>();
    /**
     * Score of the computer.
     */
    private int score = 0;
    /**
     * Computer chosen rune.
     */
    private String chosenRune;
    /**
     * Current environment of the battle.
     */
    private Environment currentEnvironment;
    /**
     * Has player chosen the rune essence.
     */
    boolean playerEssence = false;
    /**
     * Map of generated runes for the game.
     */
    private Map<String, Rune> gameRunes;
    /**
     * Constant number 3.
     */
    private static final int THREE = 3;

    /**
     * Adds a point to the score.
     */
    public void addScore() {
        this.score += 1;
    }

    /**
     * Returns the final score of the computer.
     * @return score
     */
    public int finalScore() {
        return this.score;
    }

    /**
     * Returns a list of current runes the computer has.
     * @return list of current runes
     */
    public List<String> getCurrentRunes() {
        return this.currentRunes;
    }

    /**
     * Returns a list of current runeIDs the computer has.
     * @return List of computer runeIDs.
     */
    public List<String> getRuneId() {
        return this.runeId;
    }

    /**
     * Adds a RuneID to the computers list (Can not have multiple sames).
     * @param runeId RuneID to be added
     */
    public void addRuneId(String runeId) {
        this.runeId.add(runeId);
    }

    /**
     * Adds a rune type to the list of current runes of the computer (Can have multiple sames).
     * @param runeType Rune type
     */
    public void addRune(String runeType) {
        this.currentRunes.add(runeType);
    }

    /**
     * Return the rune the computer has chosen.
     * @return computer chosen rune
     */
    public String getChosenRune() {
        return this.chosenRune;
    }

    /**
     * Gets the current environment type.
     * @param environment current environment object.
     */
    public void identifyEnvironment(Environment environment) {
        this.currentEnvironment = environment;
    }

    /**
     * Checks if the player has chosen the rune Essence.
     * @param rune Player chosen rune
     */
    public void identifyPlayerRune(Rune rune) {
        if (rune.getType().equals("Essence")) {
            this.playerEssence = true;
        }
    }

    /**
     * List of generated rune objects for the game.
     * @param gameRunes rune objects map.
     */
    public void setGameRunes(Map<String, Rune> gameRunes) {
        this.gameRunes = gameRunes;
    }

    /**
     * Randomly chooses a rune for the round.
     * @return chosen rune
     */
    public String choseRune() {
        if (this.playerEssence && this.currentRunes.contains("Essence")) {
            for (int i = 0; i < this.runeId.size(); i++) {
                if (this.gameRunes.get(this.runeId.get(i)).getType().equals("Essence")) {
                    this.chosenRune = this.runeId.get(i);
                    this.runeId.remove(i);
                    this.playerEssence = false;
                    return this.chosenRune;
                }
            }
        }
        switch (this.currentEnvironment.getEnvironment()) {
            default:
                break;
            case "Fire_env":
                for (int i = 0; i < this.runeId.size() && i < THREE; i++) {
                    Random random = new Random();
                    int value = random.nextInt(this.runeId.size());
                    String type = this.gameRunes.get(this.runeId.get(value)).getType();
                    if (type.equals("Fire") || type.equals("Blood") || type.equals("Chaos")) {
                        this.chosenRune = this.runeId.get(value);
                        this.runeId.remove(value);
                        return this.chosenRune;
                    }
                }
                break;
            case "Earth_env":
                for (int i = 0; i < this.runeId.size() && i < THREE; i++) {
                    Random random = new Random();
                    int value = random.nextInt(this.runeId.size());
                    String type = this.gameRunes.get(this.runeId.get(value)).getType();
                    if (type.equals("Fire") || type.equals("Nature") || type.equals("Law")) {
                        this.chosenRune = this.runeId.get(value);
                        this.runeId.remove(value);
                        return this.chosenRune;
                    }
                }
                break;
            case "Water_env":
                for (int i = 0; i < this.runeId.size() && i < THREE; i++) {
                    Random random = new Random();
                    int value = random.nextInt(this.runeId.size());
                    String type = this.gameRunes.get(this.runeId.get(value)).getType();
                    if (type.equals("Body") || type.equals("Water") || type.equals("Air")) {
                        this.chosenRune = this.runeId.get(value);
                        this.runeId.remove(value);
                        return this.chosenRune;
                    }
                }
                break;
            case "Cosmic_env":
                for (int i = 0; i < this.runeId.size() && i < THREE; i++) {
                    Random random = new Random();
                    int value = random.nextInt(this.runeId.size());
                    String type = this.gameRunes.get(this.runeId.get(value)).getType();
                    if (type.equals("Soul") || type.equals("Mind") || type.equals("Cosmic")) {
                        this.chosenRune = this.runeId.get(value);
                        this.runeId.remove(value);
                        return this.chosenRune;
                    }
                }
                break;
        }
        Random random = new Random();
        int value = random.nextInt(this.runeId.size());
        String chosenRune = this.runeId.get(value);
        this.chosenRune = chosenRune;
        this.runeId.remove(value);
        return chosenRune;
    }
}
