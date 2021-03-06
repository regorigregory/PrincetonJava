/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package interviewQuestions;

public class StackWithMax<T extends Comparable> {
    T[] stack1, stack2;

    int size;

    public StackWithMax() {
        this.stack1 = (T[]) new Object[4];
        this.stack2 = (T[]) new Object[4];

        this.size = 0;
    }

    private void resize(int n) {
        T[] newStack1 = (T[]) new Object[n];
        T[] newStack2 = (T[]) new Object[n];

        for (int i = 0; i < size; i++) {
            newStack1[i] = stack1[i];
            newStack2[i] = stack2[i];

        }
        stack1 = newStack1;
        stack2 = newStack2;

    }

    public T getMax() {
        return this.stack2[this.size - 1];
    }

    public T peek() {
        return this.stack1[this.size - 1];
    }

    public T pop() {
        this.size--;
        if (this.size == stack1.length / 4 && this.size != 4) {
            resize(stack1.length / 2);
        }
        T temp = this.stack1[this.size];
        this.stack1[this.size] = null;
        this.stack2[this.size] = null;

        return temp;

    }

    public void push(T newItem) {
        this.size++;
        if (this.size == stack1.length) {
            resize(this.size * 2);
        }
        this.stack1[this.size] = newItem;
        if (this.stack2[this.size - 1].compareTo(newItem) > 1) {
            this.stack2[this.size] = newItem;
        }
        else {
            this.stack2[this.size] = this.stack2[this.size - 1];
        }

    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    public static void main(String[] args) {

    }
}
