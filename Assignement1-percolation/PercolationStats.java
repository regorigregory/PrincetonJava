import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double MAGIC_NUMBER = 1.96;
    private double mean;
    private double std;

    private double[] results;
    private int n;

    private class RandomPopper {
        private int currentSize;
        private int[] elements;


        RandomPopper() {
            elements = new int[n * n];
            currentSize = n;
            for (int i = 1; i <= n * n; i++) {
                elements[i] = i;
            }

        }

        int[] reverseToCoordinate(int popIndex) {
            int col = popIndex % n;
            int row = (popIndex - col) / n;
            return new int[] { row, col };
        }

        int[] popRandom() {
            int randInt = StdRandom.uniform(currentSize);
            int temp = elements[randInt];
            elements[randInt] = elements[--currentSize];
            return reverseToCoordinate(temp);
        }
    }


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        results = new double[trials];
        int[] coords;
        RandomPopper r = new RandomPopper();

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                coords = r.popRandom();
                //coords = new int[] { indexToPop % n };
                //do {
                //    coords = new int[] { StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1) };
                //} while (p.isOpen(coords[0], coords[1]));
                p.open(coords[0], coords[1]);
            }
            results[i] = (double) p.numberOfOpenSites() / (n * n);
            // to be continued from here...
        }
        mean = StdStats.mean(results);
        std = StdStats.stddev(results);

    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return std;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - MAGIC_NUMBER * std / Math.sqrt(results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + MAGIC_NUMBER * std / Math.sqrt(results.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        throw new UnsupportedOperationException("To be implemented...");
    }

}
