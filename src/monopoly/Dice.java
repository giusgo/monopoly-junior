package monopoly;

import java.util.Random;

public class Dice {

    private int result;

    public Dice() {

        this.result = 0;

    }

    public int roll() {

        Random random = new Random();

        int number = random.nextInt(6) + 1;

        setResult(number);
        
        return number;

    }

    public int getResult() {

        return this.result;
    }

    public void setResult(int result) {

        this.result = result;
    }



    
}
