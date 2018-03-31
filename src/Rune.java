import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Terje on 19.03.2017.
 */
public class Rune {

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
     * List of all possible elements in the game.
     */
    private List<String> allTypes = Arrays.asList(
            "Fire", "Nature", "Law", "Blood",
            "Water", "Air", "Mind", "Chaos",
            "Soul", "Body", "Cosmic", "Essence");
    /**
     * List of elements that can be attacked.
     */
    private List<String> attacks = new ArrayList<>();
    /**
     * List of elements that overpower.
     */
    private List<String> weaknesses = new ArrayList<>();
    /**
     * Generated ID for the element.
     */
    private String id;
    /**
     * Randomly chosen type from the list of all elements.
     */
    private String type;

    /**
     * Constructor for the rune class.
     * @param name ID of the rune
     */
    public Rune(String name) {
        this.id = name;
    }

    /**
     * Returns the type of the rune.
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the ID of the rune.
     * @return ID
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the list of attacks of the rune.
     * @return list of attacks.
     */
    public List<String> getAttacks() {
        return this.attacks;
    }

    /**
     * Return the list of weaknesses of the rune.
     * @return list of weaknesses
     */
    public List<String> getWeaknesses() {
        return this.weaknesses;
    }

    /**
     * Randomly generates a type for the rune.
     */
    public void giveType() {
        Random random = new Random();
        int value = random.nextInt(this.allTypes.size());
        this.type = this.allTypes.get(value);
        resetAttacks();
        resetWeakness();
        typeAttack();
        typeWeakness();
    }

    /**
     * Generates a list of attacks based on the type of the rune.
     */
    public void typeAttack() {
        Map<String, int[]> attackDictionary = new HashMap<>();
        int[] intsFire = {SIX, SEVEN, EIGHT, NINE, TEN};
        int[] intsNature = {SEVEN, EIGHT, NINE, TEN, 0};
        int[] intsLaw = {EIGHT, NINE, TEN, 0, 1};
        int[] intsBlood = {NINE, TEN, 0, 1, 2};
        int[] intsWater = {TEN, 0, 1, 2, THREE};
        int[] intsAir = {0, 1, 2, THREE, FOUR};
        int[] intsMind = {1, 2, THREE, FOUR, FIVE};
        int[] intsChaos = {2, THREE, FOUR, FIVE, SIX};
        int[] intsSoul = {THREE, FOUR, FIVE, SIX, SEVEN};
        int[] intsBody = {FOUR, FIVE, SIX, SEVEN, EIGHT};
        int[] intsCosmic = {FIVE, SIX, SEVEN, EIGHT, NINE};
        int[] intsEssence = {0, 1, 2, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN};
        attackDictionary.put("Fire", intsFire);
        attackDictionary.put("Nature", intsNature);
        attackDictionary.put("Law", intsLaw);
        attackDictionary.put("Blood", intsBlood);
        attackDictionary.put("Water", intsWater);
        attackDictionary.put("Air", intsAir);
        attackDictionary.put("Mind", intsMind);
        attackDictionary.put("Chaos", intsChaos);
        attackDictionary.put("Soul", intsSoul);
        attackDictionary.put("Body", intsBody);
        attackDictionary.put("Cosmic", intsCosmic);
        attackDictionary.put("Essence", intsEssence);
        int[] attackIndex = attackDictionary.get(this.type);
        for (int i : attackIndex) {
            if (!this.attacks.contains(this.allTypes.get(i))) {
                this.attacks.add(this.allTypes.get(i));
            }
        }
    }

    /**
     * Generates a list of weaknesses based on the type of the rune.
     */
    public void typeWeakness() {
        Map<String, int[]> weaknessDictionary = new HashMap<>();
        int[] intsFire = {1, 2, THREE, FOUR, FIVE};
        int[] intsNature = {2, THREE, FOUR, FIVE, SIX};
        int[] intsLaw = {THREE, FOUR, FIVE, SIX, SEVEN};
        int[] intsBlood = {FOUR, FIVE, SIX, SEVEN, EIGHT};
        int[] intsWater = {FIVE, SIX, SEVEN, EIGHT, NINE};
        int[] intsAir = {SIX, SEVEN, EIGHT, NINE, TEN};
        int[] intsMind = {SEVEN, EIGHT, NINE, TEN, 0};
        int[] intsChaos = {EIGHT, NINE, TEN, 0, 1};
        int[] intsSoul = {NINE, TEN, 0, 1, 2};
        int[] intsBody = {TEN, 0, 1, 2, THREE};
        int[] intsCosmic = {0, 1, 2, THREE, FOUR};
        int[] intsEssence = {};
        weaknessDictionary.put("Fire", intsFire);
        weaknessDictionary.put("Nature", intsNature);
        weaknessDictionary.put("Law", intsLaw);
        weaknessDictionary.put("Blood", intsBlood);
        weaknessDictionary.put("Water", intsWater);
        weaknessDictionary.put("Air", intsAir);
        weaknessDictionary.put("Mind", intsMind);
        weaknessDictionary.put("Chaos", intsChaos);
        weaknessDictionary.put("Soul", intsSoul);
        weaknessDictionary.put("Body", intsBody);
        weaknessDictionary.put("Cosmic", intsCosmic);
        weaknessDictionary.put("Essence", intsEssence);
        int[] weakIndex = weaknessDictionary.get(this.type);
        for (int i : weakIndex) {
            if (!this.weaknesses.contains(this.allTypes.get(i))) {
                this.weaknesses.add(this.allTypes.get(i));
            }
        }
    }

    /**
     * Adds attacks to the list of attacks.
     * @param attackIndex Element index from the list of all elements.
     */
    public void giveAttacks(int[] attackIndex) {
        for (int i : attackIndex) {
            if (!this.attacks.contains(this.allTypes.get(i))) {
                this.attacks.add(this.allTypes.get(i));
            }
        }
    }

    /**
     * Sets the list of attacks back to based on the type.
     */
    public void resetAttacks() {
        this.attacks.clear();
        typeAttack();
    }

    /**
     * Sets the list of weaknesses back to based on the type.
     */
    public void resetWeakness() {
        this.weaknesses.clear();
        typeWeakness();
    }

    /**
     * Removes all weaknesses of the rune.
     */
    public void noWeaknesses() {
        this.weaknesses.clear();
    }
}
