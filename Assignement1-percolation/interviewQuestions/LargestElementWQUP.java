/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

public class LargestElementWQUP implements UF, StatusReporter {

    private int[] components;
    private int[] componentSizes;
    private int[] largestElements;

    private int nComponents;

    public LargestElementWQUP(int n) {
        nComponents = n;
        components = new int[n];
        componentSizes = new int[n];
        largestElements = new int[n];

        for (int i = 0; i < n; i++) {
            components[i] = i;
            largestElements[i] = i;

            componentSizes[i] = 1;
        }

    }

    public int find(int p) {
        while (components[p] != p) {
            components[p] = components[components[p]];
            p = components[p];
        }
        return p;
    }

    public int findLargest(int p) {
        int q = find(p);
        return largestElements[q];

    }

    public int getElementById(int id) {
        return components[id];
    }

    public void union(int p, int q) {

        if (p == q) return;
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }
        int winner = qRoot;
        int loser = pRoot;
        if (componentSizes[pRoot] > componentSizes[qRoot]) {
            winner = pRoot;
            loser = qRoot;
        }
        components[loser] = winner;
        componentSizes[winner] += componentSizes[loser];

        largestElements[winner] = (largestElements[winner] > largestElements[loser]) ?
                                  largestElements[winner] : largestElements[loser];
        nComponents--;


    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public boolean isFullyConnected() {
        return nComponents == 1;
    }


    public int getNumberOfComponents() {
        return nComponents;
    }

    public int getSize() {
        return this.components.length;
    }

    @Override
    public void printStatus() {
        StatusReporter.super.printStatus();
        StringBuilder top = new StringBuilder();
        top.append("<<[] ");
        for (int i = 0; i < this.getSize(); i++) {
            top.append(largestElements[i] + " ");
        }
        System.out.println(top);
        System.out.println("***************************************************");


    }

    public static void main(String[] args) {
        int n = 10;
        LargestElementWQUP network = new LargestElementWQUP(n);
        network.union(0, 1);
        network.printStatus();
        network.union(1, 2);
        network.printStatus();
        System.out.println(network.find(2));
        System.out.println(network.findLargest(2));
        System.out.println(network.findLargest(1));


    }
}
