import java.util.ArrayList;

public class Podium {
    private final ArrayList<Animal> animals; // The pile of animals
    public static int TOP_ROW = 2;
    public static int MIDDLE_ROW = 1;
    public static int BOTTOM_ROW = 0;

    public Podium(ArrayList<Animal> animals){
        this.animals = new ArrayList<>(animals);
    }

    public Podium(Podium other){
        this.animals = new ArrayList<>(other.animals);
    }

    public void addAtTop(Animal animal) {
        animals.add(animal);
    }

    public void removeAtTop() {
        animals.remove(animals.size()-1);
    }

    public void removeAtBottom() {
        animals.remove(0);
    }

    public String getAnimalStringByIndex(int index){
        if (index<0 || index>=animals.size())
            return "\t";
        return animals.get(index).toString();
    }

    public Animal getTop() {
        return animals.get(animals.size()-1);
    }

    public Animal getBottom() {
        return animals.get(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Animal animal : animals)
            sb.append(animal.toString());
        return sb.toString();
    }
}