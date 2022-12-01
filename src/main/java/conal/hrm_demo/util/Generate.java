package conal.hrm_demo.util;

import conal.hrm_demo.exception.ApplicationException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;

import java.util.Random;

public class Generate {

    public static String generateString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);

        return generatedString;
    }

    public static double randomNumber() {
        return new Random(9).nextDouble();
    }

    public static String randomPhone() {
        return RandomStringUtils.randomNumeric(10);
    }

    public static ApplicationException throwNotFoundExceptionMessage(String message) {
     return     ApplicationException.builder().httpStatus(HttpStatus.BAD_REQUEST).message(message).build();
    }
}
