package games;

import card.DeckOfCards;
import color.Color;
import card.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.Game.java
 * @brief Class representing the games.
 */
public class Game {
    /** Secret string to reveal the combination to get the goal situation (as requested by D.Poitrenaud) */
    private static final String SECRET_STRING = "%^&*</=#@";
    /** Minimum number of players to play a games */
    private static final int MIN_PLAYERS = 2;
    /** ArrayList to store the player */
    private final ArrayList<Player> players;
    /** Boolean to know if the current turn situation has been found */
    private boolean currentTurnSituationFound;
    /** Card representing the starting situation */
    private Card startingSituation;
    /** Card representing the goal situation */
    private Card goalSituation;
    /** Scanner to get the player input */
    private final Scanner playerInputScanner;
    /** Deck of card, used to get situations */
    private final DeckOfCards deck;

    /**
     * Constructor of the class that creates a games with a given list of nicknames.
     * @param nicknames List of nicknames of the players.
     */
    public Game(String[] nicknames) {
        verifyNicknames(nicknames);
        players = new ArrayList<>(nicknames.length);
        for (String nickname : nicknames)
            players.add(new Player(nickname));
        deck = new DeckOfCards();
        startingSituation =deck.pickRandomCard();
        currentTurnSituationFound=false;
        playerInputScanner = new Scanner(System.in);
    }

    /**
     * Getter for the starting situation.
     * @return the starting situation.
     */
    public Card getStartingSituation(){
        return startingSituation;
    }

    /**
     * Getter for the goal situation.
     * @return the goal situation.
     */
    public Card getGoalSituation(){
        return goalSituation;
    }

    /**
     * Utility function that verifies the nicknames given by the user.
     * If there are less than 2 nicknames or there is duplicate names, the program exits.
     * @param nicknames List of nicknames to verify.
     */
    private void verifyNicknames(String[] nicknames){
        if (nicknames.length < MIN_PLAYERS){
            System.out.println(Color.coloredString(Color.ANSI.RED,
                    "Pour jouer une partie, il faut " + MIN_PLAYERS + " joueurs minimum."));
            exit(1);
        }
        if (hasDoubleNicknames(nicknames)) {
            System.err.println(Color.coloredString(Color.ANSI.RED,
                    "Deux joueurs ne peuvent pas avoir le même nom."));
            exit(1);
        }
    }

    /**
     * Utility function that checks if there are duplicate nicknames in a list of nicknames.
     * @param nicknames List of nicknames to check.
     * @return true if there are duplicate nicknames, false otherwise.
     */
    public static boolean hasDoubleNicknames(String[] nicknames){
        for (int i = 0; i < nicknames.length; i++) {
            for (int j = i+1; j < nicknames.length; j++) {
                if (nicknames[i].equals(nicknames[j]))
                    return true;
            }
        }
        return false;
    }

    /**
     * Plays a games of the "Crazy Circus" games.
     * The games is played until the deck is empty.
     * At the end of the games, the list of players and their scores is displayed in descending order.
     */
    public void play() {
        while (!deck.isEmpty()){
            turn();
        }
        endOfGame();
    }

    /**
     * Plays out a turn of the games.
     * At the start prints out the starting and goal situations.
     * Each player can guess the command combination to get from the starting situation to the goal situation.
     * The turn goes on until one player finds the combination, all but one player have lost the turn or if the
     * secret command was called.
     */
    public void turn(){
        setupStartOfTurn();
        System.out.println(this);
        while (!hasAllButOnePlayerLostCurrentTurn() && !currentTurnSituationFound){
            Pair<String> playerInput = getInputFromPlayer();
            verifyPlayerCombinationInput(playerInput.getFirst(), playerInput.getSecond());
        }
        changeCurrentSituationToGoalSituation();
    }

