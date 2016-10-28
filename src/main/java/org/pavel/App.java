package org.pavel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pavel.properties.PropertyReader;
import org.pavel.translation.translator.ExcelTranslator;

public class App
{
  public static void main(String[] args)
  {

    //Map<String, String> translationMap = getTranslations();

    readAndSetProperties();

    XSSFWorkbook workbookToPopulate = getWorkbookToPopulate();

    ExcelTranslator populator = new ExcelTranslator();

    populator.populateWorkbook(workbookToPopulate);

    writeResultToFile(workbookToPopulate);

    System.out.println("Hello World!");
  }

  private static void writeResultToFile(XSSFWorkbook workbookToPopulate)
  {
    try
    {
      workbookToPopulate.write(new FileOutputStream(new File("result.xlsx")));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static XSSFWorkbook getWorkbookToPopulate()
  {
    try
    {
      FileInputStream fis = new FileInputStream(new File("TRANSLATIONS_es.xlsx"));
      return new XSSFWorkbook(fis);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

  private static void readAndSetProperties()
  {

    PropertyReader propertyReader = new PropertyReader();
    propertyReader.readAndSetProperties();

  }

}
