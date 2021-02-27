/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final String ILLEGAL_INDEX_ERROR
            = "The provided indices (%d, %d) are invalid for the grid size of %d * %d.";
    private static final String ILLEGAL_SIZE_ERROR
            = "The provided size (%d) is invalid. It cannot be negative.";
    private boolean[] openStatus;
    private final int nSites;
    private int nOpenSites;
    private final int virtualTopSite;
    private final int virtualBottomSite;

    private final WeightedQuickUnionUF filledUnion;  // only top
    private final WeightedQuickUnionUF percolationUnion;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(String.format(ILLEGAL_SIZE_ERROR, n));
        }
        nSites = n;
        nOpenSites = 0;
        openStatus = new boolean[n * n];
        virtualTopSite = n * n;
        virtualBottomSite = n * n + 1;


        filledUnion =
                new WeightedQuickUnionUF(n * n + 1);                  // n
        percolationUnion =
                new WeightedQuickUnionUF(n * n + 2);                  // n

        // connect top and bottom row to the virtual top site
        // hence, if a site is connected to the top union, it is filled
        // if it is conneted to both, it percolates
        for (int i = 0; i < n; i++) {                                    // n
            filledUnion.union(i, virtualTopSite);
            percolationUnion.union(i, virtualTopSite);

        }

        // connect bottom row to the virtual bottom site
        for (int i = 0; i < n; i++)                                      // n
            percolationUnion.union(n * (n - 1) + i,
                                   virtualBottomSite);
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row--;
        col--;
        int newlyOpenedIndex = getIndex(row, col);
        if (openStatus[newlyOpenedIndex]) {
            return;
        }
        openStatus[newlyOpenedIndex] = true;
        nOpenSites++;
        int[][] indices = {
                { row, col - 1 }, { row + 1, col }, { row, col + 1 }, { row - 1, col }
        };
        for (int[] coordinates : indices) {
            if (isValidIndex(coordinates[0], coordinates[1]) && openStatus[getIndex(coordinates[0],
                                                                                    coordinates[1])]) {
                int tempIndex = getIndex(coordinates[0], coordinates[1]);

                filledUnion.union(newlyOpenedIndex, tempIndex);
                percolationUnion.union(newlyOpenedIndex, tempIndex);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        //row--;
        //col--;
        return openStatus[getIndex(row - 1, col - 1)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {

        int index = getIndex(row - 1, col - 1);
        return openStatus[index]
                && filledUnion.find(index) == filledUnion
                .find(virtualTopSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return nSites != 1 ?
               percolationUnion.find(virtualTopSite) == percolationUnion.find(virtualBottomSite) :
               isFull(1, 1);

    }

    private boolean isValidIndex(int row, int col) {
        return row >= 0 && row < nSites && col >= 0 && col < nSites;

    }

    private int getIndex(int row, int col) {
        if (isValidIndex(row, col)) return row * nSites + col;
        throw new IllegalArgumentException(
                String.format(ILLEGAL_INDEX_ERROR, row, col, nSites, nSites));
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
