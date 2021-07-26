package com.robee103.cafemgr.utils;

public class LogUtil {
  public LogUtil() {
  }

  public static void info(String str) {
    System.out.println(str);
  }

  public static void info(String format, String str) {
    System.out.printf(format, str);
  }

  public static String printCenter(String str, Integer width) {
    Integer padding = (width-str.length())/2;
    String paddedString  = " ".repeat(padding);
    return String.format("%s%s",paddedString,str);
  }

  public static void error(String str) {
    System.err.println(str);
  }

}
