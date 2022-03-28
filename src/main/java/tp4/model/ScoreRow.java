package tp4.model;

public class ScoreRow {

    private int score;
    private boolean isFilled;
    private final Combination combination;


    public ScoreRow(Combination comb){
        combination = comb;
        isFilled = false;
        score = 0;
    }

    public boolean isAvailable() {
        return !isFilled;
    }

    public int score() {
        return score;
    }


    public void record(DiceResult result) {
        if(isAvailable()){
            score = combination.score(result);
            isFilled = true;
        }
    }

    public String description() {
        return combination.description();
    }

    public void reset() {
        score = 0;
        isFilled = false;
    }
}
