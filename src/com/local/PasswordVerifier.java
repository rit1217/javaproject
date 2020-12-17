package com.local;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author rit
 */
public class PasswordVerifier {
    final static private int MINIMUM_PASSWORD_LENGTH = 4;
    public static boolean isMinimumLength(String inString)
    {
        return inString.length() >= MINIMUM_PASSWORD_LENGTH;
    }

    public static boolean hasUpperCase ( String inString )
    {
        return !inString.equals(inString.toLowerCase());
    }

    public static boolean hasLowerCase ( String inString )
    {
        return !inString.equals(inString.toUpperCase());
    }

    public static boolean hasDigit ( String inString )
    {
        return inString.matches("(.*)(\\d+)(.*)");
    }

    public static boolean hasLegalChars ( String inString )
    {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]*");
        Matcher matcher = pattern.matcher(inString);
        return matcher.matches();
    }

    public static boolean isValid ( String inString )
    {
        return hasLegalChars(inString) && hasDigit(inString) && hasUpperCase(inString) &&
                hasLowerCase(inString) && isMinimumLength(inString);

    }

}

