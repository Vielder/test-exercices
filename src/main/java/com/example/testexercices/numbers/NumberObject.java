package com.example.testexercices.numbers;

public class NumberObject {
    private Integer number1;
    private Integer number2;
    private Integer sum;

    public NumberObject(Integer number1, Integer number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.sum= number1 + number2;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public Integer getSum() {
        return sum;
    }
}
