package org.pavel.translation.data;

public class SheetMetadata
{

  private int headerRowIndex = 2;
  private int startRowIndex = 3;
  private int englishStartCol;
  private int translateStartCol;
  private int lastRowNum;
  private int translationOffset;

  public int getHeaderRowIndex()
  {
    return headerRowIndex;
  }

  public void setHeaderRowIndex(int headerRowIndex)
  {
    this.headerRowIndex = headerRowIndex;
  }

  public int getStartRowIndex()
  {
    return startRowIndex;
  }

  public void setStartRowIndex(int startRowIndex)
  {
    this.startRowIndex = startRowIndex;
  }

  public int getEnglishStartCol()
  {
    return englishStartCol;
  }

  public void setEnglishStartCol(int englishStartCol)
  {
    this.englishStartCol = englishStartCol;
  }

  public int getTranslateStartCol()
  {
    return translateStartCol;
  }

  public void setTranslateStartCol(int translateStartCol)
  {
    this.translateStartCol = translateStartCol;
  }

  public int getLastRowNum()
  {
    return lastRowNum;
  }

  public void setLastRowNum(int lastRowNum)
  {
    this.lastRowNum = lastRowNum;
  }

  public int getTranslationOffset()
  {
    return translationOffset;
  }

  public void setTranslationOffset(int translationOffset)
  {
    this.translationOffset = translationOffset;
  }
}
