package org.pavel.translation.translator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TranslationProviderTest
{

  TranslationProvider provider = null;

  @Before
  public void init(){
    System.setProperty("translations.file.name", "testTranslations.xlsx");
    provider = new TranslationProvider();
  }

  @Test
  public void translate() throws Exception
  {
    String na = provider.translate("NA");
    String noCharge = provider.translate("No Charge");
    Assert.assertEquals("NA (no se aplica)", na);
    Assert.assertEquals("Sin costo", noCharge);
  }

  @Test
  public void translatePercent() throws Exception
  {
    String na = provider.translate("0.00%");
    Assert.assertEquals("0.00%", na);
  }

  @Test
  public void translateBacks() throws Exception
  {
    String na = provider.translate("$0.00");
    Assert.assertEquals("$0.00", na);
  }

  @Test
  public void translateBacksUnique() throws Exception
  {
    String na = provider.translate("$6,350");
    Assert.assertEquals("$6,350", na);
  }

}