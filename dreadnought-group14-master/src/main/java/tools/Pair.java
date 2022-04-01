package tools;



// a custom-made pair class that holds two different types of objects
public class Pair<T, Z> {
    T first;
    Z second;

    public Pair() {}

    public Pair(T first, Z second) {
        this.first = first;
        this.second = second;
    }

    T getFirst() {
        return first;
    }

    Z getSecond() {
        return second;
    }

    Pair<T, Z> getPair() {
        return this;
    }
}
