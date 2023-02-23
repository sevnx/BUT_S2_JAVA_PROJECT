import java.util.Deque;
import java.util.LinkedList;

public class Podium {
    private Deque<Animal> animals; // The pile of animals

    public Podium() {
        this.animals = new LinkedList<Animal>();
    }

    public void addAtEnd(Animal animal) {
        animals.addLast(animal);
    }

    public void removeAtEnd() {
        animals.removeLast();
    }

    public void removeAtStart() {
        animals.removeFirst();
    }

    public Animal getTop() {
        return animals.peekLast();
    }

    public Animal getBottom() {
        return animals.peekFirst();
    }
}