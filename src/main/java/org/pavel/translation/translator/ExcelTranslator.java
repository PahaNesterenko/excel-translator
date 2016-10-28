package org.pavel.translation.translator;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ExcelTranslator
{

private int startRowIndex = 3;
  private int englishStartCol =1;
  private int translateStartCol = 5;

  TranslationProvider translationProvider = new TranslationProvider();

  public void populateWorkbook(
      XSSFWorkbook workbookToPopulate)
  {
    List<String> sheetsToPopulate = Arrays.asList(System.getProperty("excel.sheet.to.translate").split(","));

    XSSFSheet currentSheet = null;
    for( String sheetName:sheetsToPopulate) {
      currentSheet = workbookToPopulate.getSheet(sheetName);
      populateSheet(currentSheet);
    }


  }

  public void populateSheet(XSSFSheet sheet){

    int lastRowNum = sheet.getLastRowNum();
    int translationOffset = translateStartCol - englishStartCol;

    for (int x = startRowIndex; x <= lastRowNum; ++x){

      XSSFRow row = sheet.getRow(x);

      for(int y = englishStartCol; y < translateStartCol; ++y){


        String englishValue = row.getCell(y).getStringCellValue();
        if(isNotBlank(englishValue))
        {
          String translatedValue = row.getCell(y + translationOffset).getStringCellValue();
          if (isBlank(translatedValue))
          {
            translatedValue = translationProvider.translate(englishValue);

            if(isBlank(translatedValue)){
              XSSFCell transCell = row.getCell(y + translationOffset);

              XSSFCellStyle cellStyle = (XSSFCellStyle) transCell.getCellStyle().clone();

              cellStyle.setFillForegroundColor(new XSSFColor(Color.ORANGE));

              transCell.setCellStyle(cellStyle);
            }
            else{
              row.getCell(y + translationOffset).setCellValue(translatedValue);
            }
          }
        }

      }

    }

  }

}
