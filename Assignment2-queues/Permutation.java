/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        if (args != null) {
            int numberOfPermutations = Integer.parseInt(args[0]);

            RandomizedQueue<String> q = new RandomizedQueue<String>();

            //for (String s : StdIn.readLine().split(" ")) {
            //  q.enqueue(s);
            //}
            while (!StdIn.isEmpty()) {
                q.enqueue(StdIn.readString());
            }
            Iterator<String> it = q.iterator();
            int i = 0;
            while (i < numberOfPermutations && it.hasNext()) {
                StdOut.println(it.next());
                i++;
            }
        }
    }
}
