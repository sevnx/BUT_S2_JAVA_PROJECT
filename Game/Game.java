package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.Game.java
 * @brief Class representing the game.
 */

import cards.DeckOfCards;
import color.Color;
import cards.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {
    private static final String SECRET_STRING = "%^&*</=#@";
    private static final int MIN_PLAYERS = 2;
    private final ArrayList<Player> players;
    private boolean currentTurnSituationFound;
    private Card currentSituation;
    private Card goalSituation;
    private final Scanner playerInputScanner;
    private final DeckOfCards deck;
    public Game(String[] nicknames) {
        verifyNicknames(nicknames);
        players = new ArrayList<>(nicknames.length);
        for (String nickname : nicknames)
            players.add(new Player(nickname));
        deck = new DeckOfCards();
        currentSituation=deck.pickRandomCard();
        currentTurnSituationFound=false;
        playerInputScanner = new Scanner(System.in);
    }

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

    private boolean hasDoubleNicknames(String[] nicknames){
        for (int i = 0; i < nicknames.length; i++) {
            for (int j = i+1; j < nicknames.length; j++) {
                if (nicknames[i].equals(nicknames[j]))
                    return true;
            }
        }
        return false;
    }

    public void play() {
        pickStartOfGameSituation();
        while (!deck.isEmpty()){
            turn();
        }
        endOfGame();
    }

    public void turn(){
        setupStartOfTurn();
        System.out.println(this);
        while (!hasAllButOnePlayerLostCurrentTurn() && !currentTurnSituationFound){
            Pair<String> playerInput = getCombination();
            verifyCombinationInput(playerInput.getFirst(), playerInput.getSecond());
        }
        changeCurrentSituationToGoalSituation();
    }

    private void changeCurrentSituationToGoalSituation(){
        currentSituation=goalSituation;
    }

    private void pickStartOfGameSituation(){
        currentSituation=deck.pickRandomCard();
    }

    private void setupStartOfTurn(){
        resetCurrentTurnStatusForEveryPlayer();
        resetCurrentSituationFound();
        pickTurnGoalSituation();
    }

    private void pickTurnGoalSituation(){
        goalSituation=deck.pickRandomCard();
    }

    private void resetCurrentSituationFound(){
        currentTurnSituationFound=false;
    }

    private void resetCurrentTurnStatusForEveryPlayer(){
        for (Player p : players)
            p.resetCurrentTurnLost();
    }

    private boolean hasAllButOnePlayerLostCurrentTurn(){
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

    private void displayAllButOnePlayerLostCurrentTurn(String playerNickname){
        System.out.print(Color.coloredString(Color.ANSI.GREEN, "Tous les joueurs ont perdu sauf "));
        System.out.print(Color.coloredString(Color.ANSI.YELLOW, playerNickname));
        System.out.println(Color.coloredString(Color.ANSI.GREEN," qui remporte le tour. Félicitations!"));
    }

    public void endOfGame(){
        assert(deck.isEmpty());
        sortPlayersByScoreThenByName();
        endOfGameDisplay();
    }

    public void endOfGameDisplay(){
        int rank=1;
        for (Player p : players) {
            System.out.print((rank++) + ". ");
            System.out.println(p);
        }
    }

    public Card getCurrentSituation(){
        return currentSituation;
    }

    public Card getGoalSituation(){
        return goalSituation;
    }

    private void sortPlayersByScoreThenByName(){
        players.sort(Comparator.comparing(Player::getScore).thenComparing(Player::getNickname));
    }

    private boolean canPlayerPlay(String playerNickname){
        if (isPlayerNonExistent(playerNickname))
            return false;
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return !p.isCurrentTurnLost();
        }
        return false;
    }

    private boolean isPlayerNonExistent(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return false;
        }
        return true;
    }

    private Player getPlayerByNickname(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return p;
        }
        return null;
    }

    private String findCombination(){
        CombinationFinder finder = new CombinationFinder(currentSituation, goalSituation);
        return finder.findCombination();
    }

    private void displayFoundCombination(String combination){
        System.out.println(Color.coloredString(Color.ANSI.GREEN, "Combinaison trouvée: " + combination));
    }

    private void verifyCombinationInput(String input1, String input2){
        if (isFirstInputSecretString(input1)) {
            displayFoundCombination(findCombination());
            currentTurnSituationFound=true;
        }
        else if (isPlayerNonExistent(input1))
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

    private boolean isFirstInputSecretString(String input){
        return input.equals(SECRET_STRING);
    }

    private boolean checkCombination(String combination){
        try {
            if (combination.length() % Card.COMMAND_SIZE != 0)
                return false;
            Card copyOfCurrentSituation = new Card(currentSituation);
            for (String subCommand: combination.split("(?<=\\G.{2})"))
                copyOfCurrentSituation.executeCommand(subCommand);
            return copyOfCurrentSituation.equals(goalSituation);
        }
        catch (Exception e){
            return false;
        }
    }

    private Pair<String> getCombination(){
        String enteredLine = playerInputScanner.nextLine();
        Scanner input = new Scanner(enteredLine);
        String player = input.next();
        String combination = "";
        if (input.hasNext())
            combination = input.next();
        return new Pair<>(player, combination);
    }

    @Override
    public String toString() {
        GameDisplayBuilder builder = new GameDisplayBuilder(this);
        return builder.getDisplay();
    }
}
