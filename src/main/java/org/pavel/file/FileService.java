package org.pavel.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileService
{

  public Collection<File> lookupTemplates()
  {
    return FileUtils.listFiles(new File("origin"), new String[]{"xlsx", "xls"}, false);
  }

  public File createResultFile(File file)
  {
    String resultFileName = "";
    try
    {
      String fileName = FilenameUtils.getBaseName(file.getName());
      String extension = FilenameUtils.getExtension(file.getName());
      if (!Files.isDirectory(Paths.get("translated")))
      {
        new File("translated").mkdir();
      }
      resultFileName = "translated" + System.getProperty("file.separator") + fileName + "_translated" + "." + extension;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return new File(resultFileName);
  }
}
