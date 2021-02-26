import java.util.Scanner;

public class QuickFind implements DynamicConnectivityObject {
    private int[] id;
    private int unionLoopCounter;
    private int findLoopCounter;


    public QuickFind(int n) {
        id = new int[n];
        findLoopCounter = 0;
        unionLoopCounter = 0;
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean isConnected(int a, int b) {
        return id[a] == id[b];
    }


    public void union(int a, int b) {
        if (a == b) return;

        int aid = id[a];
        int bid = id[b];
        for (int i = 0; i < this.id.length; i++) {
            unionLoopCounter++;

            if (id[i] == aid) id[i] = bid;
        }

    }

    public void printNumberOfUnionLoops() {
        System.out.println("Number of union loops: " + unionLoopCounter);
    }

    public void printNumberOfFindLoops() {
        System.out.println("Number of find calls: " + findLoopCounter);

    }


    public int count() {
        return id.length;
    }

    public int find(int a) {
        findLoopCounter += 1;
        return id[a];
    }

    public static void main(String[] args) {
        Scanner StdIn = new Scanner(System.in);
        int N = StdIn.nextInt();
        int unionOperations = 0;
        DynamicConnectivityObject dq = new QuickFind(N);
        System.out.println("Number of elements: " + N);
        while (StdIn.hasNextInt()) {
            int p = StdIn.nextInt() - 1;
            int q = StdIn.nextInt() - 1;

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
