package org.pavel.translation.reader;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pavel.translation.data.TranslationWorkData;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class TranslationReader
{

  public TranslationWorkData readTranslationData()
  {
    TranslationWorkData data = new TranslationWorkData();
    try
    {
      FileInputStream fis = new FileInputStream(new File(System.getProperty("translations.file.name")));

      XSSFWorkbook workbook = new XSSFWorkbook(fis);

      readTranslations(workbook, data);
      readPatterns(workbook, data);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return data;
  }

  private void readPatterns(XSSFWorkbook workbook, TranslationWorkData data)
  {
    XSSFSheet patternSheet = workbook.getSheet("Patterns");
    Map<String, String> patternsMap = getMapDataFromSheet(patternSheet);
    data.setTranslationPatterns(patternsMap);
  }

  private void readTranslations(XSSFWorkbook workbook, TranslationWorkData data)
  {
    XSSFSheet translationSheet = workbook.getSheet("Translations");
    Map<String, String> translationMap = getMapDataFromSheet(translationSheet);
    data.setTranslationMap(translationMap);
  }

  private Map<String, String> getMapDataFromSheet(XSSFSheet sheet)
  {
    Map<String, String> result = new HashMap<>();
    int lastRowNum = sheet.getLastRowNum();
    for (int i = 1; i <= lastRowNum; ++i)
    {
      XSSFRow row = sheet.getRow(i);
      if (notEmptyRow(row))
      {
        String english = row.getCell(0).getStringCellValue();
        String translation = row.getCell(1).getStringCellValue();
        result.put(english, translation);
      }
    }
    return result;
  }

  private boolean notEmptyRow(XSSFRow row)
  {
    return row != null && row.getCell(0) != null && row.getCell(1) != null && isNotEmpty(row.getCell(0).getStringCellValue())
        && isNotEmpty(row.getCell(1).getStringCellValue());
  }

}
