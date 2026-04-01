package com.lbravo.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.lbravo.roles.Role;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  private String uuid = UUID.randomUUID().toString();

  @Indexed(unique = true)
  private String email;

  @Size(min = 5)
  private String name;

  private String password;

  private Role role;

}
