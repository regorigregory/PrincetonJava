/* *****************************************************************************
 *  Name:              Gergo Endresz
 *  Coursera User ID:  0b98e7597a04720d1abd8d83dbdbc35a
 *  Last modified:      February 1, 2021
 **************************************************************************** */


public class HelloGoodbye {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please provide two arguments when running this file.");
        }
        else {
            System.out.println("Hello " + args[0] + " and " + args[1] + ".");
            System.out.println("Goodbye " + args[0] + " and " + args[1] + ".");
        }


    }
}

