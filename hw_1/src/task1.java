import java.util.Scanner;
import java.util.InputMismatchException;

public class task1 {
    // Вычислить
    // 1. n-ое треугольного число (сумма чисел от 1 до n)
    // 2, n! (произведение чисел от 1 до n)
    public static void main(String[] args) {
        int number = safeReadInt("input number: ");

        //int triangNum = triangularBySum(number);
        int triangNum = triangularByFormula(number);
        //int factorialNum = factorialByIteration(number);
        int factorialNum = factorialByRecursion(number);

        System.out.printf("triangular number for %d is %d\n", number, triangNum);
        System.out.printf("factorial for %d is %d\n", number, factorialNum);
    }
    public static int safeReadInt(String greetingText) {
        // borrowed from https://stackoverflow.com/questions/25277286/exception-handling-with-scanner-nextint-vs-scanner-nextline
        Scanner iScanner = new Scanner(System.in);
        boolean continueInput = true;
        int result = 0;

        do {
            try{
                System.out.print(greetingText);
                result = iScanner.nextInt();
                continueInput = false;
            }
            catch (InputMismatchException ex) {
                System.out.println("Try again. (Incorrect input: an integer is required)");
                iScanner.nextLine();
            }
        }
        while (continueInput);
        return result;
    }
    public static int triangularBySum(int number) {
        int result = 0;
        while (number > 0) {
            result += number;
            number--;
        }
        return result;
    }
    public static int triangularByFormula(int number) {
        // Допускаю int т.к. первые 1000 таких чисел целые
        return number * (number + 1) / 2;
    }
    public static int factorialByIteration(int number) {
        int result = 1;
        while (number > 0) {
            result *= number;
            number--;
        }
        return result;
    }
    public static int factorialByRecursion(int number) {
        if (number == 1) return 1;
        return number * factorialByRecursion(number-1);
    }
}
