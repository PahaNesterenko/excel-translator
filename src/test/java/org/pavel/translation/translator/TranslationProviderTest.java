package org.pavel.translation.translator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;

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

  @Test
  public void getNumbersFromValue() throws Exception
  {
    List<String> numbers = provider.getNumbersFromValue("$7150 per person / $14300 per group");
    List<String> expected = asList("$7150", "$14300");
    Assert.assertArrayEquals(expected.toArray(), numbers.toArray());
  }

  @Test
  public void getPercentFromValue() throws Exception
  {
    List<String> numbers = provider.getNumbersFromValue("20.00% Coinsurance after deductible");
    List<String> expected = asList("20.00%");
    Assert.assertArrayEquals(expected.toArray(), numbers.toArray());
  }

  @Test
  public void getCurrencyFromValue() throws Exception
  {
    List<String> numbers = provider.getNumbersFromValue("$750.00 Copay per day before deductible");
    List<String> expected = asList("$750.00");
    Assert.assertArrayEquals(expected.toArray(), numbers.toArray());
  }

  @Test
  public void translatePerPersonPerGroup() throws Exception
  {
    String na = provider.translate("$5450 per person / $10900 per group");
    Assert.assertEquals("$5450 por persona/ $10900 por grupo", na);
  }


}