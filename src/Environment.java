import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Terje on 19.03.2017.
 */
public class Environment {

    /**
     * Number 3.
     */
    private static final int THREE = 3;
    /**
     * Number 4.
     */
    private static final int FOUR = 4;
    /**
     * Number 5.
     */
    private static final int FIVE = 5;
    /**
     * Number 6.
     */
    private static final int SIX = 6;
    /**
     * Number 7.
     */
    private static final int SEVEN = 7;
    /**
     * Number 8.
     */
    private static final int EIGHT = 8;
    /**
     * Number 9.
     */
    private static final int NINE = 9;
    /**
     * Number 10.
     */
    private static final int TEN = 10;
    /**
     * List of all environments in the game.
     */
    private List<String> allEnvironments = Arrays.asList("Fire_env", "Earth_env", "Water_env", "Cosmic_env");
    /**
     * Name of the environment.
     */
    private String environment;
    /**
     * Generated ID for the environment.
     */
    private String id;
    /**
     * List of all runeIDs that are in the game.
     */
    private List<String> runeID;
    /**
     * Map where the Key is the runeID and value the specific object.
     */
    private Map<String, Rune> gameRunes;

    /**
     * Constructor for the environment class.
     * @param name ID of the environment.
     */
    public Environment(String name) {
        this.id = name;
        Random random = new Random();
        int value = random.nextInt(this.allEnvironments.size());
        this.environment = this.allEnvironments.get(value);
    }

    /**
     * Returns the type or name of the environment.
     * @return type or name
     */
    public String getEnvironment() {
        return this.environment;
    }

    /**
     * Returns the ID of the environment.
     * @return ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gives certain types of runes powerups based on the type of environment.
     * @param gameRunes All rune objects that were generated for the game
     */
    public void powerUps(Map<String, Rune> gameRunes) {
        this.runeID = new ArrayList<>(gameRunes.keySet());
        this.gameRunes = gameRunes;
        for (String name : this.runeID) {
            gameRunes.get(name).resetAttacks();
            gameRunes.get(name).resetWeakness();
            switch (this.environment) {
                default:
                    break;
                case "Fire_env":
                    envAttack(this.runeID, this.gameRunes, "Fire", "Blood", "Chaos");
                    break;
                case "Earth_env":
                    envAttack(this.runeID, this.gameRunes, "Nature", "Law", "");
                    envImmune(this.runeID, this.gameRunes, "Nature", "Law", "");
                    break;
                case "Water_env":
                    envImmune(this.runeID, this.gameRunes, "Water", "Air", "Body");
                    break;
                case "Cosmic_env":
                    envImmune(this.runeID, this.gameRunes, "Soul", "Mind", "Cosmic");
                    break;
            }
        }
    }

    /**
     * Adds new attacks to tree types of runes based on the environment type.
     * @param runeID List of all rune IDs that are in the game.
     * @param gameRunes Map or all rune objects in the game
     * @param rune1 first rune type
     * @param rune2 second rune type
     * @param rune3 third rune type
     */
    public void envAttack(List<String> runeID, Map<String, Rune> gameRunes, String rune1, String rune2, String rune3) {
        List<String> elements = Arrays.asList(rune1, rune2, rune3);
        for (String name : runeID) {
            if (elements.contains(gameRunes.get(name).getType())) {
                int[] attackIndex = {0, 1, 2, THREE, FOUR,  FIVE, SIX, SEVEN, EIGHT, NINE, TEN};
                gameRunes.get(name).resetAttacks();
                gameRunes.get(name).giveAttacks(attackIndex);
            }
        }
    }

    /**
     * Removes weaknesses for three types of runes based on the environment type.
     * @param runeID List of all rune IDs that are in the game.
     * @param gameRunes Map or all rune objects in the game
     * @param rune1 first rune type
     * @param rune2 second rune type
     * @param rune3 third rune type
     */
    public void envImmune(List<String> runeID, Map<String, Rune> gameRunes, String rune1, String rune2, String rune3) {
        List<String> elements = Arrays.asList(rune1, rune2, rune3);
        for (String name : runeID) {
            if (elements.contains(gameRunes.get(name).getType())) {
                gameRunes.get(name).resetWeakness();
                gameRunes.get(name).noWeaknesses();
            }
        }
    }
}
