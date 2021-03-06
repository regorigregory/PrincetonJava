/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class BitonicSearch implements ArrayPrinter<Integer> {
    public Integer[] generateBitonicArray(int n) {
        Integer[] arr = new Integer[n];
        int randomMiddlePoint = StdRandom.uniform(n / 2 - n / 4, n / 2 + n / 4);
        Integer[] left = generateRandomArray(randomMiddlePoint);
        Integer[] right = generateRandomArray(n - randomMiddlePoint);
        Arrays.sort(left);
        Arrays.sort(right, (a, b) -> (b - a));

        System.arraycopy(left, 0, arr, 0, randomMiddlePoint);
        System.arraycopy(right, 0, arr, randomMiddlePoint, right.length);
        return arr;

    }

    public int bitonicSearchSlow(int[] bitonicArray, int key) {
        int bitonicPoint = findBitonicPoint(bitonicArray, 0, bitonicArray.length);
        int leftIndex = BinarySearch.search(bitonicArray, key, (a, b) -> b > a, 0, bitonicPoint);
        int rightIndex = BinarySearch
                .search(bitonicArray, key, (a, b) -> b < a, bitonicPoint + 1, bitonicArray.length);
        return leftIndex == -1 ? rightIndex : leftIndex;
    }

    public int bitonicSearchFast(int[] bitonicArray, int key, int left, int right) {
        int mid = left + (right - left) / 2;
        if (bitonicArray[mid] == key) {
            return mid;
        }
        if (bitonicArray[mid] < bitonicArray[mid - 1] && bitonicArray[mid] < key) {

        }
        int bitonicPoint = findBitonicPoint(bitonicArray, 0, bitonicArray.length);
        int leftIndex = BinarySearch.search(bitonicArray, key, (a, b) -> b > a, 0, bitonicPoint);
        int rightIndex = BinarySearch
                .search(bitonicArray, key, (a, b) -> b < a, bitonicPoint + 1, bitonicArray.length);
        return leftIndex == -1 ? rightIndex : leftIndex;
    }

    public int findBitonicPoint(int[] array, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > array[mid - 1] && array[mid] > array[mid + 1]) {
                return mid;
            }
            if (array[mid - 1] < array[mid]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;

            }
        }
        return -1;
    }

    public Integer[] generateRandomArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = StdRandom.uniform(200);
        }
        return arr;
    }

    public static void main(String[] args) {
        BitonicSearch bs = new BitonicSearch();
        Integer[] arr = bs.generateBitonicArray(20);
        int[] testArray = { 22, 20, 18, 17, 16, 10, 5, 3, 1 };
        int[] testArray2 = { 0, 1, 2, 5, 8, 10, 55, 11, 7, 6, 3 };

        //int found = BinarySearch.search(testArray2, 31, (a, b) -> b > a);
        //bs.printArray(arr);
        System.out.println(bs.findBitonicPoint(testArray2, 0, testArray2.length - 1));
        System.out.println(bs.bitonicSearchSlow(testArray2, 6));
    }
}
