package com.lbravo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbravo.service.ExcelExportService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/export")
public class ExcelController {
  @Autowired
  private ExcelExportService excelExportService;

  @GetMapping("/excel")
  public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {
    ByteArrayInputStream in = excelExportService.exportToExcel();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=participantes.xlsx");
    return ResponseEntity
        .ok()
        .headers(headers)
        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        .body(new InputStreamResource(in));
  }
}
