/**
 * Created by Terje on 28.03.2017.
 */

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Runecraft GUI, containing all scenes and elements.
 */
public class MainPage extends Application {
    /**
     * Constant number 20.
     */
    private static final int TWENTY = 20;
    /**
     * Constant number 20.
     */
    private static final int TEN = 10;
    /**
     * Maximum width of the application.
     */
    private static final int MAXWIDTH = 1366;
    /**
     * Maximum heigth of the application.
     */
    private static final int MAXHEIGTH = 768;
    /**
     * Haigth and width of the rune image.
     */
    private static final int RUNESIZE = 120;
    /**
     * Default width for buttons.
     */
    private static final int BTNWIDTH = 580;
    /**
     * Element size 150.
     */
    private static final int SIZE150 = 150;
    /**
     * Constant number 30.
     */
    private static final int THIRTY = 30;
    /**
     * Constant number 50.
     */
    private static final int FIFTY = 50;
    /**
     * Element size 728.
     */
    private static final int SIZE728 = 728;
    /**
     * For rotate Transition, rotates 360 degrees.
     */
    private static final int ROTATE360 = 360;
    /**
     * For rotate Transition, rotates 720 degrees.
     */
    private static final int ROTATE720 = 720;
    /**
     * Constant number 3.
     */
    private static final int THREE = 3;
    /**
     * Element size 623.
     */
    private static final int SIZE623 = 623;
    /**
     * Element size 483.
     */
    private static final int SIZE483 = 483;
    /**
     * Element size 663.
     */
    private static final int SIZE663 = 663;
    /**
     * Element size 200.
     */
    private static final int SIZE200 = 200;
    /**
     * Element size 553.
     */
    private static final int SIZE553 = 553;
    /**
     * Element size 300.
     */
    private static final int SIZE300 = 300;
    /**
     * Element size 390.
     */
    private static final int SIZE390 = 390;
    /**
     * Milliseconds for timeline, 6000.
     */
    private static final int TIMER6 = 6000;
    /**
     * Milliseconds for timeline, 1000.
     */
    private static final int TIMER1 = 1000;
    /**
     * Element opacity 0.5.
     */
    private static final double OPAC5 = 0.5;
    /**
     * Element opacity 0.7.
     */
    private static final double OPAC7 = 0.7;
    /**
     * Mediaplayer for background music.
     */
    private static MediaPlayer musicplayer;
    /**
     * Mediaplayer for soundeffects.
     */
    private static MediaPlayer soundEffect;
    /**
     * Number of rounds the game has, default is 20.
     */
    private int round = TWENTY;
    /**
     * Counts how many rounds has been played.
     */
    private int counterbattle = 0;
    /**
     * Last environment background during the game.
     */
    private ImageView lastBackground = null;
    /**
     * Result of the game. Won or Lost.
     */
    private boolean result = false;
    /**
     * Player class for the game.
     */
    private Player player;
    /**
     * Compyter class for the game.
     */
    private Computer computer;
    /**
     * Environment class for the game.
     */
    private Environment environment;
    /**
     * Label that shows players current score.
     */
    private Label playerStats;
    /**
     * Players score label and last chosen rune.
     */
    private VBox playerSide;
    /**
     * Computers score label and last chosen rune.
     */
    private VBox computerSide;
    /**
     * Label that shows computers current score.
     */
    private Label computerStats;
    /**
     * Battle class for the game.
     */
    private Battle battle;
    /**
     * Current environment background of the game.
     */
    private StackPane background;
    /**
     * Map of generated environments for the game.
     * Key is the ID and Value environment object.
     */
    private Map<String, Environment> gameEnvironments;
    /**
     * List of all environment keys of the game.
     */
    private List<String> envKeys;
    /**
     * Map of generated runes for the game.
     * Key is the ID and Value rune object.
     */
    private Map<String, Rune> gameRunes;
    /**
     * Player name when the game is won and there's a new top score.
     */
    private String playerName;
    /**
     * Gameplay screen.
     */
    private Stage gameStage;
    /**
     * Primary screen of the application.
     */
    private Stage primaryStage;
    /**
     * Treemap of all scores.
     * Key is the score and value list of players that got that score.
     */
    private TreeMap<Integer, List<String>> scoreFile;

