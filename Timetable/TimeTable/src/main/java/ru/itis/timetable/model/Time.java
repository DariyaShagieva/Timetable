package ru.itis.timetable.model;

public enum Time {

    FIRST("08:30"),
    SECOND("10:10"),
    THIRD("11:50"),
    FORTH("14:00"),
    FIFTH("15:40"),
    SIXTH("17:50"),
    SEVENTH("19:30");

    private final String time;

   Time(String time) {
       this.time = time;
   }

   public String getTime() {
       return time;
   }

  public static Time withTime(String timeStr) {
     timeStr = timeStr.replace('.',':' );
    // обходим все возможные значения
    for (Time time : values()) {

      if (time.time.equalsIgnoreCase(timeStr)) {
        return time;
      }
    }
    return null;
  }

}
