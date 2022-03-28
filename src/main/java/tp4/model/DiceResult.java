package tp4.model;

import java.util.*;
import java.util.stream.Collectors;

public class DiceResult {

    final List<Integer> faces;

    private DiceResult(List<Integer> _faces){
        faces = _faces;
    }

    public static DiceResult fromIntegers(List<Integer> _faceValues){
        return new DiceResult(_faceValues);
    }

    public static DiceResult fromDices(List<Die> dices){
        List<Integer> _faces = dices.stream().map(Die::value).collect(Collectors.toList());

        return new DiceResult(_faces);
    }

    public int get(int die){
        return faces.get(die);
    }
    public int size(){ return faces.size();}

    public int sum(){
        int buf = 0;
        for (Integer integer : faces) {
            buf += integer;
        }

        return buf;
    }

    public int count(int faceValue){
        return (int)faces.stream().filter(diceValue -> diceValue == faceValue).count();
    }

    public int countConsecutiveValues(){

        int count = 0;
        List<Integer> sortedBuff = new HashSet<>(faces.stream().sorted().toList()) .stream().toList();

        for(int i = 0; i < sortedBuff.size(); i++){
            if(sortedBuff.get(i) == i+1){
                count++;
            }
        }

        return count;
    }
}
