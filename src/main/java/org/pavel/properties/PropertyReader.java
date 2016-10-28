package org.pavel.properties;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class PropertyReader
{

  public void readAndSetProperties()
  {
    Properties prop = new Properties();
    try
    {
      String propFileName = "tool.properties";

      //inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
      InputStream inputStream = null;

      inputStream = FileUtils.openInputStream(new File(propFileName));

      prop.load(inputStream);

      System.setProperties(prop);

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

}
