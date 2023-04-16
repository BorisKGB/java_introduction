import java.util.Scanner;
import java.util.regex.Pattern;

public class task3 {
    // Реализовать простой калькулятор
    // tips
    // For now just get expression and try to evaluate it
    // Allow only ["+", "-", "*", "/"] operations
    // All input numbers will be treated as double
    // any other symbols (as "(", ")" or not numbers and allowed operations) will be treated as invalid input

    static String exitWord = "exit";
    static String[] allowedOperations = new String[] {"+", "-", "*", "/"};
    // allow operation to evaluate with only one number (+5 allowed, *5 not)
    static boolean[] operationsAllowedSingleNumber = new boolean[] {true, true, false, false};
    // operation priorities according to https://en.wikipedia.org/wiki/Order_of_operations#Programming_languages
    static int[] operationsPriority = new int[] {4, 4, 3, 3};
    static int maxOpPriority = 0;
    // static int minOpPriority = 15;

    public static void main(String[] args) {
        String expression;
        boolean continueFlag = true;
        while (continueFlag) {
            Scanner iScanner = new Scanner(System.in);
            System.out.println("Calculator v.0.2");
            System.out.printf("Allowed operations is [%s]\n", String.join(", ", allowedOperations));
            System.out.println("Input 'exit' to stop program or");
            System.out.print("Input expression > ");
            expression = iScanner.nextLine();
            if (expression.toUpperCase().contains(exitWord.toUpperCase())){
                continueFlag = false;
            } else {
                try {
                    System.out.printf("result > %f\n", evaluate(expression));
                }
                catch (ArithmeticException ex) {
                    System.out.println("I can`t understand this expression, try again");
                }
            }
        }
    }

    public static double evaluate(String expression) {
        double result = 0;
        // delete extra symbols
        expression = expression.strip();
        // check if its plain number
        try {
            return Double.parseDouble(expression);
        }
        catch (NumberFormatException ex){
            // search for operations in operationsPriority order
            String operation = "";
            boolean foundOperation = false;
            int priority = maxOpPriority;
            boolean allowSingleNumber = false;
            for (int i=0;i<allowedOperations.length;i++){
                if (expression.toUpperCase().contains(allowedOperations[i])){
                    if (operationsPriority[i] > priority) {
                        foundOperation = true;
                        operation = allowedOperations[i];
                        allowSingleNumber = operationsAllowedSingleNumber[i];
                        priority = operationsPriority[i];
                    }
                }
            }
            if (!foundOperation) {
                throw new ArithmeticException("Cant find operation to do");
            }
            String[] expressions = expression.split(Pattern.quote(operation), 2);
            // check if user input something like "*4" or ...
            // be aware, that "+4" or "-4" will be caught by parseDouble
            if ((expressions[0].length() == 0 || expressions[1].length() == 0) && !allowSingleNumber) {
                throw new ArithmeticException(String.format("Cant use operator %s like this", operation));
            }
            switch (operation){
                case "+":
                    result = evaluate(expressions[0]) + evaluate(expressions[1]);
                    break;
                case "-":
                    result = evaluate(expressions[0]) - evaluate(expressions[1]);
                    break;
                case "*":
                    result = evaluate(expressions[0]) * evaluate(expressions[1]);
                    break;
                case "/":
                    result = evaluate(expressions[0]) / evaluate(expressions[1]);
                    break;
            }
            return result;
        }
    }
}
