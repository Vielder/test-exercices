package com.example.testexercices.numbers;

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
    private ArrayList<NumberObject> numberObjects = new ArrayList<>();

    @GetMapping("/add")
    public Object numberAddition (@RequestParam("number1") @NonNull Integer number1,
                                  @RequestParam("number2") @NonNull Integer number2){
        if (ValueRange.of(0, 100).isValidIntValue(number1) &&
            ValueRange.of(0, 100).isValidIntValue(number2)) {
            NumberObject numberObject = new NumberObject(number1, number2);
            numberObjects.add(numberObject);
            return numberObject;
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/find")
    public Object numberSearch (@RequestParam(value = "number", required = false) Integer number,
                                @RequestParam("sortDesc") boolean desc){
        ArrayList<NumberObject> result = new ArrayList<>();
        if (number == null) {
            result = numberObjects;
        } else if (ValueRange.of(0, 100).isValidIntValue(number)) {
            for (NumberObject numberObject : numberObjects) {
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

    // getter for tests
    public ArrayList<NumberObject> getNumberObjects(){
        return numberObjects;
    }

}
