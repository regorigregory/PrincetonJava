/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package interviewQuestions;

public class TwoStackQueue {
    class Stack<T> {
        T[] stack;
        int size;

        public Stack() {
            this.stack = (T[]) new Object[2];
            this.size = 0;
        }

        private void resize(int n) {
            T[] newStack = (T[]) new Object[n];
            for (int i = 0; i < size; i++) {
                newStack[i] = stack[i];
            }
            stack = newStack;
        }

        public T pop() {
            this.size--;
            if (this.size == stack.length / 4 && this.size != 4) {
                resize(stack.length / 2);
            }
            T temp = this.stack[this.size];
            this.stack[this.size] = null;
            return temp;

        }

        public void push(T newItem) {
            this.size++;
            if (this.size == stack.length) {
                resize(this.size * 2);
            }
            this.stack[this.size] = newItem;

        }

        public boolean isEmpty() {
            return this.size == 0;
        }

    }

    class FastEnqueue implements Queue<Integer> {
        Stack<Integer> s1, s2;

        public FastEnqueue() {
            Stack<Integer> s1 = new Stack<Integer>();
            Stack<Integer> s2 = new Stack<Integer>();

        }

        public Integer dequeue() {
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }

            }

            return s2.pop();

        }

        public void enqueue(Integer e) {
            s1.push(e);
        }
    }

    class FastDequeue implements Queue<Integer> {
        Stack<Integer> s1, s2;

        public FastDequeue() {
            Stack<Integer> s1 = new Stack<Integer>();
            Stack<Integer> s2 = new Stack<Integer>();

        }

        public Integer dequeue() {
            return s1.pop();
        }

        public void enqueue(Integer e) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            s1.push(e);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
    }

    interface Queue<T extends Object> {
        T dequeue();

        void enqueue(T e);
    }

    public static void main(String[] args) {

    }
}
