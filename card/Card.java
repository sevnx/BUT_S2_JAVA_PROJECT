package card;

import podiums.Animal;
import podiums.Podium;

import java.util.Objects;

/**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Cards.Card.java
 * @brief Class representing a card, also called a situation
 */
public class Card {
    /** Blue podiums (first / left one) */
    private final Podium blue;
    /** Red podiums (second / right one) */
    private final Podium red;
    /** Size of a command */
    public static final int COMMAND_SIZE = 2;

    /**
     * Constructor of Card class
     * @param blue Blue podiums
     * @param red Red podiums
     */
    public Card(Podium blue, Podium red) {
        this.blue = new Podium(blue);
        this.red = new Podium(red);
    }

    /**
     * Copy constructor of Card class
     * @param other Card to copy
     */
    public Card(Card other) {
        this(other.blue, other.red);
    }

    /**
     * Getter of blue podiums
     * @return Blue podiums
     */
    public Podium getRed() {
        return red;
    }

    /**
     * Getter of red podiums
     * @return Red podiums
     */
    public Podium getBlue() {
        return blue;
    }

    /**
     * Execute a command, calling the appropriate method
     * @param command Command to execute
     */
    public void executeCommand(String command) {
        if (command.length() != 2)
            throw new RuntimeException("Command size must be 2");
        switch (command.toUpperCase()){
            case "KI":
                commandKI();
                break;
            case "LO":
                commandLO();
                break;
            case "SO":
                commandSO();
                break;
            case "NI":
                commandNI();
                break;
            case "MA":
                commandMA();
                break;
            default:
                break;
        }
    }

    /**
     * Executes the KI command that moves the top animal of the blue podiums to the top of the red podiums
     */
    private void commandKI(){
        red.addAtTop(blue.getTop());
        blue.removeAtTop();
    }

    /**
     * Executes the LO command that moves the top animal of the red podiums to the top of the blue podiums
     */
    private void commandLO(){
        blue.addAtTop(red.getTop());
        red.removeAtTop();
    }

    /**
     * Executes the NI command that moves the bottom animal of the blue podiums to the top of the blue podiums
     */
    private void commandNI(){
        blue.addAtTop(blue.getBottom());
        blue.removeAtBottom();
    }

    /**
     * Executes the MA command that moves the bottom animal of the red podiums to the top of the red podiums
     */
    private void commandMA(){
        red.addAtTop(red.getBottom());
        red.removeAtBottom();
    }

    /**
     * Executes the SO command that swaps the top animals of the blue and red podiums
     */
    private void commandSO(){
        Animal redTop=red.getTop();
        Animal blueTop=blue.getTop();
        blue.removeAtTop();
        red.removeAtTop();
        blue.addAtTop(redTop);
        red.addAtTop(blueTop);
    }

    /**
     * Checks if the KI command can be executed
     * @see Card#commandKI()
     * @return True if the command can be executed, false otherwise
     */
    public boolean canKI(){
        return !blue.isEmpty();
    }

    /**
     * Checks if the LO command can be executed
     * @see Card#commandLO()
     * @return True if the command can be executed, false otherwise
     */
    public boolean canLO(){
        return !red.isEmpty();
    }

    /**
     * Checks if the NI command can be executed
     * @see Card#commandNI()
     * @return True if the command can be executed, false otherwise
     */
    public boolean canNI(){
        return !blue.isEmpty() && blue.getSize()>1;
    }

    /**
     * Checks if the MA command can be executed
     * @see Card#commandMA()
     * @return True if the command can be executed, false otherwise
     */
    public boolean canMA(){
        return !red.isEmpty() && red.getSize()>1;
    }

    /**
     * Checks if the SO command can be executed
     * @see Card#commandSO()
     * @return True if the command can be executed, false otherwise
     */
    public boolean canSO(){
        return !red.isEmpty() && !blue.isEmpty();
    }

    /**
     * Checks if card are equal, which means that they have the same podiums
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(red, card.red) && Objects.equals(blue, card.blue);
    }

    /**
     * Hashcode of a card
     */
    @Override
    public int hashCode() {
        return Objects.hash(red, blue);
    }
}
