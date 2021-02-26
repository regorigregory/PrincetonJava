/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class Percolation {

    private class BooleanWrapper {
        boolean status;

        BooleanWrapper(boolean status) {
            this.status = status;
        }

        void setStatus(boolean status) {
            this.status = status;
        }


        boolean getStatus() {
            return status;
        }
    }

    private boolean[] opened;
    private BooleanWrapper[] filled;

    private boolean[] connectedToBottom;
    private int numberOfOpenSites;
    private boolean percolates;
    private int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        size = n;
        opened = new boolean[n * n];
        connectedToBottom = new boolean[n * n];
        filled = new BooleanWrapper[n * n];
        for (int i = 0; i < n * n; i++) {
            if (i < n * (n - 2)) {
                connectedToBottom[i] = true;
            }
            else {
                connectedToBottom[i] = false;
            }
            opened[i] = false;
            filled[i] = new BooleanWrapper(false);
        }

    }

    private int getSafeIndex(int row, int col) {
        if (row < 0) {
            row = 0;
        }
        else if (row >= size) {
            row = size - 1;
        }

        if (col < 0) {
            col = 0;
        }
        else if (col >= size) {
            col = size - 1;
        }
    }

    private int getIndex(int row, int col) {
        // todo: what if the col is longer than n?
        // todo: what if the row is longer than n?

        return row * size + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int index = getIndex(row, col);
        opened[getIndex(row, col)] = true;
        if (isTopRow(row)) {
            filled[index].setStatus(true);
        }
        visitNeighbours(row, col);
        numberOfOpenSites++;
    }

    private boolean isTopRow(int row) {
        return row == 0;

    }

    
    public void visitNeighbours(int row, int col) {
        int index = getIndex(row, col);
        int[] indices = {
                getSafeIndex(row - 1, col), getSafeIndex(row + 1, col), getSafeIndex(row, col - 1),
                getSafeIndex(row, col + 1)
        };
        BooleanWrapper temp = new BooleanWrapper(false);
        filled[index] = temp;

        for (int i : indices) {
            if (!opened[i]) {
                continue;
            }
            else if (opened[i]) {
                temp.setStatus(filled[i].getStatus() || temp.getStatus());
                filled[i] = temp;
            }
        }


    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = getIndex(row, col);
        return opened[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = getIndex(row, col);
        return filled[index].getStatus();

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;

    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
