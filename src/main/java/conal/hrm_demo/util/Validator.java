package conal.hrm_demo.util;

import conal.hrm_demo.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static conal.hrm_demo.util.Constant.PASSWORD_NOT_MATCH;
import static conal.hrm_demo.util.Constant.WRONG_EMAIL_FORMAT;


public class Validator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);

    public static void validateEmail(String emailAddress) {
        if (emailAddress == null || emailAddress.isBlank())
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Email is empty or null!");
        emailAddress = emailAddress.trim();
        boolean isEmailFormat = isEmailFormat(emailAddress);
        if (!isEmailFormat) {
            throw new ApplicationException(WRONG_EMAIL_FORMAT, HttpStatus.BAD_REQUEST);
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
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Phone number is empty or null!");
        text = text.trim();
        if (text.length() > 20)
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Maximum phone number is 20 characters.");
        if (text.length() < 5)
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Minimum phone number is 5 characters.");
        for (char letter : text.toCharArray()) {
            if (!Character.isDigit(letter))
                throw new ApplicationException(HttpStatus.BAD_REQUEST, "Wrong format phone number.");
        }
    }

}

