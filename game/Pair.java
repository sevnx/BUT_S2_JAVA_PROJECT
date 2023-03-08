package game;

/**
 * Generic pair class
 * @author Seweryn CZYKINOWSKI / Corentin LENCLOS
 * @param <T> type for both values of the pair
 */
public class Pair<T> {
    /** First value of the pair **/
    private final T first;
    /** Second value of the pair **/
    private final T second;

    /**
     * Constructor for Pair class
     * @param first value
     * @param second values
     */
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Getter for first element of Pair
     * @return first element of Pair
     */
    public T getFirst() {
        return first;
    }

    /**
     * Getter for second element of Pairs
     * @return second element of Pair
     */
    public T getSecond() {
        return second;
    }
}
