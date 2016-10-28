package org.pavel.translation.reader;

import org.junit.Assert;
import org.junit.Test;
import org.pavel.properties.PropertyReader;

public class PropertyReaderTest
{

  @Test
  public void testprops(){
    PropertyReader pr = new PropertyReader();
    pr.readAndSetProperties();
    Assert.assertNotNull(System.getProperty("translations.file.name"));
  }

}