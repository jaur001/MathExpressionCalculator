package com.urena.calculator.operation;

public class Operation {

    private String text;
    private double result;

    public Operation() {
        this("");
    }

    public Operation(String text) {
        this.text = text;
        updateResult();
    }

    private void updateResult() {
        try{
            if(text.equals("")) result = 0;
            else result = new Calculator(text).calculate();
        } catch (Exception e){
            text = e.getMessage();
            result = 0;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        updateResult();
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