    /**
     * Start of turn setup :
     * - Resets the current turn status for every player (every player has not lost the turn at the start of a turn).
     * - Resets the current situation found (the current situation has not been found at the start of a turn).
     * - Picks the goal situation for the turn.
     */
    private void setupStartOfTurn(){
        resetCurrentTurnStatusForEveryPlayer();
        resetCurrentSituationFound();
        pickTurnGoalSituation();
    }

    /**
     * Picks the goal situation for the turn, from the deck of card, removing that card from the deck.
     */
    private void pickTurnGoalSituation(){
        goalSituation = deck.pickRandomCard();
    }

    /**
     * Changes the current situation to the goal situation.
     * Happens at the end of a turn
     * (by the rules at the end of a turn, the current situation becomes the goal situation).
     */
    private void changeCurrentSituationToGoalSituation(){
        startingSituation = goalSituation;
    }

    /**
     * Resets the current situation found to false (used at the start of a turn).
     * @see Game#setupStartOfTurn()
     */
    private void resetCurrentSituationFound(){
        currentTurnSituationFound = false;
    }

    /**
     * Resets the current turn status for every player (used at the start of a turn).
     * @see Player#resetCurrentTurnLost()
     * @see Game#setupStartOfTurn()
     */
    private void resetCurrentTurnStatusForEveryPlayer(){
        for (Player p : players)
            p.resetCurrentTurnLost();
    }

    /**
     * Checks if all but one player have lost the current turn.
     * If that is the case, the player who has not lost the turn has his score incremented.
     * @return true if all but one player have lost the current turn, false otherwise.
     */
    public boolean hasAllButOnePlayerLostCurrentTurn(){
        Player lastOneStanding = null; // used to store player who has not lost, to increment his score if he is the only one
        int playersWhoHaveNotLost=0;
        for (Player p : players){
            if (!p.isCurrentTurnLost()) {
                playersWhoHaveNotLost++;
                lastOneStanding = p;
            }
        }
        if (playersWhoHaveNotLost==1){
            lastOneStanding.incrementScore();
            displayAllButOnePlayerLostCurrentTurn(lastOneStanding.getNickname());
            return true;
        }
        return false;
    }

    /**
     * Displays a message to the user when all but one player have lost the current turn, informing
     * that he has won the turn.
     * @param playerNickname Nickname of the player who has not lost the current turn.
     */
    private void displayAllButOnePlayerLostCurrentTurn(String playerNickname){
        System.out.print(Color.coloredString(Color.ANSI.GREEN, "Tous les joueurs ont perdu sauf "));
        System.out.print(Color.coloredString(Color.ANSI.YELLOW, playerNickname));
        System.out.println(Color.coloredString(Color.ANSI.GREEN," qui remporte le tour. Félicitations!"));
    }

    /**
     * End of games processing :
     * - Sorts the players by score then by name.
     * - Displays the list of players and their scores in descending order.
     */
    public void endOfGame(){
        assert(deck.isEmpty());
        sortPlayersByScoreThenByName();
        endOfGameDisplay();
    }

    /**
     * Sorts the players by score then by name thanks to a comparator.
     */
    private void sortPlayersByScoreThenByName(){
        players.sort(Comparator.comparing(Player::getScore).thenComparing(Player::getNickname));
    }

    /**
     * Displays the list of players and their scores in descending order.
     */
    private void endOfGameDisplay(){
        int rank=1;
        for (Player p : players) {
            System.out.print((rank++) + ". ");
            System.out.println(p);
        }
    }

