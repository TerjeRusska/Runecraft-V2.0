import java.util.ArrayList;
import java.util.List;

/**
 * Created by Terje on 19.03.2017.
 */
public class Player {

    /**
     * List of rune names the person has.
     */
    private List<String> currentRunes = new ArrayList<>();
    /**
     * List of runeIDs that the person has.
     */
    private List<String> runeId = new ArrayList<>();
    /**
     * Player score.
     */
    private int score = 0;
    /**
     * Player chosen rune.
     */
    private String chosenRune;

    /**
     * Adds a point to the score.
     */
    public void addScore() {
        this.score += 1;
    }

    /**
     * Returns a list of current runes of the player.
     * @return current runes
     */
    public List<String> getCurrentRunes() {
        return currentRunes;
    }

    /**
     * Returns the final score.
     * @return score
     */
    public int finalScore() {
        return this.score;
    }

    /**
     * Returns the runeID the player last picked.
     * @return Player chosen runeID.
     */
    public List<String> getRuneId() {
        return this.runeId;
    }

    /**
     * Adds a RuneID to the players list of runes.
     * @param runeId RuneID to be added (can not have multiple sames)
     */
    public void addRuneId(String runeId) {
        this.runeId.add(runeId);
    }

    /**
     * Adds the name of the rune to the players list of current runes.
     * @param runeType Type of rune (can have multiple sames)
     */
    public void addRune(String runeType) {
        this.currentRunes.add(runeType);
    }

    /**
     * Player chooses a rune.
     * @param chosenRune rune that was chosen
     */
    public void choseRune(String chosenRune) {
        this.chosenRune = chosenRune;
        this.runeId.remove(chosenRune);
    }

    /**
     * Returns the rune the player last picked.
     * @return player last chosen rune
     */
    public String getChosenRune() {
        return this.chosenRune;
    }
}
