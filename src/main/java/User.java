public class User {
    private int wins = 0;
    private int losses = 0;
    private float ratio = 0;


    void setWins(int n) {
        wins = n;
    }

    void setLosses(int n) {
        losses = n;
    }

    void setRatio(float n) {
        ratio = n;
    }

    int getWins() { return wins; }

    int getLosses() { return losses; }

    float getRatio() { return ratio; }
}
