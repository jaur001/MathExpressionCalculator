package com.urena.calculator.operation.singleOperators;

public class SqrtOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return Math.sqrt(num);
    }
}
