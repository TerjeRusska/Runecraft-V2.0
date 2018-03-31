import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by Terje on 18.04.2017.
 */
public class GenerateGameTest {
    /**
     * Default number of rounds.
     */
    private static final int ROUNDS20 = 20;
    /**
     * 20 rounds must generate 40 runes, 20 for player and 20 for computer.
     */
    private static final int RUNES40 = 40;
    /**
     * Each game must generate 5 random environments to choose from.
     */
    private static final int ENVIRONMENTS = 5;
    /**
     * Tests generated objects when the game has 20 rounds.
     */
    @org.junit.Test
    public void generateGame20() {
        GenerateGame testGame = new GenerateGame(ROUNDS20);
        Map<String, Rune> gameRunes = testGame.getGameRunes();
        assertTrue(gameRunes.size() == RUNES40);
        Map<String, Environment> gameEnvironments = testGame.getGameEnvironments();
        assertTrue(gameEnvironments.size() == ENVIRONMENTS);
        Player player = testGame.getPlayer();
        Computer computer = testGame.getComputer();
        assertTrue(player.getRuneId().size() == ROUNDS20);
        assertTrue(computer.getRuneId().size() == ROUNDS20);
        for (String id : computer.getRuneId()) {
            assertTrue(!player.getRuneId().contains(id));
        }
    }
    /**
     * Tests the list of attacks and weaknesses depending on the type of the rune.
     */
    @org.junit.Test
    public void runeTypes() {
        Rune waterRune = new Rune("randomId2");
        waterRune.giveType();
        while (!waterRune.getType().equals("Water")) {
            waterRune.giveType();
        }
        List<String> waterAttacks = Arrays.asList("Cosmic", "Fire", "Nature", "Law", "Blood");
        List<String> waterWeaknesses = Arrays.asList("Air", "Mind", "Chaos", "Soul", "Body");
        assertEquals(waterAttacks, waterRune.getAttacks());
        assertEquals(waterWeaknesses, waterRune.getWeaknesses());
        Rune fireRune = new Rune("randomId3");
        fireRune.giveType();
        while (!fireRune.getType().equals("Fire")) {
            fireRune.giveType();
        }
        List<String> fireAttacks = Arrays.asList("Mind", "Chaos", "Soul", "Body", "Cosmic");
        List<String> fireWeaknesses = Arrays.asList("Nature", "Law", "Blood", "Water", "Air");
        assertEquals(fireAttacks, fireRune.getAttacks());
        assertEquals(fireWeaknesses, fireRune.getWeaknesses());
    }
    /**
     * Tests rune powerups depending on the type of the environment.
     */
    @org.junit.Test
    public void environmentPowers() {
        Environment testFire = new Environment("randomId1");
        while (!testFire.getEnvironment().equals("Fire_env")) {
            testFire = new Environment("randomId1");
        }
        Rune waterRune = new Rune("randomId2");
        waterRune.giveType();
        while (!waterRune.getType().equals("Water")) {
            waterRune.giveType();
        }
        List<String> waterAttacks = Arrays.asList("Cosmic", "Fire", "Nature", "Law", "Blood");
        List<String> waterWeaknesses = Arrays.asList("Air", "Mind", "Chaos", "Soul", "Body");
        Rune fireRune = new Rune("randomId3");
        fireRune.giveType();
        while (!fireRune.getType().equals("Fire")) {
            fireRune.giveType();
        }
        List<String> fireWeaknesses = Arrays.asList("Nature", "Law", "Blood", "Water", "Air");

        Map<String, Rune> gameRunes = new HashMap<>();
        gameRunes.put("randomId2", waterRune);
        gameRunes.put("randomId3", fireRune);
        testFire.powerUps(gameRunes);

        assertEquals(waterAttacks, waterRune.getAttacks());
        assertEquals(waterWeaknesses, waterRune.getWeaknesses());
        assertEquals(fireWeaknesses, fireRune.getWeaknesses());
        List<String> fireAttacksPower = Arrays.asList("Mind", "Chaos", "Soul", "Body", "Cosmic", "Fire",
                "Nature", "Law", "Blood", "Water", "Air");
        assertEquals(fireAttacksPower, fireRune.getAttacks());
    }
}
