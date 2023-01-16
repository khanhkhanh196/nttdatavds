package com.example.demo.common;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern noSpecialChar = Pattern.compile("'^[^*&%\\s]+$'", Pattern.CASE_INSENSITIVE);
}
