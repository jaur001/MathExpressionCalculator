package com.urena.calculator.operation.singleOperators;

public class TanOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return Math.tan(num);
    }
}
