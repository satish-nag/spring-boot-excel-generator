package com.example.springbootexcelgenerator.service.impl;

import com.example.springbootexcelgenerator.service.IExcelGeneratorService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelGeneratorService implements IExcelGeneratorService {
    public ExcelGeneratorService() {
    }

    public ByteArrayOutputStream generateExcelData() {
        ByteArrayOutputStream excelSheetBytes = new ByteArrayOutputStream();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet dummyDataSheet = xssfWorkbook.createSheet("Dummy Data");
        dummyDataSheet.createRow(0).getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("abc");

        try {
            xssfWorkbook.write(excelSheetBytes);
            return excelSheetBytes;
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }
}