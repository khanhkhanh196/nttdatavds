package com.example.demo.common;

import java.util.regex.Pattern;

public class Regex
{
    private static final String regex = "[+^*$#@%]+";
    public static final Pattern noSpecialChar = Pattern.compile(regex);
}
