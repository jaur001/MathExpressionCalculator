package com.urena.calculator.operation;

import com.urena.calculator.operation.operators.Operator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private String text;

    public Calculator(String text) {
        this.text = text;
        prepareOperation();
    }

    public void prepareOperation(){
        this.text = text.replace("log","@").replace("ln","#").trim();
        this.text = text.replace("sin",",").replace("cos","'").replace("tan",";");
        this.text = text.replace("x","*").replace("e",Math.E+"").replace("π", Math.PI+"");
        handleSpaces();
        this.text = text.replaceAll("[a-zA-Z]","").replace(" ","");
        this.text = this.text.replaceAll("^"+OperatorController.MULTI_DIV + "+","").replaceAll(OperatorController.OPERATORS + "+$","");
    }

    private void handleSpaces() {
        Matcher matcher = Pattern.compile("\\s+").matcher(text);
        while(matcher.find()){
            if(isBetweenDoubles(matcher)) replaceCharacter(matcher, "*");
            else {
                replaceCharacter(matcher, "");
            }
        }
    }

    private void replaceCharacter(Matcher matcher, String s) {
        text = text.substring(0, matcher.start()) + s + text.substring(matcher.end());
    }

    private boolean isBetweenDoubles(Matcher matcher) {
        return text.substring(matcher.start() - 1, matcher.start()).matches(OperatorController.DOUBLE +"|" + OperatorController.RIGHTSIMPLEOPERATING)
                && text.substring(matcher.end(), matcher.end() + 1).matches(OperatorController.DOUBLE +"|" + OperatorController.LEFTSIMPLEOPERATING);
    }

    public double calculate() {
        if(!text.contains("(")) {
            return calculate(text.replace(")", ""));
        }
        return ParenthesisController.calculateParenthesisOperation(text);
    }

    private double calculate(String text) {
        String finalText = solvePossibleUserErrors(text);
        List<Operating> numbersUsed = OperatingController.getOperatings(finalText);
        List<Operator> operatorsUsed = OperatorController.getOperators(finalText);
        if(operatorsUsed.size()==0)return getOperating();
        if(numbersUsed.size()==operatorsUsed.size()+1) {
            return OperatorController.calculateWithPriorities(numbersUsed, operatorsUsed);
        } else throw new RuntimeException("There was an error calculating the result! Try again.");
    }


    private String solvePossibleUserErrors(String finalText) {
        if(finalText.substring(0,1).matches(OperatorController.MULTI_DIV)) finalText = finalText.substring(1);
        if(finalText.substring(finalText.length()-1).matches(OperatorController.OPERATORS)) finalText = finalText.substring(0,finalText.length()-1);
        finalText = deleteDuplicatedOperators(finalText);
        return simplifyPlusMinuses(finalText);
    }

    private String deleteDuplicatedOperators(String finalText) {
        Matcher matcher = Pattern.compile(OperatorController.OPERATORS + "{2,}").matcher(finalText);
        while (matcher.find()){
            if(matcher.group().matches(OperatorController.MULTI_DIV+"$"))
                finalText = finalText.substring(0, matcher.start()-1) + finalText.substring(matcher.end()-1);
            else {
                finalText = deleteDuplicatedMultiDiv(finalText,matcher);
            }
        }
        return finalText;
    }

    private String deleteDuplicatedMultiDiv(String finalText, Matcher matcher) {
        int position = matcher.end()-1;
        while (position>=matcher.start()){
            if(finalText.substring(position,position+1).matches(OperatorController.MULTI_DIV))
                return finalText.substring(0, matcher.start())+finalText.substring(position);
            position--;
        }
        return finalText;
    }

    private String simplifyPlusMinuses(String finalText) {
        Matcher matcher = Pattern.compile(OperatorController.MAX_MINUS + "{2,}").matcher(finalText);
        while (matcher.find()){
            int minusCount = (int) finalText.substring(matcher.start(),matcher.end()+1).chars().filter(c->c=='-').count();
            if(minusCount%2==0) finalText =  finalText.substring(0,matcher.start())+"+"+finalText.substring(matcher.end());
            else finalText = finalText.substring(0,matcher.start())+"-"+finalText.substring(matcher.end());
        }
        return finalText;
    }

    private double getOperating() {
        Operating operating = OperatingController.getOperating(text);
        if(operating!=null) return operating.getNumber();
        throw new RuntimeException("Error, Bad input syntax");
    }

    public static void main(String[] args) {
        Operation operation = new Operation("2 5 6");
        System.out.println(operation.getText());
        System.out.println(operation.getResult());
    }
}
