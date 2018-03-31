
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Terje on 19.03.2017.
 */
public class Battle {
    /**
     * List of all runeIDs of the game.
     */
    private List<String> runeID;
    /**
     * Map of all rune objects of the game.
     */
    private Map<String, Rune> gameRunes;
    /**
     * Computer that plays.
     */
    private Computer computer;
    /**
     * Player that plays.
     */
    private Player player;

    /**
     * Constructor for the battle class.
     *
     * @param gameRunes All the rune objects for the battle.
     * @param player    Player object
     * @param computer  Computer object
     */
    public Battle(Map<String, Rune> gameRunes, Player player, Computer computer) {
        this.runeID = new ArrayList<>(gameRunes.keySet());
        this.gameRunes = gameRunes;
        this.player = player;
        this.computer = computer;
    }

    /**
     * Gives point to the winner of the round.
     */
    public void roundCheck() {
        int computerScore = 0;
        int playerScore = 0;

        String playerRuneID = this.player.getChosenRune();
        String computerRuneID = this.computer.choseRune();

        this.computer.identifyPlayerRune(this.gameRunes.get(playerRuneID));

        String playerRune = this.gameRunes.get(playerRuneID).getType();


        String computerRune = this.gameRunes.get(computerRuneID).getType();


        List<String> playerAttacks = this.gameRunes.get(playerRuneID).getAttacks();
        List<String> computerAttacks = this.gameRunes.get(computerRuneID).getAttacks();
        List<String> playerWeaknesses = this.gameRunes.get(playerRuneID).getWeaknesses();
        List<String> computerWeaknesses = this.gameRunes.get(computerRuneID).getWeaknesses();
        if (playerWeaknesses.contains(computerRune)) {
            computerScore += 1;
        }
        if (computerAttacks.contains(playerRune)) {
            if (playerWeaknesses.size() > 0) {
                computerScore += 1;
            }
        }
        if (computerWeaknesses.contains(playerRune)) {
            playerScore += 1;
        }
        if (playerAttacks.contains(computerRune)) {
            if (computerWeaknesses.size() > 0) {
                playerScore += 1;
            }
        }
        if (computerScore > playerScore) {
            this.computer.addScore();
        } else if (computerScore == playerScore) {
            this.computer.addScore();
            this.player.addScore();
        } else {
            this.player.addScore();
        }
    }
}
