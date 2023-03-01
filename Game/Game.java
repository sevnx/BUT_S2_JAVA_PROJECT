package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.Game.java
 * Class representing the game.
 */

import cards.DeckOfCards;
import podium.Podium;
import cards.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Game {
    private static final int MIN_PLAYERS = 2;
    private final ArrayList<Player> players;
    private boolean currentTurnSituationFound;
    private Card currentSituation;
    private Card goalSituation;
    private final Scanner playerInputScanner;
    private final DeckOfCards deck;
    public Game(String[] nicknames) {
        assert(nicknames.length >= MIN_PLAYERS); // TODO: throw exception
        players = new ArrayList<>(nicknames.length);
        for (String nickname : nicknames)
            players.add(new Player(nickname));
        deck = new DeckOfCards();
        currentSituation=deck.pickRandomCard();
        currentTurnSituationFound=false;
        playerInputScanner = new Scanner(System.in);
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
        displaySupportedCommands();
        while (!hasEveryPlayerLostCurrentTurn() && !currentTurnSituationFound){
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

    private boolean hasEveryPlayerLostCurrentTurn(){
        for (Player p : players){
            if (!p.isCurrentTurnLost())
                return false;
        }
        return true;
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

    private void sortPlayersByScoreThenByName(){
        players.sort(Comparator.comparing(Player::getScore).thenComparing(Player::getNickname));
    }

    private boolean canPlayerPlay(String playerNickname){
        if (!doesPlayerExist(playerNickname))
            return false;
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return !p.isCurrentTurnLost();
        }
        return false;
    }

    private boolean doesPlayerExist(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return true;
        }
        return false;
    }

    private Player getPlayerByNickname(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return p;
        }
        return null;
    }

    private void verifyCombinationInput(String playerNickname, String combination){
        if (!doesPlayerExist(playerNickname))
            CombinationInputState.displayCombinationInputState(CombinationInputState.NON_EXISTENT_PLAYER);
        else if (!canPlayerPlay(playerNickname))
            CombinationInputState.displayCombinationInputState(CombinationInputState.CANNOT_PLAY);
        else if (!checkCombination(playerNickname, combination)){
            CombinationInputState.displayCombinationInputState(CombinationInputState.INVALID_INPUT);
            getPlayerByNickname(playerNickname).loseTurn();
        }
        else {
            currentTurnSituationFound=true;
            getPlayerByNickname(playerNickname).incrementScore();
        }
    }

    private boolean checkCombination(String playerNickname, String combination){
        try {
            if (combination.length() % Card.COMMAND_SIZE != 0)
                return false;
            Card copyOfCurrentSituation = new Card(currentSituation);
            for (int i = 0; i < combination.length() / Card.COMMAND_SIZE; i++) {
                String subCommand = getSubCommand(combination, i);
                copyOfCurrentSituation.executeCommand(subCommand);
            }
            return copyOfCurrentSituation.equals(goalSituation);
        }
        catch (Exception e){
            return false;
        }
    }

    private Pair<String> getCombination(){
        String player = playerInputScanner.next();
        String combination = playerInputScanner.next();
        return new Pair<>(player, combination);
    }

    private void displaySupportedCommands(){
        System.out.println("KI : BLEU --> ROUGE\t\tNI : BLEU ^");
        System.out.println("LO : BLEU <-- ROUGE\t\tMA : ROUGE ^");
        System.out.println("SO : BLEU <-> ROUGE");
    }

    @Override
    public String toString() { // TODO: help
        return getRow(Podium.TOP_ROW) + System.lineSeparator() +
                getRow(Podium.MIDDLE_ROW) + System.lineSeparator() +
                getRow(Podium.BOTTOM_ROW) + System.lineSeparator() +
                "--------   --------   ==>   --------   --------" + System.lineSeparator() +
                "  BLEU       ROUGE             BLEU       ROUGE  " + System.lineSeparator() +
                "-------------------------------------------------" + System.lineSeparator();
    }

    private String getRow(int index) { // TODO: help
        return String.format("%-10s%-10s\t\t%-10s%-10s",
                currentSituation.getBlue().getAnimalStringByIndex(index),
                currentSituation.getRed().getAnimalStringByIndex(index),
                goalSituation.getBlue().getAnimalStringByIndex(index),
                goalSituation.getRed().getAnimalStringByIndex(index)
        );
    }

    private String getSubCommand(String command, int subCommandIndex){
        int commandStart=subCommandIndex* Card.COMMAND_SIZE;
        return command.substring(commandStart,commandStart+ Card.COMMAND_SIZE);
    }
}
