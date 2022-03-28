package tp4.model;

import java.util.ArrayList;
import java.util.List;
import tp4.combinations.*;

public class Game {

    private final List<Die> dice;
    private final List<ScoreRow> rows;
    private int nbRollsLeft;

    public Game(){
        dice = new ArrayList<>();
        for(int i = 0; i < 5; i++) dice.add(new Die());

        rows = new ArrayList<>();

        add(CombinationManager.chance());

        add(CombinationManager.sumOfK(6, "Sixes"));
        add(CombinationManager.sumOfK(5, "Fives"));
        add(CombinationManager.sumOfK(4, "Fours"));
        add(CombinationManager.sumOfK(3, "Threes"));
        add(CombinationManager.sumOfK(2, "Twos"));
        add(CombinationManager.sumOfK(1, "Ones"));


        add(CombinationManager.kStraight(4, "Large Straight"));
        add(CombinationManager.kStraight(3, "Small Straight"));

        add(CombinationManager.kOfAKind(5, "Yahtzee"));
        add(CombinationManager.kOfAKind(4, "Four"));
        add(CombinationManager.kOfAKind(3, "Three"));

        add(CombinationManager.fHouse());



        initialRoll();
    }

    private DiceResult getDiceResult(){
        return DiceResult.fromDices(dice);
    }


    public int getNbRollsLeft() {
        return nbRollsLeft;
    }

    public void roll() {
        if(nbRollsLeft > 0){
            nbRollsLeft--;

            dice.stream().filter(d -> !d.isBlocked()).forEach(Die::roll);
        }
    }

    private void add(Combination combination){
        rows.add(new ScoreRow(combination));
    }

    private void unblockDice(){
        dice.forEach(Die::unblock);
    }

    public void choose(ScoreRow row) {
        row.record(getDiceResult());
    }

    public boolean isOver() {
        for (ScoreRow row : rows) {
            if (row.isAvailable()) {
                return false;
            }
        }
        return true;
    }

    public List<ScoreRow> getRows() {
        return rows;
    }

    public List<Die> getDice() {
        return dice;
    }

    public void initialRoll() {
        nbRollsLeft = 3;
        unblockDice();
        roll();
    }

    public int getTotalScore() {
        int total = 0;
        for(ScoreRow row : rows){
            total += row.score();
        }

        return total;
    }
}
