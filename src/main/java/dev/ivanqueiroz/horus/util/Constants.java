package dev.ivanqueiroz.horus.util;

public final class Constants {
  private Constants() {
    throw new IllegalCallerException("Cant instantiate this class!");
  }

  public static final long SECOND = 1000;
  public static final long MINUTES = SECOND * 60;
  public static final long HOUR = MINUTES * 60;
}
