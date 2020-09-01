package com.urena.calculator.operation.singleOperators;

public class SinOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return Math.sin(num);
    }
}
