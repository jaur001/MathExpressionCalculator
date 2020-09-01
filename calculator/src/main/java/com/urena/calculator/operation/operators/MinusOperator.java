package com.urena.calculator.operation.operators;

public class MinusOperator implements Operator{
    @Override
    public double operate(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
