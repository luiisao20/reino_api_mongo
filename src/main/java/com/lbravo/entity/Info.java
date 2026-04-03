package com.lbravo.entity;

import java.time.Instant;
import java.util.List;

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

  private Boolean accept = false;

  private Boolean winner = false;

  private String phone;

  private String activity;

  private List<String> reasons;

  @CreatedDate
  private Instant createdAt;
}
