package ru.itis.timetable.model.user;

public enum UserRole {
    ADMIN("Администратор"),
    USER ("Пользователь");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
