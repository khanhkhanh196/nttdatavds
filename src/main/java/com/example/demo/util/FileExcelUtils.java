package com.example.demo.util;

import com.example.demo.entity.File;
import com.example.demo.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class FileExcelUtils {
    static String[] HEADERs = { "ID", "Name", "URL", "PRODUCTS"};
    static String SHEET = "Files";

    public static ByteArrayInputStream filesToExcel(List<File> entities ) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (File entity : entities) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(entity.getFileId());
                row.createCell(1).setCellValue(entity.getFile_name());
                row.createCell(2).setCellValue(entity.getUrl());
                List<Product> product = entity.getProduct();
                for(int i = 0; i < product.size(); i++) {
                    row.createCell(3).setCellValue(product.get(i).getProductId() + " | " + product.get(i).getProductName());
                    if( i == product.size() - 1) {
                        break;
                    }
                    row = sheet.createRow(rowIdx++);
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to export data to Excel file: " + e.getMessage());
        }
    }
}
