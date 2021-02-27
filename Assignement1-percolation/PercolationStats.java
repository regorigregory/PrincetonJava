import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double MAGIC_NUMBER = 1.96;
    private double mean;
    private double std;

    private double[] results;
    private int n;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        results = new double[trials];
        int[] coords;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);

            while (!p.percolates()) {
                do {
                    coords = new int[] { StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1) };
                } while (p.isOpen(coords[0], coords[1]));
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
        if (args != null && args.length == 2) {
            int nSites = Integer.parseInt(args[0]);
            int nTrials = Integer.parseInt(args[1]);
            PercolationStats pStats = new PercolationStats(nSites, nTrials);
            StdOut.printf("mean\t\t\t= %f%n", pStats.mean());
            StdOut.printf("stddev\t\t\t= %f%n", pStats.stddev());
            StdOut.printf("95%% confidence interval\t= [%f, %f]%n", pStats.confidenceLo(),
                          pStats.confidenceHi());

        }
    }

}
