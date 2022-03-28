package tp4.model;

public interface Combination {

    default int score(DiceResult result) {
        return 0;
    }

    default String description() {
        return "combinationName";
    }
}
