package org.pavel.translation.data;

import java.util.Map;

public class TranslationWorkData
{

  private Map<String, String> translationMap;
  private Map<String, String> translationPatterns;

  public Map<String, String> getTranslationMap()
  {
    return translationMap;
  }

  public void setTranslationMap(Map<String, String> translationMap)
  {
    this.translationMap = translationMap;
  }

  public Map<String, String> getTranslationPatterns()
  {
    return translationPatterns;
  }

  public void setTranslationPatterns(Map<String, String> translationPatterns)
  {
    this.translationPatterns = translationPatterns;
  }
}
