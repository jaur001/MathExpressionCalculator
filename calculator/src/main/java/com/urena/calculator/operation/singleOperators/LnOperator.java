package com.urena.calculator.operation.singleOperators;

public class LnOperator implements SingleOperator{
    @Override
    public double operate(double num) {
        return Math.log(num);
    }
}
