package com.example.testexercices.controller;

import com.example.testexercices.component.NumberObjectStorage;
import com.example.testexercices.numbers.NumberObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Comparator;

@RestController
public class NumberController {
    
    @Autowired
    private NumberObjectStorage numberObjectStorage;
    @GetMapping("/add")
    public Object numberAddition (@RequestParam("number1") @NonNull Integer number1,
                                  @RequestParam("number2") @NonNull Integer number2){
        if (ValueRange.of(0, 100).isValidIntValue(number1) &&
            ValueRange.of(0, 100).isValidIntValue(number2)) {
            NumberObject numberObject = new NumberObject(number1, number2);
            numberObjectStorage.saveNumberObject(numberObject);
            return numberObject;
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/find")
    public Object numberSearch (@RequestParam(value = "number", required = false) Integer number,
                                @RequestParam("sortDesc") boolean desc){
        ArrayList<NumberObject> result = new ArrayList<>();
        if (number == null) {
            result = numberObjectStorage.getAllNumberObjects();
        } else if (ValueRange.of(0, 100).isValidIntValue(number)) {
            for (NumberObject numberObject : numberObjectStorage.getAllNumberObjects()) {
                if (numberObject.getNumber1().equals(number) ||
                    numberObject.getNumber2().equals(number) ||
                    numberObject.getSum().equals(number)
                ) {
                    result.add(numberObject);
                }
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }
        if (desc) result.sort(Comparator.comparingInt(NumberObject::getSum).reversed());
        else result.sort(Comparator.comparingInt(NumberObject::getSum));

        return result;
    }

}
