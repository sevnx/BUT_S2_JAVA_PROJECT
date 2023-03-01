package cards; /**
 * @author Seweryn CZYKINOWSKI & Corentin LENCLOS
 * @file Cards.Card.java
 * Class representing a card
 */

import podium.Animal;
import podium.Podium;

import java.util.Objects;

public class Card {
    private final Podium blue;
    private final Podium red;
    public static final int COMMAND_SIZE = 2;
    public static final int NUMBER_OF_PODIUMS = 2;

    public Card(Podium blue, Podium red) {
        this.blue = new Podium(blue);
        this.red = new Podium(red);
    }

    public Card(Card other) {
        this(other.blue, other.red);
    }

    public Podium getRed() {
        return red;
    }

    public Podium getBlue() {
        return blue;
    }

    private void commandKI(){
        red.addAtTop(blue.getTop());
        blue.removeAtTop();
    }

    private void commandLO(){
        blue.addAtTop(red.getTop());
        red.removeAtTop();
    }

    private void commandNI(){
        blue.addAtTop(blue.getBottom());
        blue.removeAtBottom();
    }

    private void commandMA(){
        red.addAtTop(red.getBottom());
        red.removeAtBottom();
    }

    private void commandSO(){
        Animal redTop=red.getTop();
        Animal blueTop=blue.getTop();
        blue.removeAtTop();
        red.removeAtTop();
        blue.addAtTop(redTop);
        red.addAtTop(blueTop);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(red, card.red) && Objects.equals(blue, card.blue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, blue);
    }
}