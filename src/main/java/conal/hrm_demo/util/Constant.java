package conal.hrm_demo.util;

public interface Constant {

    String HEADER_TOKEN = "Auth-Token";
    // API format date
    String API_FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss a";
    String API_FORMAT_DATE = "dd/MM/yyyy";

    String VALID_XSS = "^((?!<|>)[\\s\\S])*?$";
    String VALID_CURLY_BRACES = "^((?!\\{|\\})[\\s\\S])*?$";

    int SALT_LENGTH = 6;

    String SORT_BY_USER_NAME = "userName";
    String SORT_BY_EMAIL = "email";
    String SORT_BY_LAST_NAME = "lastName";
    String SORT_BY_FIRST_NAME = "firstName";
    public static final String SHOE_NOT_FOUND = "Shoe with id %s not found";

    public static final String USER_NOT_FOUND ="User %s not found";
    public static final String USER_EXISTED ="User %s already exist";
    public static final String  WRONG_EMAIL_FORMAT ="Wrong email format";
    public static final String  PASSWORD_NOT_MATCH ="Password is not match";


}


