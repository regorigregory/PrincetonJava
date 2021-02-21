/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation<Item> {

    public static void main(String[] args) {
        int numberOfPermutations = Integer.parseInt(args[0]);
        System.out.println(numberOfPermutations);

        String[] permutableElements = StdIn.readLine().split(" ");
        RandomizedQueue<String> q = new RandomizedQueue<String>(numberOfPermutations);

        for (String s : permutableElements) {
            q.enqueue(s);
        }
        System.out.println(q.size());
        Iterator<String> it = q.iterator();
        int i = 1;
        while (i < numberOfPermutations && it.hasNext()) {
            StdOut.println(it.next());
        }
    }
}
