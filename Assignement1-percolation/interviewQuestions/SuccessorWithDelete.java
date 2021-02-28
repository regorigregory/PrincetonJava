/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

public class SuccessorWithDelete {
    interviewQuestions.LargestElementWQUP wqup;
    boolean lastRemoved;

    public SuccessorWithDelete(int n) {
        wqup = new LargestElementWQUP(n);

    }

    public void remove(int p) {
        if (p > wqup.getSize()) throw new IllegalArgumentException();
        if (p == wqup.getSize() - 1) {
            lastRemoved = true;
        }
        else {
            wqup.union(p, p + 1);

        }
    }

    public int successor(int p) {
        int temp = wqup.findLargest(p);
        if (temp == wqup.getSize() - 1 && lastRemoved) {
            return -1;
        }
        return temp;

    }

    public void printStatus() {
        this.wqup.printStatus();
    }

    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(10);
        System.out.println(swd.successor(2));
        //swd.printStatus();
        swd.remove(2);
        swd.remove(3);
        swd.remove(5);
        swd.remove(7);
        swd.remove(9);

        swd.printStatus();

        System.out.println(swd.successor(3));
        swd.remove(4);
        System.out.println(swd.successor(3));
        swd.remove(6);
        System.out.println(swd.successor(3));
        System.out.println(swd.successor(9));
        System.out.println(swd.successor(8));
        swd.remove(8);
        System.out.println(swd.successor(8));

        swd.printStatus();


    }
}
