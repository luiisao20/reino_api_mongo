package com.lbravo.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "info")
public class Info {
  @Id
  private String id;

  private String name;

  private String lastName;

  private Integer age;

  private String category;

  private Boolean needsTransport;

  private Integer passengers;

  private Boolean attendsLunch;

  private Boolean confirmed;

  private Boolean winner = false;

  @CreatedDate
  private Instant createdAt;
}
