package org.pavel.translation.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.pavel.translation.data.SheetMetadata;

public class SheetMetadataReader
{

  public SheetMetadata readMetadata(XSSFSheet sheet) {

    SheetMetadata data = new SheetMetadata();

    data.setLastRowNum(sheet.getLastRowNum());

    XSSFRow headerRow = sheet.getRow(data.getHeaderRowIndex());

    short lastCellNum = headerRow.getLastCellNum();
    List<String> headers = new ArrayList<>();
    for(int i = 0; i < lastCellNum; ++i) {
      XSSFCell cell = headerRow.getCell(i);
      if(StringUtils.isBlank(cell.getStringCellValue())){
        continue;
      }
      headers.add(cell.getStringCellValue());
    }

    Map<String, Integer> headersMap = new HashMap<>();

    for(int i = 0; i < headers.size();++i){

      String current = headers.get(i);

      if(headersMap.containsKey(current)) {
        data.setTranslateStartCol(i);
        data.setEnglishStartCol(headersMap.get(current));
        break;
      }
      headersMap.put(current, i);

    }
    data.setTranslationOffset(data.getTranslateStartCol() - data.getEnglishStartCol());

    return data;
  }

}
