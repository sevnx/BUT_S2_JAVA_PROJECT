/**
 * Represents a player in the game.
 * A player has a nickname, a score and a status indicating if he has lost the current turn
 */
public class Player {
    private final String nickname;
    private int score;
    private boolean currentTurnLost;

    /**
     * Creates a new player with the given nickname.
     * @param nickname the nickname of the player.
     */
    public Player(String nickname){
        this.nickname = nickname;
        score = 0;
        currentTurnLost = false;
    }

    public boolean isCurrentTurnLost(){
        return currentTurnLost;
    }

    /**
     * Resets the current turn lost status of the player.
     */
    public void resetCurrentTurnLost(){
        currentTurnLost = false;
    }

    public void loseTurn(){
        currentTurnLost = true;
    }

    /**
     * Increments the score of the player by 1.
     * @throws AssertionError if the player has lost the current turn.
     */
    public void incrementScore(){
        assert(!currentTurnLost);
        score++;
    }

    @Override
    public String toString(){
        return "Joueur : "+nickname+", Score : "+score;
    }

    public int getScore(){
        return score;
    }

    public String getNickname(){
        return nickname;
    }
}
