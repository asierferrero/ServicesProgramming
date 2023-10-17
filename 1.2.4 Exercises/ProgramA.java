import java.util.Scanner;

public class ProgramA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a string: ");
        String userString = in.next();
        in.close();

        String newString = ". This is Program A string";
        String result = userString + newString;

        System.out.println("Result: " + result);
    }
}
