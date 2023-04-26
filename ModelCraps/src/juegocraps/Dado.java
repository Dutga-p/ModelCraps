package juegocraps;

import java.util.Random;

/**
 *Class generate a random number between 1 to 6
 * @author David Camilo Ordoñez Marin 2226057
 * @version v.1.0.0 date 18/04/2022
 */

public class Dado {
    private int cara;

    /**
     * Method that generate a random number to dado face
     * @return number between(1,6)
     */
    public int getCara() {
        Random randomNumber = new Random();
        cara = randomNumber.nextInt(6)+1;
        return cara;
    }
}