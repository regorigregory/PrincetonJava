import java.util.Scanner;

public class MyQuickUnion implements DynamicConnectivityObject {
    private class IDHolder {
        private int id;

        IDHolder(int id) {
            this.id = id;
        }

        int getId() {
            return this.id;
        }

        void setId(int newId) {
            this.id = newId;
        }

        boolean equals(IDHolder other) {
            return this.id == other.getId();

        }
    }

    private IDHolder[] id;
    private int[] componentSizes;
    private int unionLoopCounter;
    private int findLoopCounter;

    public MyQuickUnion(int n) {
        id = new IDHolder[n];
        componentSizes = new int[n];
        findLoopCounter = 0;
        unionLoopCounter = 0;
        for (int i = 0; i < n; i++) {
            id[i] = new IDHolder(i);
            componentSizes[i] = 1;
        }
    }

    private int findRoot(int q) {
        findLoopCounter++;
        IDHolder newId = new IDHolder(q);
        while (id[q].getId() != q) {
            findLoopCounter++;
            //int newQ = id[id[q].getId()].getId();
            int newQ = id[q].getId();
            id[q] = newId;
            q = newQ;
        }
        id[q] = newId;
        newId.setId(q);
        return q;
    }

    public void union(int a, int b) {
        unionLoopCounter++;
        if (a == b) return;
        int aid = findRoot(a);
        int bid = findRoot(b);

        if (componentSizes[aid] > componentSizes[bid]) {
            componentSizes[aid] += componentSizes[bid];
            id[bid] = id[aid];
        }
        else {
            componentSizes[bid] += componentSizes[aid];
            id[aid] = id[bid];

        }

    }

    public boolean isConnected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }


    public int find(int a) {
        findLoopCounter++;
        return id[a].getId();
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
        Scanner sc = new Scanner(new java.io.File("snake101.txt"));
        int N = sc.nextInt();
        int unionOperations = 0;

        DynamicConnectivityObject dq = new MyQuickUnion(N);

        System.out.println("Number of elements: " + N);
        int x = 0;
        while (sc.hasNextInt()) {
            x++;

            int p = sc.nextInt() - 1;
            int q = sc.nextInt() - 1;
            if (p == q) continue;
            if (p == 2 && q == 1) {
                boolean stopHere = true;
                dq.union(p, q);
                unionOperations++;
            }
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
