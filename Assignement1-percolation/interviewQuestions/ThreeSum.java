/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class ThreeSum {

    public static int threesum(int[] numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for (int k = j + 1; k < numbers.length; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int binarySearch(int[] array, int value) {
        int first = 0;
        int last = array.length - 1;
        while (first <= last) {
            int mid = first + (last - first) / 2;
            if (value < array[mid]) {
                last = mid - 1;
            }
            else if (value > array[mid]) {
                first = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }


    public static int quickThreeSum(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int a = arr[i];
            int j = i + 1;
            int k = arr.length - 1;
            int b = arr[j];
            int c = arr[k];
            while (j < k) {
                if (a + b + c == 0) {
                    j++;
                    k--;
                    count++;
                }
                else if (a + b + c > 0) {
                    k--;
                }
                else {
                    j++;
                }
            }
        }
        return count;
    }

    public static int[] generateThreeSum(int n, int max) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = StdRandom.uniform(-max, max);

        }
        return nums;
    }

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            int n = Integer.parseInt(args[0]);
            int[] numbers = new int[n];
        }
        int array_size = 10000;
        int array_max_element = 1000;
        int n_experiments = 1000;
        int k = 0;
        double[] experimentTimes1 = new double[n_experiments];
        while (k < n_experiments) {

            int[] randomNumbers = generateThreeSum(array_size, array_max_element);
            Arrays.sort(randomNumbers);

            Stopwatch s = new Stopwatch();
            threesum(randomNumbers);
            experimentTimes1[k] = s.elapsedTime();
            k++;
        }
        System.out.printf("Simple threesum average runtime: %f%n", StdStats.mean(experimentTimes1));
        k = 0;
        double[] experimentTimes2 = new double[n_experiments];
        while (k < n_experiments) {

            int[] randomNumbers = generateThreeSum(array_size, array_max_element);
            Arrays.sort(randomNumbers);

            Stopwatch s = new Stopwatch();
            quickThreeSum(randomNumbers);
            experimentTimes2[k] = s.elapsedTime();
            k++;
        }
        System.out
                .printf("Advanced threesum average runtime: %f%n", StdStats.mean(experimentTimes2));
        System.out.printf("Runtime improvements: %f%n",
                          StdStats.mean(experimentTimes1) / StdStats.mean(experimentTimes2));


    }
}
