package tk.mwacha.helpers;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileContentTestHelper {

  @SneakyThrows
  public static String getContentAsString(String filePath) {

    ClassLoader classLoader = FileContentTestHelper.class.getClassLoader();
    File file = new File(classLoader.getResource(filePath).getFile());
    String absolutePath = file.getAbsolutePath();

     return Files.readString(Paths.get(absolutePath), StandardCharsets.UTF_8);
  }
}
