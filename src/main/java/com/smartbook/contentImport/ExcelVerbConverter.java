package com.smartbook.contentImport;

import com.smartbook.entity.VerbGroupMark;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelVerbConverter {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "FirstForm", "SecondForm", "ThirdForm" };
    static String SHEET = "Verbs";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

//    public static ByteArrayInputStream verbsToExcel(List<Verb> verbs) {
//
//        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
//            Sheet sheet = workbook.createSheet(SHEET);
//
//            // Header
//            Row headerRow = sheet.createRow(0);
//
//            for (int col = 0; col < HEADERs.length; col++) {
//                Cell cell = headerRow.createCell(col);
//                cell.setCellValue(HEADERs[col]);
//            }
//
//            int rowIdx = 1;
//            for (Verb verb : verbs) {
//                Row row = sheet.createRow(rowIdx++);
//
//                row.createCell(0).setCellValue(verb.getId());
//                row.createCell(1).setCellValue(verb.getFirstForm());
//                row.createCell(2).setCellValue(verb.getSecondForm());
//                row.createCell(3).setCellValue(verb.getThirdForm());
//            }
//
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException e) {
//            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
//        }
//    }

//    public static List<Verb> excelToVerbs(InputStream is) {
//        try {
//            Workbook workbook = new XSSFWorkbook(is);
//
////            Sheet sheet = workbook.getSheet(SHEET);
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            Iterator<Row> rows = sheet.iterator();
//
//            List<Verb> verbs = new ArrayList<>();
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//                // skip header
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                Verb verb = new Verb();
//                VerbGroupMark verbGroupMark = new VerbGroupMark();
//                int cellIdx = 0;
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//
//                    switch (cellIdx) {
//                        case 0:
//                            verb.setId((long) currentCell.getNumericCellValue());
//                            break;
//
//                        case 1:
//                            verb.setFirstForm(currentCell.getStringCellValue());
//                            break;
//
//                        case 2:
//                            verb.setSecondForm(currentCell.getStringCellValue());
//                            break;
//
//                        case 3:
//                            verb.setThirdForm(currentCell.getStringCellValue());
//                            break;
//                        case 4:
//                            verb.setTranslation(currentCell.getStringCellValue());
//                            break;
//                        case 5:
//
//                            verbGroupMark.setId((long) currentCell.getNumericCellValue());
////                            verb.setVerbSortMark(verbSortMark);
//                            break;
//
//                        default:
//                            break;
//                    }
//
//                    cellIdx++;
//                }
//
//                verbs.add(verb);
//            }
//
//            workbook.close();
//
//            return verbs;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }
//    }
}
