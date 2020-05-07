package ru.itis.services.timetable.parsers;

import java.util.NoSuchElementException;

public enum  Days {
  MONDAY("понедельник"),
  TUESDAY("вторник"),
  WEDNESDAY ("среда"),
  THURSDAY ("четверг"),
  FRIDAY ("пятница"),
  SATURDAY ("суббота");

  private final String day;

  Days(String day) {
    this.day = day;
  }

  public String getDay() {
    return day;
  }

  public static Days withDay(String day) {
    // обходим все возможные значения
    for (Days days : values()) {

      if (days.day.equalsIgnoreCase(day)) {
        return days;
      }
    }
    return null;
  }
}
