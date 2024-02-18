package com.workintech.s18d1.util;

import org.springframework.http.HttpStatus;

import com.workintech.s18d1.exceptions.BurgerException;

public class BurgerValidation {

    public static void checkName(String name) {
        if (name == null || name.isEmpty()) {
            throw new BurgerException("Burger name can not be empty or null.", HttpStatus.BAD_REQUEST);
        }
    }

}
