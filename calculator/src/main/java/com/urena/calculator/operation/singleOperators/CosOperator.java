package com.urena.calculator.operation.singleOperators;

public class CosOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return Math.cos(num);
    }
}
