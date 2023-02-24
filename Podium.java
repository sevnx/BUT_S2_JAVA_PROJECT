import java.util.ArrayList;

public class Podium {
    private ArrayList<Animal> animals; // The pile of animals

    public Podium() {
        this.animals = new ArrayList<>();
    }

    public Podium(Animal animal){
        this();
        animals.add(animal);
    }

    public Podium(ArrayList<Animal> animals){
        this.animals = animals;
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