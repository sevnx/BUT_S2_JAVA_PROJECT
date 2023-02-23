public class Card {
    private final AnimalSituation startingLocation;
    private final AnimalSituation arrivalSituation;
    private final int commandSize;

    public Card(AnimalSituation startingLocation, AnimalSituation arrivalSituation, int commandSize) {
        this.startingLocation = startingLocation;
        this.arrivalSituation = arrivalSituation;
        this.commandSize = commandSize;
    }

    public Card(Card other) {
        this.startingLocation = new AnimalSituation(other.startingLocation);
        this.arrivalSituation = new AnimalSituation(other.arrivalSituation);
        this.commandSize = other.commandSize;
    }

    public AnimalSituation getStartingLocation() {
        return startingLocation;
    }

    public AnimalSituation getArrivalSituation() {
        return arrivalSituation;
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
        return "Départ:\n" + startingLocation.toString() + "\nArrivée:\n" + arrivalSituation.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Card)) {
            return false;
        }
        Card otherCard = (Card) other;
        return this.startingLocation.equals(otherCard.startingLocation)
                && this.arrivalSituation.equals(otherCard.arrivalSituation)
                && this.commandSize == otherCard.commandSize;
    }

    public void executeOrder(AnimalStack blueStack, AnimalStack redStack) {
        Animal blueTop = blueStack.getTop();
        Animal redTop = redStack.getTop();
        Animal blueBottom = blueStack.getBottom();
        Animal redBottom = redStack.getBottom();

        switch (commandSize) {
            case 1:
                if (startingLocation == AnimalSituation.BLUE_TOP && arrivalSituation == AnimalSituation.RED_TOP) {
                    // KI : L'animal se trouvant en haut de la pile du podium bleu saute pour rejoindre le sommet de la pile
                    // du podium rouge.
                    redStack.addAtEnd(blueStack.getTop());
                    blueStack.removeAtEnd();
                } else if (startingLocation == AnimalSituation.RED_TOP && arrivalSituation == AnimalSituation.BLUE_TOP) {
                    // LO : L'animal se trouvant en haut de la pile du podium rouge saute pour rejoindre le sommet de la pile
                    // du podium bleu.
                    blueStack.addAtEnd(redStack.getTop());
                    redStack.removeAtEnd();
                } else if (startingLocation == AnimalSituation.BLUE_BOTTOM && arrivalSituation == AnimalSituation.BLUE_TOP) {
                    // NI : L'animal se trouvant en bas de la pile du podium bleu monte et se place en haut de la pile de ce
                    // même podium.
                    blueStack.addAtEnd(blueBottom);
                    blueStack.removeAtStart();
                } else if (startingLocation == AnimalSituation.RED_BOTTOM && arrivalSituation == AnimalSituation.RED_TOP) {
                    // MA : L'animal se trouvant en bas de la pile du podium rouge monte et se place en haut de la pile de
                    // ce même podium.
                    redStack.addAtEnd(redBottom);
                    redStack.removeAtStart();
                }
                break;
            case 2:
                if (startingLocation == AnimalSituation.BOTH_TOP && arrivalSituation == AnimalSituation.BOTH_TOP) {
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