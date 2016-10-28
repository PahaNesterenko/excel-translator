package org.pavel.translation.translator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.pavel.translation.data.TranslationWorkData;
import org.pavel.translation.reader.TranslationReader;

public class TranslationProvider
{
  private TranslationWorkData translationData;
  // Matches 30% and $4.33 and $5,560
  private String regExpForNumber = "^\\$?\\d*[.,]?\\d+?%?$";

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
      List<String> numbers = getNumbersFromValue(englishValue);
      String translationValue = String.format(template, numbers.toArray()).trim();
      return translationValue;
    }

    return StringUtils.SPACE;

  }

  public List<String> getNumbersFromValue(String value) {
    return Arrays.stream(value.split(StringUtils.SPACE))
        .filter(s -> Pattern.compile(regExpForNumber).matcher(s).matches())
        .collect(Collectors.toList());
  }
}
