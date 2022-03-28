package tp4.model;

import java.util.Random;

public class Die {

    private static final Random generator = new Random();
    private int faceValue;
    private boolean isBlocked;



    public void roll() {
        faceValue = generator.nextInt(1,7);
    }

    public int value() {
        return faceValue;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void block() {
        isBlocked = true;
    }

    public void unblock() {
        isBlocked = false;
    }

}