    /**
     * Starts the application with all arguments.
     *
     * @param args Stages and objects.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Runecraft");
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        StackPane backPane = new StackPane();
        backPane.setStyle("-fx-background-color: transparent");

        Media media1 = new Media(getClass().getClassLoader().getResource("MusicSrc/Home Sweet Home.mp3").toString());
        musicplayer = new MediaPlayer(media1);
        musicplayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicplayer.play();

        HBox split = new HBox();
        split.setAlignment(Pos.CENTER);
        split.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        split.setStyle("-fx-background-color: transparent");

        StackPane rightPanel = new StackPane();
        rightPanel.setStyle("-fx-background-color: transparent");
        rightPanel.setPrefSize(SIZE663, SIZE728);

        FadeTransition come = fadeTransition(0, 1, rightPanel);

        VBox gameButtons = new VBox();
        gameButtons.setAlignment(Pos.CENTER);
        gameButtons.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        gameButtons.setSpacing(TWENTY);
        gameButtons.setStyle("-fx-background-color: transparent");
        Button start = new Button("Start");
        buttonEffects(start, Color.GOLD, BTNWIDTH);
        Button score = new Button("Score");
        buttonEffects(score, Color.GOLD, BTNWIDTH);
        Button tutorial = new Button("Tutorial");
        buttonEffects(tutorial, Color.GOLD, BTNWIDTH);
        Button options = new Button("Options");
        buttonEffects(options, Color.GOLD, BTNWIDTH);
        Button exit = new Button("Exit");
        buttonEffects(exit, Color.GOLD, BTNWIDTH);
        Label title = new Label("Runecraft");
        title.getStyleClass().add("labelTitle");
        title.setPrefSize(BTNWIDTH, SIZE150);
        title.setAlignment(Pos.CENTER);

        exit.setOnMouseClicked(event -> primaryStage.close());
        tutorial.setOnMouseClicked(event -> {
            rightPanel.getChildren().removeAll(rightPanel.getChildren());
            try {
                rightPanel.getChildren().addAll(tutorial(tutorial));
            } catch (IOException e) {
                e.printStackTrace();
            }
            come.play();
            tutorial.setDisable(true);
            options.setDisable(false);
            score.setDisable(false);
        });
        options.setOnMouseClicked(event -> {
            rightPanel.getChildren().removeAll(rightPanel.getChildren());
            rightPanel.getChildren().addAll(options(options));
            come.play();
            options.setDisable(true);
            tutorial.setDisable(false);
            score.setDisable(false);
        });
        score.setOnMouseClicked(event -> {
            rightPanel.getChildren().removeAll(rightPanel.getChildren());
            rightPanel.getChildren().addAll(score(score));
            come.play();
            options.setDisable(false);
            tutorial.setDisable(false);
            score.setDisable(true);
        });
        start.setOnMouseClicked(event -> {
            rightPanel.getChildren().removeAll(rightPanel.getChildren());
            musicplayer.stop();
            tutorial.setDisable(false);
            options.setDisable(false);
            score.setDisable(false);
            gameInterface();
            this.primaryStage.hide();
        });

        gameButtons.getChildren().addAll(title, start, score, tutorial, options, exit);

        split.getChildren().addAll(gameButtons, rightPanel);
        backPane.getChildren().addAll(split);

        Image background = new Image(getClass().getClassLoader().getResource("StyleSrc/Main.png").toString());
        ImagePattern pattern = new ImagePattern(background);

        Scene scene = new Scene(backPane, MAXWIDTH, MAXHEIGTH, pattern);
        scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toString());

        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.show();
    }

    /**
     * Returns an image object.
     *
     * @param pathname path of the image.
     * @return image object.
     */
    private Image image(String pathname) {
        File imageFile = new File(pathname);
        String fileLocation = imageFile.toURI().toString();
        return new Image(fileLocation);
    }

    /**
     * Sets the main effects for buttons.
     *
     * @param button button object that will be changed.
     * @param text   text color of the button.
     * @param width  width of the button.
     */
    private void buttonEffects(Button button, Color text, int width) {
        button.setTextFill(text);
        button.setPrefWidth(width);
        button.getStyleClass().add("buttonDefault");
        button.setOnMouseEntered(event -> {
            button.setTextFill(text);
            button.setPrefWidth(width);
        });
        button.setOnMousePressed(event -> {
            button.setTextFill(text);
            button.setPrefWidth(width - (width / TEN));
        });
        button.setOnMouseReleased(event -> {
            button.setTextFill(text);
            button.setPrefWidth(width);
        });
    }

