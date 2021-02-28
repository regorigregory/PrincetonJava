/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

import java.util.Scanner;

public class SocialNetworkConnectivity implements UF, StatusReporter {


    private int[] components;
    private int[] componentSizes;
    String timestamp;
    private int nComponents;

    public SocialNetworkConnectivity(int n) {
        nComponents = n;
        components = new int[n];
        componentSizes = new int[n];

        for (int i = 1; i < n; i++) {
            components[i] = i;
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


    public void union(int p, int q) {
        p--;
        q--;
        System.out.printf("Checking %d and %d...%n", p, q);

        if (p == q) return;
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            System.out.printf("%d and %d are already connected.%n", p, q);
            return;
        }

        if (componentSizes[pRoot] > componentSizes[qRoot]) {
            System.out.printf("Connecting %d and %d.%n", p, q);

            componentSizes[pRoot] += componentSizes[qRoot];
            components[qRoot] = pRoot;
        }
        else {
            System.out.printf("Connecting %d and %d.%n", p, q);

            componentSizes[qRoot] += componentSizes[pRoot];
            components[pRoot] = qRoot;
        }
        nComponents--;

    }

    public void unionWithTimeStamp(int p, int q, String timeStamp) {
        union(p, q);
        if (nComponents == 1) {
            this.timestamp = timeStamp;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int getSize() {
        return this.components.length;
    }

    public int getElementById(int id) {
        return components[id];
    }

    public boolean isFullyConnected() {
        return nComponents == 1;
    }

    public String getTimeOfFullyConnected() {
        if (isFullyConnected()) return timestamp;
        return "Not yet";
    }


    public int getNumberOfComponents() {
        return nComponents;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        SocialNetworkConnectivity network = new SocialNetworkConnectivity(n);
        while (input.hasNextInt()) {
            network.unionWithTimeStamp(input.nextInt(), input.nextInt(), "2020/11/11 16:30");
            if (network.isFullyConnected()) {
                System.out.println("The network has become fully connected at " + network
                        .getTimeOfFullyConnected());
                network.printStatus();

                break;
            }
            network.printStatus();
        }


    }
}
