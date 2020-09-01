package com.urena.calculator.operation;

import com.urena.calculator.operation.operators.*;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OperatorController {

    public static final String MULTI_DIV = "[*/^]";
    public static final String MAX_MINUS = "[-+]";
    public static final String OPERATORS = "[-+*/^]";
    public static final String DOUBLE = "-?[0-9]+\\.?[0-9]*";
    public static final String LEFTSIMPLEOPERATING = "[√@#,';]?";
    public static final String RIGHTSIMPLEOPERATING = "[!%]?";
    public static final String SINGLEOPERATING = LEFTSIMPLEOPERATING + DOUBLE + RIGHTSIMPLEOPERATING;
    private static final Map<String, Operator> operators = new HashMap<>();

    static {
        operators.put("+", new PlusOperator());
        operators.put("-", new MinusOperator() );
        operators.put("*", new MultiplicationOperator());
        operators.put("/", new DivisionOperator());
        operators.put("^", new PowOperator());
    }

    public static double calculateWithPriorities(List<Operating> numbersUsed, List<Operator> operatorsUsed) {
        while (operatorsUsed.size()>0){
            if(operatorsUsed.size()==1) return operatorsUsed.get(0).operate(numbersUsed.get(0).getNumber(),numbersUsed.get(1).getNumber());
            Operator priorityOperation = getPriorityOperation(operatorsUsed);
            calculateResult(numbersUsed, operatorsUsed, priorityOperation);
        }
        return numbersUsed.get(0).getNumber();
    }

    private static Operator getPriorityOperation(List<Operator> operatorsUsed) {
        Operator priorityOperation = operatorsUsed.get(0);
        for (int i = 1; i < operatorsUsed.size(); i++) {
            if(priorityOperation.getPriority()< operatorsUsed.get(i).getPriority())
                priorityOperation = operatorsUsed.get(i);
            i++;
        }
        return priorityOperation;
    }

    private static void calculateResult(List<Operating> numbersUsed, List<Operator> operatorsUsed, Operator priorityOperation) {
        int index = operatorsUsed.indexOf(priorityOperation);
        double result = priorityOperation.operate(numbersUsed.get(index).getNumber(),
                numbersUsed.get(index +1).getNumber());
        numbersUsed.get(index).setNumber(result);
        numbersUsed.remove(index+1);
        operatorsUsed.remove(priorityOperation);
    }

    public static List<Operator> getOperators(String finalText) {
        return Pattern.compile(OPERATORS+"+").matcher(finalText).results()
                .filter(matchResult -> matchResult.start()!=0)
                .map(matchResult -> finalText.substring(matchResult.start(),matchResult.end()))
                .map(OperatorController::getOperator)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static Operator getOperator(String operator) {
        if(operator.length()>1) return getOperator(operator.substring(0,1));
        return operators.get(operator);
    }
}
