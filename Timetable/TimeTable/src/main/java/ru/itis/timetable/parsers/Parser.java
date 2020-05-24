package ru.itis.timetable.parsers;

import java.io.InputStream;

public interface Parser {
  void parseAndSave(InputStream inputStream);
}
