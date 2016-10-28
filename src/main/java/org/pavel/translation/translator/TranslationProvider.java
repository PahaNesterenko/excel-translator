package org.pavel.translation.translator;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.pavel.translation.data.TranslationWorkData;
import org.pavel.translation.reader.TranslationReader;

public class TranslationProvider
{
  private TranslationWorkData translationData;

  public TranslationProvider()
  {
    TranslationReader reader = new TranslationReader();
    translationData = reader.readTranslationData();
  }

  public String translate(String englishValue)
  {
    if (translationData.getTranslationMap().containsKey(englishValue))
    {
      return translationData.getTranslationMap().get(englishValue);
    }

    Map<String, String> collect = translationData.getTranslationPatterns().entrySet().stream()
        .filter(map -> Pattern.compile(map.getKey()).matcher(englishValue).matches())
        .collect(Collectors.toMap((map) -> map.getKey(), (map) -> map.getValue()));

    if (!collect.isEmpty())
    {
      String template = collect.values().stream().findFirst().get();
      Scanner sc = new Scanner(englishValue);
      Double number = sc.nextDouble();
      String translationValue = String.format(template, number);
      return translationValue;
    }

    return "";

  }
}
