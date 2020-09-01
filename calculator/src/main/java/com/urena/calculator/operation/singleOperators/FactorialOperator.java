package com.urena.calculator.operation.singleOperators;

public class FactorialOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return factorial(Math.round(Math.abs(num)));
    }

    private double factorial(long num) {
        if(num<=1) return 1;
        else return num * factorial(num-1);

    }
}
