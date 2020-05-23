package ru.itis.timetable.model;

public enum Time {

    FIRST("8:30"),
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

}
