import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Terje on 19.03.2017.
 */
public class TestGame {

    public static void main(String[] args) {
        GenerateGame testGame = new GenerateGame(20);
        Map<String, Rune> gameRunes = testGame.getGameRunes();
        Map<String, Environment> gameEnvironments = testGame.getGameEnvironments();
        List<String> envKeys = new ArrayList<>(gameEnvironments.keySet());
        Random random = new Random();
        int value = random.nextInt(envKeys.size());
        Environment environment = gameEnvironments.get(envKeys.get(value));
        environment.powerUps(gameRunes);
        Battle battle = testGame.startBattle();
        for (int i = 0; i < 20; i++) {
            battle.roundCheck();
            value = random.nextInt(envKeys.size());
            environment = gameEnvironments.get(envKeys.get(value));
            environment.powerUps(gameRunes);
        }
        Player player = testGame.getPlayer();
        Computer computer = testGame.getComputer();
        System.out.println("Player final score: " + player.finalScore());
        System.out.println("Computer final score: " + computer.finalScore());
    }
}
