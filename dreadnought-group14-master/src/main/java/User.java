public class User {
    private int wins;
    private int losses;
    private float ratio;

    // default constructor initializes all values to zero
    User() {
        this.wins = 0;
        this.losses = 0;
        this.ratio = 0;
    }

    // setters
    void setWins(int n) {
        wins = n;
    }
    void setLosses(int n) {
        losses = n;
    }
    void setRatio(float n) {
        ratio = n;
    }

    // getters
    int getWins() { return wins; }
    int getLosses() { return losses; }
    float getRatio() { return ratio; }
}
