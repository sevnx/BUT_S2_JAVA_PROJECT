public class Card {
    private final AnimalSituation situation;
    private final int commandSize;

    public Card(AnimalSituation situation, int commandSize) {
        this.situation = situation;
        this.commandSize = commandSize;
    }

    public Card(Card other) {
        this.situation = new AnimalSituation(other.situation);
        this.commandSize = other.commandSize;
    }

    public AnimalSituation getSituation() {
        return situation;
    }

    public int getCommandSize() {
        return commandSize;
    }

    public void executeCommand(AnimalStack blueStack, AnimalStack redStack) {
        if (commandSize != 2) {
            throw new UnsupportedOperationException("Command size must be 2.");
        }

        Animal blueTop = blueStack.getTop();
        Animal redTop = redStack.getTop();
        Animal blueBottom = blueStack.getBottom();
        Animal redBottom = redStack.getBottom();

        AnimalSituation startingLocation = situation.getStartingLocation();
        AnimalSituation arrivalSituation = situation.getArrivalSituation();

        switch (startingLocation) {
            case BLUE_TOP:
                switch (arrivalSituation) {
                    case RED_TOP:
                        redStack.addAtEnd(blueTop);
                        blueStack.removeAtEnd();
                        break;
                    case BLUE_TOP:
                        throw new UnsupportedOperationException("Cannot move to the same starting location.");
                    case BLUE_BOTTOM:
                        blueStack.removeAtEnd();
                        blueStack.addAtStart(blueTop);
                        break;
                    case RED_BOTTOM:
                        throw new UnsupportedOperationException("Invalid movement from blue top to red bottom.");
                }
                break;
            case BLUE_BOTTOM:
                switch (arrivalSituation) {
                    case RED_TOP:
                        redStack.addAtEnd(blueBottom);
                        blueStack.removeAtStart();
                        break;
                    case BLUE_TOP:
                        blueStack.removeAtStart();
                        blueStack.addAtEnd(blueBottom);
                        break;
                    case BLUE_BOTTOM:
                        throw new UnsupportedOperationException("Cannot move to the same starting location.");
                    case RED_BOTTOM:
                        throw new UnsupportedOperationException("Invalid movement from blue bottom to red bottom.");
                }
                break;
            case RED_TOP:
                switch (arrivalSituation) {
                    case RED_TOP:
                        throw new UnsupportedOperationException("Cannot move to the same starting location.");
                    case BLUE_TOP:
                        blueStack.addAtEnd(redTop);
                        redStack.removeAtEnd();
                        break;
                    case BLUE_BOTTOM:
                        throw new UnsupportedOperationException("Invalid movement from red top to blue bottom.");
                    case RED_BOTTOM:
                        redStack.removeAtEnd();
                        redStack.addAtStart(redTop);
                        break;
                }
                break;
            case RED_BOTTOM:
                switch (arrivalSituation) {
                    case RED_TOP:
                        redStack.addAtEnd(redBottom);
                        redStack.removeAtStart();
                        break;
                    case BLUE_TOP:
                        throw new UnsupportedOperationException("Invalid movement from red bottom to blue top.");
                    case BLUE_BOTTOM:
                        blueStack.addAtEnd(redBottom);
                        redStack.removeAtStart();
                        break;
                    case RED_BOTTOM:
                        throw new UnsupportedOperationException("Cannot move to the same starting location.");
                }
                break;
            default:
                throw new UnsupportedOperationException("Invalid starting location.");
        }
    }

    @Override
    public String toString() {
        return situation.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Card)) {
            return false;
        }
        Card otherCard = (Card) other;
        return this.situation.equals(otherCard.situation)
                && this.commandSize == otherCard.commandSize;
    }

    public void executeOrder(AnimalStack blueStack, AnimalStack redStack, AnimalSituation situation) {
    Animal blueTop = blueStack.getTop();
    Animal redTop = redStack.getTop();
    Animal blueBottom = blueStack.getBottom();
    Animal redBottom = redStack.getBottom();

        switch (commandSize) {
            case 1:
                if (situation == AnimalSituation.BLUE_TOP_TO_RED_TOP) {
                    // KI : L'animal se trouvant en haut de la pile du podium bleu saute pour rejoindre le sommet de la pile
                    // du podium rouge.
                    redStack.addAtEnd(blueStack.getTop());
                    blueStack.removeAtEnd();
                } else if (situation == AnimalSituation.RED_TOP_TO_BLUE_TOP) {
                    // LO : L'animal se trouvant en haut de la pile du podium rouge saute pour rejoindre le sommet de la pile
                    // du podium bleu.
                    blueStack.addAtEnd(redStack.getTop());
                    redStack.removeAtEnd();
                } else if (situation == AnimalSituation.BLUE_BOTTOM_TO_BLUE_TOP) {
                    // NI : L'animal se trouvant en bas de la pile du podium bleu monte et se place en haut de la pile de ce
                    // même podium.
                    blueStack.addAtEnd(blueBottom);
                    blueStack.removeAtStart();
                } else if (situation == AnimalSituation.RED_BOTTOM_TO_RED_TOP) {
                    // MA : L'animal se trouvant en bas de la pile du podium rouge monte et se place en haut de la pile de
                    // ce même podium.
                    redStack.addAtEnd(redBottom);
                    redStack.removeAtStart();
                }
                break;
            case 2:
                if (situation == AnimalSituation.BOTH_TOP_TO_BOTH_TOP) {
                    // SO : Les deux animaux se trouvant au sommet des piles des deux podiums échangent leur place.
                    blueStack.addAtEnd(redTop);
                    redStack.removeAtEnd();
                    redStack.addAtEnd(blueTop);
                    blueStack.removeAtEnd();
                }
                break;
            default:
                break;
        }
    }
}