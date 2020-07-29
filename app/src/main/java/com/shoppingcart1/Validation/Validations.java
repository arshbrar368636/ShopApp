package com.shoppingcart1.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    private Pattern email;
    private Pattern name;
    private Pattern password;
    private Pattern phone;
    private Matcher matcher;
    private static final String NAME_PATTERN="^[A-Za-z ]{3,25}$";
    private static final String PHONE_PATTERN="^[0-9]{10}$";
    private static final String PASS_PATTERN="^[A-Za-z0-9]{3,25}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$";
    public Validations()
    {
        email = Pattern.compile(EMAIL_PATTERN);
        name = Pattern.compile(NAME_PATTERN);
        password=Pattern.compile(PASS_PATTERN);
        phone=Pattern.compile(PHONE_PATTERN);
    }
    public boolean email_validate(final String email1) {
        matcher = email.matcher(email1);
        return matcher.matches();

    }

    public boolean name_validate(final String name1) {
        matcher = name.matcher(name1);
        return matcher.matches();

    }

    public boolean pass_validate(final String pass1) {
        matcher = password.matcher(pass1);
        return matcher.matches();

    }

    public boolean phone_validate(final String phone1) {
        matcher = phone.matcher(phone1);
        return matcher.matches();

    }
}
