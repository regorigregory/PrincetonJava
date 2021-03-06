/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package interviewQuestions;

import java.util.function.BiPredicate;

public class BinarySearch {
    public static int search(int[] array, int key, BiPredicate<Integer, Integer> comparator,
                             int left, int right) {
        if (right >= array.length) {
            right = array.length - 1;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == key) {
                return mid;
            }
            if (comparator.test(array[mid], key)) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }

        }
        return -1;
    }

    public static int search(int[] array, int key, int left, int right) {
        BiPredicate<Integer, Integer> comparator = (a, b) -> b > a;
        return search(array, key, comparator, left, right);

    }

    public static int search(int[] array, int key) {
        BiPredicate<Integer, Integer> comparator = (a, b) -> b > a;
        return search(array, key, comparator, 0, array.length);

    }

    public static void main(String[] args) {

    }
}
