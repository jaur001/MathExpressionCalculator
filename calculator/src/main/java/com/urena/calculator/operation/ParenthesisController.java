package com.urena.calculator.operation;

public class ParenthesisController {

    public static double calculateParenthesisOperation(String text) {
        int start = text.indexOf("(");
        int end = getParenthesis(text,start);
        return calculateResult(text, start, end);
    }

    private static int getParenthesis(String text,int start) {
        int position = start+1;
        int openFound = 0;
        while(position < text.length()){
            if(text.charAt(position)=='(') openFound++;
            if(text.charAt(position)==')')
                if(openFound==0) return position;
                else openFound--;
            position++;
        }
        return text.length()-1;
    }

    private static double calculateResult(String text,int start, int end) {
        double middleResult = new Calculator(text.substring(start +1, end)).calculate();
        return new Calculator(text.substring(0,start)+middleResult+text.substring(end+1)).calculate();
    }
}
