package com.urena.calculator.operation;

import com.urena.calculator.operation.singleOperators.*;

import java.util.*;
import java.util.stream.Collectors;

public class SingleOperatorController {
    private static final Map<String, SingleOperator> operators = new HashMap<>();
    static {
        operators.put(",", new SinOperator());
        operators.put("'", new CosOperator() );
        operators.put(";", new TanOperator());
        operators.put("@", new LogOperator());
        operators.put("#", new LnOperator() );
        operators.put("√", new SqrtOperator());
        operators.put("%", new PercentageOperator());
        operators.put("!", new FactorialOperator());
    }

    public static double parseOperators(double operating, String text) {
        List<SingleOperator> operatorsUsed = parseOperators(text);
        if(operatorsUsed.size()==0) return operating;
        operating = operateOperating(operating, operatorsUsed);
        return operating;
    }

    private static List<SingleOperator> parseOperators(String text) {
        return text.chars().boxed()
                .map(value -> (char)value.intValue())
                .map(String::valueOf)
                .filter(operators::containsKey)
                .map(operators::get)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static double operateOperating(double operating, List<SingleOperator> operatorsUsed) {
        for (int i = operatorsUsed.size()-1; i >= 0; i--) {
            operating = operatorsUsed.get(i).operate(operating);
        }
        return operating;
    }
}
