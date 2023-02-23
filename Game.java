import java.util.ArrayList;
import java.util.Comparator;

public class Game {
    private static final int MIN_PLAYERS = 2;
    private final ArrayList<Player> players;
    private boolean currentTurnSituationFound;
    private Card currentSituation;
    private Card goalSituation;

    private DeckOfCards deck;
    public Game(String[] nicknames) {
        assert(nicknames.length >= MIN_PLAYERS); // TODO: throw exception
        players = new ArrayList<>(nicknames.length);
        for (String nickname : nicknames)
            players.add(new Player(nickname));
        deck = new DeckOfCards();
        currentSituation=deck.pickRandomCard();
        currentTurnSituationFound=false;
    }

    public void play() {
        while (!deck.isEmpty()){
            turn();
        }
    }

    public void turn(){
        setupStartOfTurn();
        while (!hasEveryPlayerLostCurrentTurn() || !currentTurnSituationFound){
            askForCombination();
        }
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
        for (Player p : players)
            System.out.println(p);
    }

    private void sortPlayersByScoreThenByName(){
        players.sort(Comparator.comparing(Player::getScore).thenComparing(Player::getNickname));
    }

    //TODO: Maybe transfer into another class (that does input output)
    private void checkCombination(String playerNickname, String combination){
        assert(combination%Card::COMBINATION_SIZE == 0); // TODO: change to something more appropriate
    }

    private void askForCombination() {
        // TODO : Whole function
    }

    private void getSubCommand(String command, int subCommandIndex){
        int commandStart=subCommandIndex*Card::COMBINATION_SIZE;
        return command.substring(commandStart,commandStart+Card::COMBINATION_SIZE);
    }
}
