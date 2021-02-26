/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Scanner;

public class WeightedQuickUnionPath implements DynamicConnectivityObject {
    private int[] id;
    private int[] componentSizes;
    private int unionLoopCounter;
    private int findLoopCounter;


    public WeightedQuickUnionPath(int n) {
        id = new int[n];
        componentSizes = new int[n];
        findLoopCounter = 0;
        unionLoopCounter = 0;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            componentSizes[i] = 1;
        }
    }

    private int findRoot(int q) {
        findLoopCounter++;

        while (id[q] != q) {
            findLoopCounter++;
            id[q] = id[id[q]];
            q = id[q];
        }
        return q;
    }

    public void union(int a, int b) {
        if (a == b) return;

        unionLoopCounter++;
        int aid = findRoot(a);
        int bid = findRoot(b);

        if (componentSizes[aid] > componentSizes[bid]) {
            componentSizes[aid] += componentSizes[bid];
            id[bid] = aid;
        }
        else {
            componentSizes[bid] += componentSizes[aid];
            id[aid] = bid;

        }

    }

    public boolean isConnected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }


    public int find(int a) {
        findLoopCounter++;
        return id[a];
    }

    public int count() {
        return id.length;
    }

    public void printNumberOfUnionLoops() {
        System.out.println("Number of union loops: " + unionLoopCounter);
    }

    public void printNumberOfFindLoops() {
        System.out.println("Number of find calls: " + findLoopCounter);

    }


    public static void main(String[] args) throws java.io.FileNotFoundException {
        // Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new java.io.File("tinyUF.txt"));

        int N = sc.nextInt();
        int unionOperations = 0;

        DynamicConnectivityObject dq = new WeightedQuickUnionPath(N);

        System.out.println("Number of elements: " + N);

        while (sc.hasNextInt()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            System.out.printf("Next union: %d, %d\n", p, q);

            if (!dq.isConnected(p, q)) {
                dq.union(p, q);
                unionOperations++;
            }
            dq.printStatus();

        }
        dq.printNumberOfFindLoops();
        dq.printNumberOfUnionLoops();
        System.out.println("Number of elements: " + N);
        System.out.println("Number of union requests: " + unionOperations);


    }

}
