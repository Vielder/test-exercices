package com.example.testexercices.component;

import com.example.testexercices.numbers.NumberObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class NumberObjectStorage {
    private ArrayList<NumberObject> numberObjects = new ArrayList<>();

    public void saveNumberObject(NumberObject numberObject){
        numberObjects.add(numberObject);
    }

    public ArrayList<NumberObject> getAllNumberObjects(){
        return numberObjects;
    }
}
