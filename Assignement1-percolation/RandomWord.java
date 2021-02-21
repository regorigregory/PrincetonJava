/* *****************************************************************************
 *  Name:              Gergo Endresz
 *  Coursera User ID:  0b98e7597a04720d1abd8d83dbdbc35a
 *  Last modified:      February 1, 2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        String champ = StdIn.readString();
        int i = 1;
        while (!StdIn.isEmpty()) {
            i++;
            double p = 1.0 / (double) i;
            boolean isNewChamp = StdRandom.bernoulli();
            String candidateChamp = StdIn.readString();
            champ = isNewChamp ? candidateChamp : champ;
        }
        StdOut.println(champ);


    }

}
