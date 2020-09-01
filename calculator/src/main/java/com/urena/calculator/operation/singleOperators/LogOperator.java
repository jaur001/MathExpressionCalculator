package com.urena.calculator.operation.singleOperators;

public class LogOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return Math.log10(num);
    }
}
