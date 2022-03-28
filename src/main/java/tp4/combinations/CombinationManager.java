package tp4.combinations;

import tp4.model.Combination;
import tp4.model.DiceResult;

import java.util.ArrayList;
import java.util.List;

public class CombinationManager {

    //avec des classes statiques ici ça semble fonctionner aussi, mais, pour la "lisibilité" je préfère mettre à part les classes

    public static Chance chance(){
        return new Chance();
    }

    public static KOfAKind kOfAKind(int k, String description){
        return new KOfAKind(k, description);
    }

    public static SumOfK sumOfK(int k, String description){
        return new SumOfK(k, description);
    }


    public static KStraight kStraight(int k, String description){
        return new KStraight(k, description);
    }


    public static FHouse fHouse(){
        return new FHouse();
    }
}

class SumOfK implements Combination{

    private final String description;
    private final int k;

    protected SumOfK(int _k, String _description){
        k = _k;
        description = _description;
    }

    @Override
    public int score(DiceResult result){
        return k*result.count(k);
    }

    @Override
    public String description(){
        return "Sum of " + description;
    }
}

class KOfAKind implements Combination{
    private final String description;
    private final int k;

    protected KOfAKind(int _k, String _description){
        k = _k;
        description = _description;
    }

    @Override
    public int score(DiceResult result) {
        for(int i = 0; i <= result.size(); i++){
            if(result.count(i) >= k) {
                if(k==5){return 50;}
                return result.sum();
            }
        }

        return 0;
    }

    @Override
    public String description(){
        if(k == 5){
            return "Amuhtzee";
        }
        return description + "-of-a-kind";
    }
}

class KStraight implements Combination{
    private final int k;
    private final String description;

    protected KStraight(int _k, String _description){
        k = _k;
        description = _description;
    }
    @Override
    public int score(DiceResult result) {
        if(result.countConsecutiveValues() >= k){
            return 10*k;
        }
        return 0;
    }

    @Override
    public String description() {
        return description;
    }
}

class Chance implements Combination {

    @Override
    public int score(DiceResult result) {
        return result.sum();
    }

    @Override
    public String description() {
        return "Chance";
    }
}


class FHouse implements Combination {

    @Override
    public int score(DiceResult result) {
        List<Integer> buf = new ArrayList<>();

        for (int x = 0; x < 2; x++){
            for(int i = 0; i <= result.size(); i++){
                if(((result.count(i) == 3) && !buf.contains(i)) || ((result.count(i) == 2) && !buf.contains(i))){
                    buf.add(i);
                }
            }
        }

        if(buf.size() > 1) return 25;


        return 0;
    }

    @Override
    public String description() {
        return "Full House";
    }
}



