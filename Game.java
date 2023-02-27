/**
 * @brief Class representing the game.
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 */

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
        while (!hasEveryPlayerLostCurrentTurn() || !currentTurnSituationFound){
            System.out.println(this);
            displaySupportedCommands();
            askForCombination();
            changeGoalSituationToCurrentSituation();
        }
    }

    private void changeGoalSituationToCurrentSituation(){
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
        int rank=0;
        for (Player p : players) {
            System.out.print((rank++) + ". ");
            System.out.println(p);
        }
    }

    private void sortPlayersByScoreThenByName(){
        players.sort(Comparator.comparing(Player::getScore).thenComparing(Player::getNickname));
    }

    private void checkPlayer(String playerNickname){
        if (!doesPlayerExist(playerNickname))
            throw new IllegalArgumentException("Player does not exist");
        if (!canPlayerPlay(playerNickname))
            throw new IllegalArgumentException("Player cannot play");
    }

    private boolean canPlayerPlay(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return !p.isCurrentTurnLost();
        }
        throw new IllegalStateException("Player does not exist");
    }

    private boolean doesPlayerExist(String playerNickname){
        for (Player p : players){
            if (p.getNickname().equals(playerNickname))
                return true;
        }
        return false;
    }

    private void checkCombination(String playerNickname, String combination){
        checkPlayer(playerNickname);
        assert(combination.length()%Card.COMMAND_SIZE == 0);
        Card copyOfCurrentSituation = new Card(currentSituation);
            for (int i=0;i<combination.length()/Card.COMMAND_SIZE;i++){
                String subCommand=getSubCommand(combination,i);
                copyOfCurrentSituation.executeCommand(subCommand);
        }
        if (copyOfCurrentSituation.equals(goalSituation)){
            currentTurnSituationFound=true;
            for (Player p : players){
                if (p.getNickname().equals(playerNickname))
                    p.incrementScore();
            }
        }
    }

    private void askForCombination() {
        String player = playerInputScanner.next();
        String combination = playerInputScanner.next();
        checkCombination(player, combination);
    }

    private void displaySupportedCommands(){
        System.out.println("KI : BLEU --> ROUGE\t\tNI : BLEU ^");
        System.out.println("LO : BLEU <-- ROUGE\t\tMA : ROUGE ^");
        System.out.println("SO : BLEU <-> ROUGE");
    }

    public String toString(){
        return getRow(Podium.TOP_ROW) +
                System.lineSeparator() +
                getRow(Podium.MIDDLE_ROW) +
                System.lineSeparator() +
                getRow(Podium.BOTTOM_ROW) +
                System.lineSeparator() +
                "----\t----\t==>\t\t----\t----" +
                System.lineSeparator() +
                "BLEU\tROUGE\t\t\tBLEU\tROUGE" +
                System.lineSeparator() +
                "--------------------------------------------" +
                System.lineSeparator();
    }

    private String getRow(int index){
        return currentSituation.getBlue().getAnimalStringByIndex(index)+"\t"
                + currentSituation.getRed().getAnimalStringByIndex(index)+"\t\t"
                + goalSituation.getBlue().getAnimalStringByIndex(index)+"\t"
                + goalSituation.getRed().getAnimalStringByIndex(index);
    }

    private String getSubCommand(String command, int subCommandIndex){
        int commandStart=subCommandIndex*Card.COMMAND_SIZE;
        return command.substring(commandStart,commandStart+Card.COMMAND_SIZE);
    }
}
