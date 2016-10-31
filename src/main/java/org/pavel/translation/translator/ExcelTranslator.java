package org.pavel.translation.translator;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pavel.translation.data.SheetMetadata;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ExcelTranslator
{

  private TranslationProvider translationProvider = new TranslationProvider();

  public void populateWorkbook(
      XSSFWorkbook workbookToPopulate)
  {
    List<String> sheetsToPopulate = Arrays.asList(System.getProperty("excel.sheet.to.translate").split(","));
    sheetsToPopulate = sheetsToPopulate.stream().map(String::trim).collect(Collectors.toList());

    XSSFSheet currentSheet = null;
    for( String sheetName:sheetsToPopulate) {
      currentSheet = workbookToPopulate.getSheet(sheetName);
      populateSheet(currentSheet);
    }
  }

  public void populateSheet(XSSFSheet sheet){

    SheetMetadataReader metadataReader = new SheetMetadataReader();
    SheetMetadata metadata = metadataReader.readMetadata(sheet);

    for (int x = metadata.getStartRowIndex(); x <= metadata.getLastRowNum(); ++x){

      XSSFRow row = sheet.getRow(x);

      for(int y = metadata.getEnglishStartCol(); y < metadata.getTranslateStartCol(); ++y){


        String englishValue = row.getCell(y).getStringCellValue();
        if(isNotBlank(englishValue))
        {
          String translatedValue = row.getCell(y + metadata.getTranslationOffset()).getStringCellValue();
          if (isBlank(translatedValue))
          {
            translatedValue = translationProvider.translate(englishValue.trim());

            if(isBlank(translatedValue)){
              XSSFCell transCell = row.getCell(y + metadata.getTranslationOffset());

              XSSFCellStyle cellStyle = (XSSFCellStyle) transCell.getCellStyle().clone();

              cellStyle.setFillForegroundColor(new XSSFColor(Color.ORANGE));

              transCell.setCellStyle(cellStyle);
            }
            else{
              row.getCell(y + metadata.getTranslationOffset()).setCellValue(translatedValue);
            }
          }
        }

      }

    }

  }

}
