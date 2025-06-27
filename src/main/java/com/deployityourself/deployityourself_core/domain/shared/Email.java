
package com.deployityouself.deployityouself_core.domain.shared;

import java.util.regex.Pattern;

public class Email {

    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email of(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException(email);
        }
        return new Email(email)
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email comparedEmail = (Email) o;
        return email.equals(comparedEmail.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}