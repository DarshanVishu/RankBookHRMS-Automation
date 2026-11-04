package com.rankbook.hrms.automation.mobile.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class ExcelReaderUtil {
    private ExcelReaderUtil() {}

    public static Object[][] getSheetData(String excelResourcePath, String sheetName) {
        List<Object[]> rowsData = new ArrayList<>();
        try (InputStream inputStream = ExcelReaderUtil.class.getClassLoader().getResourceAsStream(excelResourcePath);
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
            int rowCount = sheet.getPhysicalNumberOfRows();
            if (rowCount <= 1) return new Object[0][0];

            int colCount = sheet.getRow(0).getLastCellNum();

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                Object[] cellValues = new Object[colCount];
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cellValues[j] = formatter.formatCellValue(cell);
                }
                rowsData.add(cellValues);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel resource: " + excelResourcePath, e);
        }
        return rowsData.toArray(new Object[0][]);
    }
}