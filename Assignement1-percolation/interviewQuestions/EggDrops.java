/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

import edu.princeton.cs.algs4.StdRandom;

public class EggDrops {
    int floors, breakLimit, tosses;

    public EggDrops(int n) {
        this.floors = n;
        this.breakLimit = StdRandom.uniform(1, n + 1);
    }

    public boolean isBroken(int n) {
        this.tosses++;
        return n >= this.breakLimit;
    }

    public void printResults(int i, int eggs, String version) {
        System.out
                .printf("%s: It took %d tosses and %d eggs to find the floor %d == %d out of %d floors. %n",
                        version,
                        this.tosses,
                        eggs, i,
                        this.breakLimit,
                        this.floors);
    }

    public int getTriangularNumberStart() {

        return (int) (-1 + Math.sqrt(4 * 1 * 2 * this.floors)) / 2;

    }

    public boolean foundBreakingPoint(int i) {
        return i == this.breakLimit;
    }

    //1 egg and <=this.breakLimit tosses
    public void Version0() {
        this.tosses = 0;

        for (int i = 1; i <= this.floors; i++) {
            if (isBroken(i)) {
                printResults(i, 1, "V0");
                break;
            }
        }
    }


    //Version 1: lg(n) eggs and lg(n) tosses
    public void Version1() {
        this.tosses = 0;
        int eggs = 0;
        int start = 0;
        int end = this.floors;
        int mid = start + (end - start) / 2;
        while (!foundBreakingPoint(mid)) {
            if (isBroken(mid)) {
                end = mid - 1;
                eggs++;
            }
            else {
                start = mid + 1;

            }
            mid = start + (end - start) / 2;

        }
        printResults(mid, ++eggs, "V1");
    }

    // lg(breakLimit) eggs and 2lg(breakLimit) tosses
    public void Version2() {
        this.tosses = 0;
        int eggs = 0;
        int previousFloor = 1;
        int startingFloor = 1;

        while (startingFloor <= this.floors && eggs < 1) {
            if (!isBroken(startingFloor)) {
                previousFloor = startingFloor;
                startingFloor *= 2;
            }
            else {

                eggs++;
            }
        }
        if (foundBreakingPoint(startingFloor)) {
            this.printResults(startingFloor, ++eggs, "V2");
        }
        else {
            int mid = previousFloor + (startingFloor - previousFloor) / 2;
            while (!foundBreakingPoint(mid)) {
                if (isBroken(mid)) {
                    startingFloor = mid - 1;
                    eggs++;
                }
                else {
                    previousFloor = mid + 1;

                }
                mid = previousFloor + (startingFloor - previousFloor) / 2;

            }
            this.printResults(mid, ++eggs, "V2");


        }


    }
    //Version 3: 2 eggs and  2 * sqrt(this.floors) tosses

    public void Version3() {
        this.tosses = 0;
        int eggs = 0;
        int previousFloor = 1;
        int startingFloor = (int) Math.sqrt(this.floors);

        while (!isBroken(startingFloor)) {

            previousFloor = startingFloor;
            startingFloor += (int) Math.sqrt(this.floors);

        }
        eggs++;
        if (foundBreakingPoint(startingFloor)) {
            this.printResults(startingFloor, ++eggs, "V3");
        }
        else {
            for (int i = previousFloor; i < startingFloor; i++) {
                if (isBroken(i)) {
                    this.printResults(i, ++eggs, "V3");
                    break;
                }
            }


        }


    }

    // 2 eggs and c * sqrt(breakLimit)
    public void Version4() {
        this.tosses = 0;

        int eggs = 0;
        int previousFloor = 1;
        int startingFloor = 1;
        int d = 1;
        while (startingFloor < this.floors && eggs < 1) {
            if (isBroken(startingFloor)) {
                eggs++;
            }
            else {
                previousFloor = startingFloor;
                startingFloor += d;
                d++;
            }
        }
        if (foundBreakingPoint(startingFloor)) {
            this.printResults(startingFloor, ++eggs, "V4");
        }
        else {
            for (int i = previousFloor; i < startingFloor; i++) {
                if (isBroken(i)) {
                    this.printResults(i, ++eggs, "V4");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        EggDrops ed = new EggDrops(1000000);
        //ed.breakLimit = 10000;
        ed.Version0();
        ed.Version1();
        ed.Version2();
        ed.Version3();
        ed.Version4();
        System.out.println(ed.getTriangularNumberStart());


    }
}
