package com.fif;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {

  public static void main(String[] args) {
    String encryptedPassword = passwordEncoder().encode("user");
    System.out.println(encryptedPassword);
  }

  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
