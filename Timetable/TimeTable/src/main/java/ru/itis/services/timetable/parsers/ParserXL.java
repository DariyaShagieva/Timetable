package ru.itis.services.timetable.parsers;

import com.google.gdata.data.spreadsheet.Column;
import com.google.gdata.data.spreadsheet.Data;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import ru.itis.services.timetable.model.ElectiveCourse;
import ru.itis.services.timetable.model.TimeTable;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParserXL {
  private static Sheet getSheet(String name) {

    InputStream in = null;
    XSSFWorkbook wb = null;

    try {
      in = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
      wb = new XSSFWorkbook(in);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return wb.getSheetAt(0);

  }

  public static void parse(String name) {
    List<String> groups = new ArrayList<String>();
    List<TimeTable> timeTables = new ArrayList<TimeTable>();
    String result = "";
    Iterator<Row> it = getSheet(name).iterator();

    Days day = null;
    String time = "";

    while (it.hasNext()) {
      Row row = it.next();
      Iterator<Cell> cells = row.iterator();
      int i = 0;
      while (cells.hasNext()) {
        Cell cell = cells.next();
            String string = cell.getStringCellValue();
            // получаем группы
            if (string.contains("11-") && string.length() < 7) {
              groups.add(string);
            } else {
              if (Days.withDay(cell.toString()) != null)
                day = Days.withDay(cell.toString());

              Pattern pattern = Pattern.compile("[.].*.[-].*[.]");
              Matcher matcher = pattern.matcher(cell.toString());
              Boolean flag = false;
              while (matcher.find() && cell.toString().length() < 12) {
                flag = true;
                System.out.println(day.getDay());
                System.out.println(cell);
                time = cell.toString();
              }

              if (!flag) {
                if (!cell.toString().equalsIgnoreCase(""))
                System.out.println(i + " " + cell);
                if (i < groups.size() && day != null) {
                  ElectiveCourse electiveCourse = ElectiveCourse.builder().title(cell.toString()).build();
                  TimeTable timeTable = TimeTable.builder()
                    .group(groups.get(i))
                    .course(electiveCourse)
                    .day(day)
                    .time(time)
                    .build();
                  timeTables.add(timeTable);
                }
                  i++;
              }



            }
            result += cell.getStringCellValue() + "=";
        }
      result += "\n";

    }
    }


  public static void main(String[] args) {
    parse("timetable_2019-2020 2сем.xlsx");
  }
}
