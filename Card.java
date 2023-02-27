import java.util.Objects;

public class Card {
    private final Podium red;
    private final Podium blue;
    public static final int COMMAND_SIZE = 2;
    public static final int NUMBER_OF_PODIUMS = 2;

    public Card(Podium red, Podium blue) {
        this.red = new Podium(red);
        this.blue = new Podium(blue);
    }

    public Card(Card other) {
        this.red = new Podium(other.red);
        this.blue = new Podium(other.blue);
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
        blue.addAtTop(red.getBottom());
        red.removeAtBottom();
    }

    private void commandMA(){
        red.addAtTop(red.getBottom());
        red.removeAtBottom();
    }

    public Podium getRed() {
        return red;
    }

    public Podium getBlue() {
        return blue;
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
            throw new UnsupportedOperationException("Command size must be 2.");
        switch (command){
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
                throw new IllegalStateException("Unknown command :c");
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "red=" + red +
                ", blue=" + blue +
                '}';
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