    /**
     * Verifies if the player can play at a given turn (if he has not given a wrong combination yet).
     * @param playerNickname Nickname of the player to check.
     * @return true if the player can play, false otherwise.
     */
    public boolean canPlayerPlay(String playerNickname){
        if (doesPlayerNotExist(playerNickname))
            return false;
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return !p.isCurrentTurnLost();
        }
        return false;
    }

    /**
     * Checks if a player does not exist in the list of players.
     * @param playerNickname Nickname of the player to check.
     * @return true if the player does not exist, false otherwise.
     */
    public boolean doesPlayerNotExist(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return false;
        }
        return true;
    }

    /**
     * Retrieves a player from the list of players by his nickname.
     * @param playerNickname Nickname of the player to retrieve.
     * @return The player with the given nickname, or null if no player has that nickname.
     */
    private Player getPlayerByNickname(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return p;
        }
        return null;
    }

    /**
     * Uses the CombinationFinder class to find the combination that leads from the starting situation to the goal situation.
     * Only to be used with the secret command.
     * @return The combination that leads from the starting situation to the goal situation.
     */
    private String findCombination(){
        CombinationFinder finder = new CombinationFinder(startingSituation, goalSituation);
        return finder.findCombination();
    }

    /**
     * Displays the combination that leads from the starting situation to the goal situation.
     * @see Game#findCombination()
     * @see Game#SECRET_STRING
     * @param combination Combination that leads from the starting situation to the goal situation.
     */
    private void displayFoundCombination(String combination){
        System.out.println(Color.coloredString(Color.ANSI.GREEN, "Combinaison trouvée: " + combination));
    }

    /**
     * Does numerous checks on the combination input by the player.
     * @param input1 Nickname of the player who is inputting the combination / secret string.
     * @param input2 Combination input by the player.
     */
    private void verifyPlayerCombinationInput(String input1, String input2){
        if (isSecretString(input1)) {
            displayFoundCombination(findCombination());
            currentTurnSituationFound=true;
        }
        else if (doesPlayerNotExist(input1))
            CombinationInputState.displayCombinationInputState(CombinationInputState.NON_EXISTENT_PLAYER);
        else if (!canPlayerPlay(input1))
            CombinationInputState.displayCombinationInputState(CombinationInputState.CANNOT_PLAY);
        else if (!checkCombination(input2)){
            CombinationInputState.displayCombinationInputState(CombinationInputState.INVALID_INPUT);
            Objects.requireNonNull(getPlayerByNickname(input1)).loseTurn();
        }
        else {
            CombinationInputState.displayCombinationInputState(CombinationInputState.CORRECT_INPUT);
            currentTurnSituationFound=true;
            Objects.requireNonNull(getPlayerByNickname(input1)).incrementScore();
        }
    }

    /**
     * Checks if the input is the secret string.
     * @param s string to check.
     * @return true if the input is the secret string, false otherwise.
     */
    public static boolean isSecretString(String s){
        return s.equals(SECRET_STRING);
    }

    /**
     * Checks if the combination input by the player leads from the starting situation to the goal situation.
     * @param combination Combination input by the player.
     * @return true if the combination leads from the starting situation to the goal situation,
     * if the combination is invalid (or throws an exception), returns false.
     */
    private boolean checkCombination(String combination){
        try {
            if (combination.length() % Card.COMMAND_SIZE != 0)
                return false;
            Card copyOfCurrentSituation = new Card(startingSituation);
            for (String subCommand: combination.split("(?<=\\G.{2})"))
                copyOfCurrentSituation.executeCommand(subCommand);
            return copyOfCurrentSituation.equals(goalSituation);
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Retrieves the input from the player.
     * @return A pair containing the nickname of the player and the combination input by the player.
     * If the player did not input a combination, the second element of the pair is an empty string
     */
    private Pair<String> getInputFromPlayer(){
        String enteredLine = playerInputScanner.nextLine();
        Scanner input = new Scanner(enteredLine);
        String player = input.next();
        String combination = "";
        if (input.hasNext())
            combination = input.next();
        input.close();
        return new Pair<>(player, combination);
    }

    /**
     * String representation of the display at the start of each turn, with the current situation, the goal situation
     * and the supported commands.
     */
    @Override
    public String toString() {
        GameDisplayBuilder builder = new GameDisplayBuilder(this);
        return builder.getDisplay();
    }
}
