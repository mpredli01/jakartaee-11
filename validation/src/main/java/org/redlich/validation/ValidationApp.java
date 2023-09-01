package org.redlich.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidationApp {
    public static void main(String[] args) {
        String title = "[APP] Welcome to the Jakarta Validation Demo Application";
        displayTitle(title);

        User user = new User(null, true, "Java Champion", 13, "mike");
        System.out.println("[APP] The user is: " + user);
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        System.out.println("[APP] Checking validation rules:");
        for(ConstraintViolation<User> violation : violations) {
            System.out.println("[APP] * " + violation.getMessage());
            }
        }

    public static void displayTitle(String title) {
        int length = title.length();
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        System.out.println(title);
        System.out.print("[APP] ");
        for(int i = 0; i < length; ++i) {
            System.out.print("-");
            }
        System.out.println();
        }
    }
