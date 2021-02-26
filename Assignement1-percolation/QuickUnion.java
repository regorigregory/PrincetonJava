/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Scanner;

public class QuickUnion implements DynamicConnectivityObject {
    private int[] id;
    private int unionLoopCounter;
    private int findLoopCounter;


    public QuickUnion(int n) {
        id = new int[n];
        findLoopCounter = 0;
        unionLoopCounter = 0;
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private int findRoot(int q) {
        findLoopCounter++;

        while (id[q] != q) {
            findLoopCounter++;
            q = id[q];
        }
        return q;
    }

    public void union(int a, int b) {
        if (a == b) return;

        unionLoopCounter++;
        int aid = findRoot(a);
        int bid = findRoot(b);
        id[aid] = bid;

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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int unionOperations = 0;

        DynamicConnectivityObject dq = new QuickUnion(N);

        System.out.println("Number of elements: " + N);

        while (sc.hasNextInt()) {
            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;

            if (!dq.isConnected(p, q)) {
                dq.union(p, q);
                unionOperations++;
            }
            System.out.printf("Last union: %d, %d\n", p, q);
            dq.printStatus();

        }
        dq.printNumberOfFindLoops();
        dq.printNumberOfUnionLoops();
        System.out.println("Number of elements: " + N);
        System.out.println("Number of union requests: " + unionOperations);


    }
}
