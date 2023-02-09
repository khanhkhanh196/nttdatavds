package com.example.demo.util;


import org.springframework.web.multipart.MultipartFile;


public class ExcelUtils {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
}
