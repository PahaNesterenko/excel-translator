package org.pavel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pavel.file.FileService;
import org.pavel.properties.PropertyReader;
import org.pavel.translation.translator.ExcelTranslator;

public class App
{
  public static void main(String[] args)
  {

    readAndSetProperties();

    FileService fileService = new FileService();
    Collection<File> filesToPopulate = fileService.lookupTemplates();

    for(File file: filesToPopulate)
    {
      XSSFWorkbook workbookToPopulate = getWorkbookToPopulate(file);

      ExcelTranslator populator = new ExcelTranslator();

      populator.populateWorkbook(workbookToPopulate);

      writeResultToFile(workbookToPopulate, fileService.createResultFile(file));
    }

  }

  private static void writeResultToFile(XSSFWorkbook workbookToPopulate, File file)
  {
    try
    {
      workbookToPopulate.write(new FileOutputStream(file));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static XSSFWorkbook getWorkbookToPopulate(File file)
  {
    try
    {
      FileInputStream fis = new FileInputStream(file);
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
