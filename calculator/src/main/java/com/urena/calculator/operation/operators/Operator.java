package com.urena.calculator.operation.operators;

public interface Operator {
    double operate(double num1, double num2);
    int getPriority();
}
