package game;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Game.Game.java
 * @brief Class representing the game.
 */

import cards.DeckOfCards;
import color.Color;
import podium.Podium;
import cards.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.System.exit;

public class Game {

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
        displaySupportedCommands();
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
        else if (!checkCombination(combination)){
            CombinationInputState.displayCombinationInputState(CombinationInputState.INVALID_INPUT);
            getPlayerByNickname(playerNickname).loseTurn();
        }
        else {
            currentTurnSituationFound=true;
            getPlayerByNickname(playerNickname).incrementScore();
        }
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
    public String toString() {
        return getRow(Podium.TOP_ROW) + System.lineSeparator() +
                getRow(Podium.MIDDLE_ROW) + System.lineSeparator() +
                getRow(Podium.BOTTOM_ROW) + System.lineSeparator() +
                "--------   --------   ==>   --------   --------" + System.lineSeparator() +
                "  "
                +Color.coloredString(Color.ANSI.BLUE, "BLEU")
                +"       "
                +Color.coloredString(Color.ANSI.RED, "ROUGE")
                +"            "
                +Color.coloredString(Color.ANSI.BLUE, "BLEU")
                +"       "
                +Color.coloredString(Color.ANSI.RED, "ROUGE")
                +"  " + System.lineSeparator() +
                "-------------------------------------------------" + System.lineSeparator();
    }


    private String getRow(int index) {
        return String.format("%-10s%-10s      %-10s%-10s",
                currentSituation.getBlue().getAnimalStringByIndex(index),
                currentSituation.getRed().getAnimalStringByIndex(index),
                goalSituation.getBlue().getAnimalStringByIndex(index),
                goalSituation.getRed().getAnimalStringByIndex(index)
        );
    }
}
