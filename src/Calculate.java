import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = null;
        try {
            input = scanner.nextLine();
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(calc(input));
    }

    public static String calc(String str) {
        Parser pars = new Parser(str);
        return pars.eval();
    }
}

class Parser {
    private String input;
    private String[] items = new String[3];
    private String [] op = {"+","-","*","/"};
    private int operationInd;
    private boolean isArab = false;

    Parser(String input) {
        this.input = input.replaceAll("\\s+","");
    }

    public void getItems() {
        if ('9' >= input.charAt(0) && input.charAt(0) > '0') {
            isArab = true;
        }
        for(int i=0; i<4; i++) {

            if(input.indexOf(op[i]) != -1) {
                operationInd = input.indexOf(op[i]);
            }
        }
    }

    public String eval() {
        getItems();
        if(isArab) {
            try {
                int a = Integer.valueOf(input.substring(0, operationInd));
                int b =  Integer.valueOf(input.substring(operationInd+1, input.length()));
                if (a <= 10 && b <= 10) {
                    return String.valueOf(Calculate.doCalc(a, b, String.valueOf(input.charAt(operationInd))));
                }
                else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }

        } else {
            int a = romanToInt(input.substring(0, operationInd));
            int b = romanToInt(input.substring(operationInd+1));
            if (a <= 10 && b <= 10) {
                return intToRoman(Calculate.doCalc(a, b, String.valueOf(input.charAt(operationInd))));
            } else {
                throw new IllegalArgumentException();
            }
        }

    }
    public static int romanToInt(String s) {
        String roman = "IVX";
        int[] value = {1,5,10};
        int sum = 0;  //to store the value of the roman numeral
        for(int i = 0; i < s.length(); i++){
            if(i == s.length()-1){
                sum += value[roman.indexOf(s.charAt(i))];
                break;
            }
            char ch = s.charAt(i);
            char ch1 = s.charAt(i+1);
            int idx = roman.indexOf(ch);
            int idx1 = roman.indexOf(ch1);

            if(value[idx1] > value[idx])
            {
                sum += (-value[idx]);
            }
            else {
                sum += value[idx];
            }
        }
        return sum;
    }

    public static String intToRoman(int num) {

        String C[] = {"", "C"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}

class Calculate {
    public static int doCalc(int a, int b, String operator) throws IllegalArgumentException {
        switch (operator) {
            case "+":
                return   a+b;
            case "-":
                return  a-b;
            case "/":
                return a/b;
            case "*":
                return a*b;

            default: {
                throw new IllegalArgumentException();
            }
        }
    }
}