    /**
     * Creates a fade transition for StackPane objects.
     *
     * @param from starting opacity.
     * @param to   ending opacity.
     * @param pane StackPane that will receive the effect.
     * @return transition effect.
     */
    private FadeTransition fadeTransition(double from, double to, StackPane pane) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(TIMER1), pane);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        return fadeTransition;
    }

    /**
     * Stackpane for showing all the score tables.
     *
     * @param scorebutton main menu button that leads to the score panel.
     * @return score StackPane.
     */
    private StackPane score(Button scorebutton) {
        StackPane scoreoption = new StackPane();
        scoreoption.managedProperty().bind(scoreoption.visibleProperty());
        scoreoption.setPrefSize(SIZE663, SIZE728);
        scoreoption.setStyle("-fx-background-color: transparent");
        Image scrollImg = new Image(getClass().getClassLoader().getResource(
                "StyleSrc/scrollBackground.jpg").toString());
        ImageView scroll = new ImageView(scrollImg);
        scroll.setFitWidth(SIZE663);
        scroll.setFitHeight(SIZE728);
        scroll.setOpacity(OPAC7);
        ImageView scroll2 = new ImageView(scrollImg);
        scroll2.setFitWidth(SIZE663);
        scroll2.setFitHeight(SIZE728);

        FadeTransition scoreFade = fadeTransition(1, 0, scoreoption);

        StackPane scoreTable = new StackPane();
        scoreTable.setPrefSize(SIZE663, SIZE728);
        scoreTable.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        scoreTable.managedProperty().bind(scoreoption.visibleProperty());
        scoreTable.setStyle("-fx-background-color: transparent");

        Label table = new Label();
        table.setAlignment(Pos.CENTER);
        table.getStyleClass().add("labelTable");
        table.setPrefWidth(SIZE553);

        VBox score = new VBox();
        score.setAlignment(Pos.TOP_CENTER);
        score.setPadding(new Insets(FIFTY, TWENTY, TWENTY, TWENTY));
        score.setSpacing(THIRTY);
        Button back = new Button("Back");
        buttonEffects(back, Color.GHOSTWHITE, SIZE200);

        VBox options = new VBox();
        options.setAlignment(Pos.CENTER);
        options.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        options.setSpacing(THIRTY);
        Button rounds10 = new Button("10 ROUNDS");
        buttonEffects(rounds10, Color.GHOSTWHITE, BTNWIDTH);
        rounds10.setOnMouseClicked(event -> {
            try {
                table.setText(scoreTable("10"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scoreoption.getChildren().remove(options);
            scoreoption.getChildren().add(score);
        });
        Button rounds20 = new Button("20 ROUNDS");
        buttonEffects(rounds20, Color.GHOSTWHITE, BTNWIDTH);
        rounds20.setOnMouseClicked(event -> {
            try {
                table.setText(scoreTable("20"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scoreoption.getChildren().remove(options);
            scoreoption.getChildren().add(score);
        });
        Button rounds25 = new Button("25 ROUNDS");
        buttonEffects(rounds25, Color.GHOSTWHITE, BTNWIDTH);
        rounds25.setOnMouseClicked(event -> {
            try {
                table.setText(scoreTable("25"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scoreoption.getChildren().remove(options);
            scoreoption.getChildren().add(score);
        });
        Button close = new Button("Close");
        buttonEffects(close, Color.GHOSTWHITE, SIZE200);
        close.setOnMouseClicked(event -> {
            scorebutton.setDisable(false);
            scoreFade.play();
        });

        back.setOnMouseClicked(event -> {
            scoreoption.getChildren().remove(score);
            scoreoption.getChildren().addAll(options);
        });
        score.getChildren().addAll(table, back);
        options.getChildren().addAll(rounds10, rounds20, rounds25, close);
        scoreoption.getChildren().addAll(scroll, options);
        return scoreoption;
    }

    /**
     * StackPane for showing all the options for the game.
     *
     * @param optionbutton main menu button that leads to options.
     * @return options StackPane.
     */
    private StackPane options(Button optionbutton) {
        StackPane option = new StackPane();
        option.managedProperty().bind(option.visibleProperty());
        option.setPrefSize(SIZE663, SIZE728);
        option.setStyle("-fx-background-color: transparent");
        Image scrollImg = new Image(getClass().getClassLoader().getResource(
                "StyleSrc/scrollBackground.jpg").toString());
        ImageView scroll = new ImageView(scrollImg);
        scroll.setFitWidth(SIZE663);
        scroll.setFitHeight(SIZE728);
        scroll.setOpacity(OPAC7);

        FadeTransition optionsFade = fadeTransition(1, 0, option);

        VBox options = new VBox();
        options.setAlignment(Pos.CENTER);
        options.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        options.setSpacing(THIRTY);
        Button rounds10 = new Button("10 ROUNDS");
        buttonEffects(rounds10, Color.GHOSTWHITE, BTNWIDTH);
        Button rounds20 = new Button("20 ROUNDS");
        buttonEffects(rounds20, Color.GHOSTWHITE, BTNWIDTH);
        rounds20.setDisable(true);
        Button rounds25 = new Button("25 ROUNDS");
        buttonEffects(rounds25, Color.GHOSTWHITE, BTNWIDTH);
        Button close = new Button("Close");
        buttonEffects(close, Color.GHOSTWHITE, SIZE200);
        close.setOnMouseClicked(event -> {
            optionbutton.setDisable(false);
            optionsFade.play();
        });
        if (this.round == TWENTY) {
            rounds10.setDisable(false);
            rounds20.setDisable(true);
            rounds25.setDisable(false);
        } else if (this.round == TEN) {
            rounds10.setDisable(true);
            rounds20.setDisable(false);
            rounds25.setDisable(false);
        } else if (this.round == FIFTY / 2) {
            rounds10.setDisable(false);
            rounds20.setDisable(false);
            rounds25.setDisable(true);
        }
        rounds10.setOnMouseClicked(event -> {
            this.round = TEN;
            rounds10.setDisable(true);
            rounds20.setDisable(false);
            rounds25.setDisable(false);
        });
        rounds20.setOnMouseClicked(event -> {
            this.round = TWENTY;
            rounds10.setDisable(false);
            rounds20.setDisable(true);
            rounds25.setDisable(false);
        });
        rounds25.setOnMouseClicked(event -> {
            this.round = FIFTY / 2;
            rounds10.setDisable(false);
            rounds20.setDisable(false);
            rounds25.setDisable(true);
        });

        options.getChildren().addAll(rounds10, rounds20, rounds25, close);
        option.getChildren().addAll(scroll, options);
        return option;
    }

    /**
     * StackPane that shows the tutorial scene.
     *
     * @param tutorialbutton main menu button that leads to the tutorial scene.
     * @return tutorial StackPane.
     * @throws IOException when the .txt file that contains tutorial description is missing.
     */
    private StackPane tutorial(Button tutorialbutton) throws IOException {
        StackPane tutorial = new StackPane();
        tutorial.managedProperty().bind(tutorial.visibleProperty());
        tutorial.setPrefSize(SIZE663, SIZE728);
        tutorial.setStyle("-fx-background-color: transparent");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-alignment: center;"
                + "-fx-background: #eeff3b;"
                + "-fx-opacity: 0.8");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(SIZE663, SIZE728);
        Image scrollImg = new Image(getClass().getClassLoader().getResource(
                "StyleSrc/scrollBackground.jpg").toString());
        ImageView scroll = new ImageView(scrollImg);
        scroll.setFitWidth(SIZE663);
        StackPane test = new StackPane();
        VBox split = new VBox();
        split.setAlignment(Pos.CENTER);
        split.setStyle("-fx-background-color: transparent");
        split.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        split.setSpacing(FIFTY);
        split.setPrefWidth(SIZE663);

        FadeTransition tutorialFade = fadeTransition(1, 0, tutorial);

        scroll.fitHeightProperty().bind(test.heightProperty());
        test.getChildren().addAll(scroll, split);
        Image chartImg = new Image(getClass().getClassLoader().getResource("StyleSrc/Chart.png").toString());
        ImageView chart = new ImageView(chartImg);
        chart.setStyle("-fx-alignment: center");
        chart.setOpacity(1);
        chart.setFitWidth(SIZE623);

        Button close = new Button("Close");
        buttonEffects(close, Color.GHOSTWHITE, SIZE200);
        close.setOnMouseClicked(event -> {
            tutorialbutton.setDisable(false);
            tutorialFade.play();
        });

        Label tutText = new Label();
        List<String> lines = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Tutorial.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        String lineinput = input.readLine();
        while (lineinput != null) {
            lines.add(lineinput);
            lineinput = input.readLine();
        }
        input.close();

        String tutorialFile = "";
        for (String line : lines) {
            tutorialFile += line + "\n";
        }
        tutText.setText(tutorialFile);
        tutText.getStyleClass().add("labelTutText");

        scrollPane.setContent(test);
        split.getChildren().addAll(chart, tutText, close);
        tutorial.getChildren().addAll(scrollPane);
        return tutorial;
    }

    /**
     * Generates new window for the gameplay.
     */
    private void gameInterface() {
        int rows = this.round / (TEN / 2);
        GenerateGame game = new GenerateGame(this.round);
        this.player = game.getPlayer();
        this.computer = game.getComputer();
        this.gameRunes = game.getGameRunes();
        this.gameEnvironments = game.getGameEnvironments();
        this.envKeys = new ArrayList<>(gameEnvironments.keySet());
        Random random = new Random();
        int value = random.nextInt(envKeys.size());
        this.environment = gameEnvironments.get(envKeys.get(value));
        this.environment.powerUps(gameRunes);
        this.computer.identifyEnvironment(this.environment);
        Image environmentImg = new Image(getClass().getClassLoader().getResource(
                "StyleSrc/" + this.environment.getEnvironment() + ".jpg").toString());
        ImageView test = new ImageView(environmentImg);
        this.lastBackground = test;
        test.setFitHeight(MAXHEIGTH);
        test.setFitWidth(MAXWIDTH);

        Media media1 = new Media(getClass().getClassLoader().getResource(
                "MusicSrc/" + environment.getEnvironment() + ".mp3").toString());
        musicplayer = new MediaPlayer(media1);
        musicplayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicplayer.play();

        List<String> playerRunesId = this.player.getRuneId();
        int counter = 0;

        this.battle = game.startBattle();

        this.gameStage = new Stage();
        this.gameStage.setTitle("Runecraft");
        this.gameStage.initStyle(StageStyle.UNDECORATED);
        this.gameStage.setResizable(false);

        Button exit = new Button("Exit");
        buttonEffects(exit, Color.GOLD, SIZE150);
        exit.setOnMouseClicked(event -> this.gameStage.close());

        this.playerSide = new VBox();
        this.playerSide.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        this.playerSide.setSpacing(TEN);
        this.playerSide.setAlignment(Pos.TOP_LEFT);
        this.playerStats = new Label();
        this.playerStats.setText("Player\nScore: " + this.player.finalScore() + "\n" + "Last chosen:");
        this.playerStats.setPrefWidth(SIZE200);
        this.playerStats.setAlignment(Pos.CENTER);
        this.playerStats.getStyleClass().add("labelStatsPlayer");
        this.playerSide.getChildren().addAll(this.playerStats);

        this.computerSide = new VBox();
        this.computerSide.setPadding(new Insets(TWENTY, TWENTY, TWENTY, TWENTY));
        this.computerSide.setSpacing(TEN);
        this.computerSide.setAlignment(Pos.TOP_RIGHT);
        this.computerStats = new Label();
        this.computerStats.setText("Computer\nScore: " + this.computer.finalScore() + "\n" + "Last chosen:");
        this.computerStats.setPrefWidth(SIZE200);
        this.computerStats.setAlignment(Pos.CENTER);
        this.computerStats.getStyleClass().add("labelStatsComputer");
        this.computerSide.getChildren().addAll(this.computerStats);

        this.background = new StackPane();
        StackPane.setAlignment(exit, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(this.playerSide, Pos.CENTER_LEFT);

        GridPane runeGrid = new GridPane();
        StackPane.setAlignment(runeGrid, Pos.CENTER);
        runeGrid.setAlignment(Pos.CENTER);
        runeGrid.setHgap(FIFTY);
        runeGrid.setVgap(FIFTY / 2);
        runeGrid.setPadding(new Insets(TEN, TEN, TEN, TEN));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < TEN / 2; col++) {
                Rune selected = gameRunes.get(playerRunesId.get(counter));
                StackPane rune = rune(selected);
                counter += 1;
                runeGrid.add(rune, col, row);
            }
        }

        background.getChildren().addAll(test, this.playerSide, this.computerSide, runeGrid, exit);
        Scene scene = new Scene(this.background, MAXWIDTH, MAXHEIGTH);
        scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toString());
        this.gameStage.setScene(scene);
        this.gameStage.show();
    }

    /**
     * Generates a rune StackPane for the grid.
     *
     * @param selected Player rune object that was generated for the game.
     * @return small stackpane containing the rune image.
     */
    private StackPane rune(Rune selected) {
        String type = selected.getType();
        StackPane rune = new StackPane();
        rune.setPrefSize(RUNESIZE, RUNESIZE);
        rune.setStyle("-fx-background-color: transparent");

        Image runeImg = new Image(getClass().getClassLoader().getResource("RunesSrc/" + type + ".png").toString());
        ImageView runeTypeImg = new ImageView(runeImg);
        runeTypeImg.setFitHeight(RUNESIZE);
        runeTypeImg.setFitWidth(RUNESIZE);

        Media sound = new Media(getClass().getClassLoader().getResource(
                "SoundSrc/Cast_" + selected.getType() + ".mp3").toString());

        Glow glow = new Glow();

        Random random = new Random();
        int rand = random.nextInt(ROTATE720) - ROTATE360;
        while (Math.abs(rand) < THIRTY) {
            rand = random.nextInt(ROTATE720) - ROTATE360;
        }

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(TIMER6), runeTypeImg);
        rotateTransition.setByAngle(rand);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();

        Tooltip tooltip = new Tooltip(runeStats(selected));
        tooltip.getStyleClass().add("tooltipStyle");

        runeTypeImg.setOnMouseEntered(event -> {
            rotateTransition.pause();
            tooltip.setPrefWidth(SIZE200 + FIFTY);
            tooltip.setText(runeStats(selected));
            tooltip.show(runeTypeImg, this.gameStage.getX(), this.gameStage.getY() + SIZE300);
            glow.setLevel(OPAC5);
            runeTypeImg.setEffect(glow);

        });
        runeTypeImg.setOnMouseExited(event -> {
            glow.setLevel(0);
            tooltip.hide();
            rotateTransition.play();
        });
        runeTypeImg.setOnMouseClicked(event -> {
            try {
                elementClicked(selected, random, sound, runeTypeImg, rune);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        rune.getChildren().addAll(runeTypeImg);
        return rune;
    }

    /**
     * Unused for now, should give information of the selected rune.
     *
     * @param selected selected rune object.
     * @return textflow of rune stats.
     */
    private String runeStats(Rune selected) {
        List<String> weaknessesList = selected.getWeaknesses();
        List<String> attacksLst = selected.getAttacks();
        String type = selected.getType();
        String name = type + "\n";
        String weaknesses = "Weaknesses:\n";
        String attacks = "Attacks:\n";
        int countatk = 0;
        int countweak = 0;
        for (String weak : weaknessesList) {
            weaknesses += weak + ", ";
            countweak += 1;
            if (countweak % THREE == 0) {
                weaknesses += "\n";
            }
        }
        for (String atk : attacksLst) {
            countatk += 1;
            attacks += atk + ", ";
            if (countatk % THREE == 0) {
                attacks += "\n";
            }
        }
        attacks += "\n";
        return name + attacks + weaknesses;
    }

    /**
     * Activates changes on the interface and in the battle sequence.
     *
     * @param selected    rune object that was clicked on.
     * @param random      for generating a random number.
     * @param runeTypeImg image of the selected rune.
     * @param sound soundfile for the sound effect.
     * @param rune        rune object stackpane.
     * @throws IOException when the score .txt file is missing.
     */
    private void elementClicked(Rune selected, Random random, Media sound,
                                ImageView runeTypeImg, StackPane rune) throws IOException {
        this.player.choseRune(selected.getId());
        this.battle.roundCheck();

        soundEffect = new MediaPlayer(sound);
        soundEffect.setCycleCount(1);
        soundEffect.getOnReady();

        Rune compRune = this.gameRunes.get(this.computer.getChosenRune());
        String compRuneType = compRune.getType();
        StackPane rune2 = new StackPane();
        rune2.setPrefSize(RUNESIZE, RUNESIZE);
        rune2.setStyle("-fx-background-color: transparent");
        Image runeImg2 = new Image(getClass().getClassLoader().getResource(
                "RunesSrc/" + compRuneType + ".png").toString());
        ImageView runeTypeImg2 = new ImageView(runeImg2);
        runeTypeImg2.setFitHeight(RUNESIZE);
        runeTypeImg2.setFitWidth(RUNESIZE);
        int rand2 = random.nextInt(ROTATE720) - ROTATE360;
        while (Math.abs(rand2) < THIRTY) {
            rand2 = random.nextInt(ROTATE720) - ROTATE360;
        }
        RotateTransition rotateTransition2 = new RotateTransition(Duration.millis(TIMER6), runeTypeImg2);
        rotateTransition2.setByAngle(rand2);
        rotateTransition2.setCycleCount(Timeline.INDEFINITE);
        rotateTransition2.setAutoReverse(true);
        rotateTransition2.play();

        this.computerStats.setText("Computer\nScore: " + this.computer.finalScore() + "\n" + "Last chosen:");
        this.computerSide.getChildren().removeAll(this.computerSide.getChildren());
        this.computerSide.getChildren().addAll(this.computerStats, runeTypeImg2);

        rune.getChildren().removeAll(runeTypeImg);
        this.playerStats.setText("Player\nScore: " + this.player.finalScore() + "\n" + "Last chosen:");
        this.playerSide.getChildren().removeAll(this.playerSide.getChildren());
        this.playerSide.getChildren().addAll(this.playerStats, runeTypeImg);
        this.counterbattle += 1;

        soundEffect.play();

        if (this.counterbattle == this.round) {
            this.result = this.player.finalScore() >= this.computer.finalScore();
            musicplayer.stop();
            this.gameStage.close();
            result();
        } else if (this.counterbattle % (TEN / 2) == 0) {
            int value = random.nextInt(envKeys.size());
            Environment environment2 = this.gameEnvironments.get(this.envKeys.get(value));
            environment2.powerUps(this.gameRunes);
            Image environmentImg = new Image(getClass().getClassLoader().getResource(
                    "StyleSrc/" + environment2.getEnvironment() + ".jpg").toString());
            ImageView test2 = new ImageView(environmentImg);
            test2.setFitHeight(MAXHEIGTH);
            test2.setFitWidth(MAXWIDTH);
            if (!this.environment.getEnvironment().equals(environment2.getEnvironment())) {
                this.background.getChildren().remove(this.lastBackground);
                this.background.getChildren().add(test2);
                this.lastBackground = test2;
                this.environment = environment2;
                this.computer.identifyEnvironment(this.environment);
                test2.toBack();
                musicplayer.stop();
                Media media1 = new Media(getClass().getClassLoader().getResource(
                        "MusicSrc/" + environment.getEnvironment() + ".mp3").toString());
                musicplayer = new MediaPlayer(media1);
                musicplayer.setCycleCount(MediaPlayer.INDEFINITE);
                musicplayer.play();
            }
        }
    }

    /**
     * Opens new scene after the game ends.
     *
     * @throws IOException when the score .txt files can't be found.
     */
    private void result() throws IOException {
        Image background;
        ImageView backgroundImg;
        this.counterbattle = 0;
        Stage result = new Stage();
        result.setTitle("Runecraft");
        result.initStyle(StageStyle.UNDECORATED);
        result.setResizable(false);
        StackPane resultPane = new StackPane();
        resultPane.setAlignment(Pos.CENTER);

        Button exit = new Button("Continue to Main Menu");
        buttonEffects(exit, Color.GOLD, SIZE390);

        if (this.result) {
            background = new Image(getClass().getClassLoader().getResource("StyleSrc/Winner.png").toString());
            Media media1 = new Media(getClass().getClassLoader().getResource("MusicSrc/Winner.mp3").toString());
            musicplayer = new MediaPlayer(media1);
            musicplayer.setCycleCount(MediaPlayer.INDEFINITE);
            musicplayer.play();

            backgroundImg = new ImageView(background);
            backgroundImg.setFitWidth(MAXWIDTH);
            backgroundImg.setFitHeight(MAXHEIGTH);

            VBox container = new VBox();
            container.setAlignment(Pos.CENTER);
            container.setPadding(new Insets(SIZE150, SIZE483, TWENTY, SIZE483));
            container.setSpacing(TWENTY);
            container.setStyle("-fx-background-color: transparent");
            container.setPrefWidth(TEN * TEN);

            if (highScore()) {
                Label infoText = new Label("Player: " + this.player.finalScore() + "\tComputer: "
                        + this.computer.finalScore() + "\nNew Top Score! Enter your name");
                infoText.getStyleClass().add("labelInfoText");
                TextField enterText = new TextField();
                enterText.getStyleClass().add("enterText");
                enterText.setAlignment(Pos.CENTER);
                container.getChildren().addAll(infoText, enterText, exit);

                exit.setOnMouseClicked(event -> {
                    this.playerName = enterText.getText();
                    try {
                        saveScore();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    result.close();
                    this.primaryStage.show();
                });
            } else {
                Label infoText = new Label("Player: " + this.player.finalScore() + "\tComputer: "
                        + this.computer.finalScore());
                infoText.getStyleClass().add("labelInfoText");
                if (this.computer.finalScore() == this.player.finalScore()) {
                    infoText.setText("It's a tie!");
                }
                exit.setOnMouseClicked(event -> {
                    result.close();
                    this.primaryStage.show();
                });
                container.getChildren().addAll(infoText, exit);
            }
            resultPane.getChildren().addAll(backgroundImg, container);
        } else {
            background = new Image(getClass().getClassLoader().getResource("StyleSrc/Loser.png").toString());
            Media media1 = new Media(getClass().getClassLoader().getResource("MusicSrc/Loser.mp3").toString());
            musicplayer = new MediaPlayer(media1);
            musicplayer.setCycleCount(MediaPlayer.INDEFINITE);
            musicplayer.play();

            VBox container = new VBox();
            container.setAlignment(Pos.CENTER);
            container.setPadding(new Insets(SIZE150, SIZE483, TWENTY, SIZE483));
            container.setSpacing(TWENTY);
            container.setStyle("-fx-background-color: transparent");
            container.setPrefWidth(TEN * TEN);

            Label infoText = new Label("Player: " + this.player.finalScore() + "\tComputer: "
                    + this.computer.finalScore());
            infoText.getStyleClass().add("labelInfoTextLost");

            backgroundImg = new ImageView(background);
            backgroundImg.setFitWidth(MAXWIDTH);
            backgroundImg.setFitHeight(MAXHEIGTH);
            container.getChildren().addAll(infoText, exit);
            resultPane.getChildren().addAll(backgroundImg, container);

            exit.setOnMouseClicked(event -> {
                result.close();
                this.primaryStage.show();
            });
        }

        Scene scene = new Scene(resultPane, MAXWIDTH, MAXHEIGTH);
        scene.getStylesheets().add(getClass().getResource("Stylesheet.css").toString());
        result.setScene(scene);
        result.show();
    }

    /**
     * Check if a new top score was made.
     *
     * @return top score, true or false.
     * @throws IOException when the score .txt files can't be found.
     */
    private boolean highScore() throws IOException {
        Boolean highScore = false;
        int score = this.player.finalScore() - this.computer.finalScore();
        this.scoreFile = new TreeMap<>(Collections.reverseOrder());
        List<String> scoreLine;

        File file = new File("Score_" + this.round + ".txt");
        if (file.isFile()) {
            Path path = Paths.get(file.toURI());
            scoreLine = Files.readAllLines(path);

            if (scoreLine.size() < TEN) {
                highScore = true;
            }
            int counter = 0;
            for (String line : scoreLine) {
                if (counter < TEN - 1) {
                    counter += 1;
                    String[] split = line.split("#");
                    List<String> name = new ArrayList<>();
                    name.add(split[0]);
                    if (this.scoreFile.containsKey(Integer.valueOf(split[1]))) {
                        name = this.scoreFile.get(Integer.valueOf(split[1]));
                        name.add(split[0]);
                    }
                    this.scoreFile.put(Integer.valueOf(split[1]), name);
                    if (score >= Integer.valueOf(split[1])) {
                        highScore = true;
                    }
                }
            }
            return highScore;
        } else {
            return true;
        }
    }

    /**
     * Writes the new top score to the corresponding text file.
     *
     * @throws IOException when the score .txt files can't be found.
     */
    private void saveScore() throws IOException {
        int score = this.player.finalScore() - this.computer.finalScore();
        List<String> playerName = new ArrayList<>();
        playerName.add(this.playerName);
        if (this.scoreFile.containsKey(score)) {
            playerName = this.scoreFile.get(score);
            playerName.add(0, this.playerName);
        }
        this.scoreFile.put(score, playerName);
        List<Integer> keys = new ArrayList<>(this.scoreFile.keySet());
        File file = new File("Score_" + this.round + ".txt");
        if (file.isFile()) {
            Writer writer = new FileWriter(file);
            int counter = 0;
            for (int i = 0; i < TEN - 1 && i < keys.size(); i++) {
                int key = keys.get(i);
                for (int j = 0; j < this.scoreFile.get(key).size(); j++) {
                    List<String> names = this.scoreFile.get(key);
                    writer.write(names.get(j) + "#" + key + "\n");
                    counter += 1;
                    if (counter == TEN) {
                        break;
                    }
                }
            }
            writer.close();
        } else {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(this.playerName + "#" + score + "\n");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Generates a table of the scores from the score file.
     *
     * @param roundType round type to determine which file will be used.
     * @return string containing the score table.
     * @throws IOException when the score .txt file can't be found.
     */
    private String scoreTable(String roundType) throws IOException {
        String text = "";
        File file = new File("Score_" + roundType + ".txt");
        if (file.isFile()) {
            List<String> lines;
            Path path = Paths.get(file.toURI());
            lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] split = line.split("#");
                text += split[0] + ":  " + split[1] + "\n";
            }
        }
        return text;
    }
}
