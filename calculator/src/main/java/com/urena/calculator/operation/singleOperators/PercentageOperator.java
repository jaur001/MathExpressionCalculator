package com.urena.calculator.operation.singleOperators;

public class PercentageOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return num/100.0;
    }
}
