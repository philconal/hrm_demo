package conal.hrm_demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static conal.hrm_demo.util.Constant.WRONG_EMAIL_FORMAT;


public class Validator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);

    public static void validateEmail(String emailAddress) {
        if (emailAddress == null || emailAddress.isBlank())
            throw Generate.throwNotFoundExceptionMessage("Email is empty or null!");
        emailAddress = emailAddress.trim();
        boolean isEmailFormat = isEmailFormat(emailAddress);
        if (!isEmailFormat) {
            throw Generate.throwNotFoundExceptionMessage(WRONG_EMAIL_FORMAT);
        }
    }

    private static boolean isEmailFormat(String valueToValidate) {
        // Regex
        String regexExpression = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        Pattern regexPattern = Pattern.compile(regexExpression);

        if (valueToValidate != null) {
            if (valueToValidate.indexOf("@") <= 0) {
                return false;
            }
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(valueToValidate);
            return matcher.matches();
        } else { // The case of empty Regex expression must be accepted
            Matcher matcher = regexPattern.matcher("");
            return matcher.matches();
        }
    }


    public static void checkPhoneFormat(String text) {
        if (text == null || text.isBlank())
            throw Generate.throwNotFoundExceptionMessage("Phone number is empty or null!");
        text = text.trim();
        for (char letter : text.toCharArray()) {
            if (!Character.isDigit(letter))
                throw Generate.throwNotFoundExceptionMessage("Wrong format phone number.");
        }
        if (text.length() > 20)
            throw Generate.throwNotFoundExceptionMessage("Maximum phone number is 20 characters.");
        if (text.length() < 5)
            throw Generate.throwNotFoundExceptionMessage("Minimum phone number is 5 characters.");
    }

}

