package ru.itis.services.timetable.parsers;

import com.google.api.services.sheets.v4.SheetsScopes;
import ru.itis.services.timetable.model.user.User;

import java.util.Collections;
import java.util.List;

public class ParserGoogle {
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    public void parse () {


    }

}
