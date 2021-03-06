package ru.itis.timetable.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DataDto {

    private String title;

    private String teacher;

    private String description;
}
