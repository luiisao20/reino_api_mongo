package com.lbravo.dto;

import java.time.Instant;
import java.util.List;

import com.lbravo.entity.Info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawResponse {
  private String id;

  private Instant createdAt;

  private List<Info> infos;
}
