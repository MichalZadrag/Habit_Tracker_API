package com.example.habit_tracker_api.payload;

import javax.validation.constraints.*;

public class SignUpRequest {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9ąćęłńóśźżĄĘŁŃÓŚŹŻ]{3,}$", message = "Nieprawidłowe imie")
    private String first_name;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9ąćęłńóśźżĄĘŁŃÓŚŹŻ]{3,}$", message = "Nieprawidłowe nazwisko")
    private String last_name;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{4,}$", message = "Nieprawidłowa nazwa użytkownika")
    private String username;

    @NotNull(message = "Nieprawidłowy email")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Nieprawidłowy email")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$/.!%*?&])[A-Za-z\\d@$!/.%*?&]{8,}$",
            message = "Minimum 8 znaków, duża i mała litera, liczba, znak specjalny")
    private String password;


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
