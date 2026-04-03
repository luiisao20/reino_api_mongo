package com.lbravo.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbravo.entity.Info;

@Service
public class ExcelExportService {
  @Autowired
  private InfoService infoService;

  public ByteArrayInputStream exportToExcel() throws IOException {
    List<Info> infos = infoService.getAllList();

    String[] columns = { "Nombre", "Apellido", "Celular", "Actividad", "Razones", "Acepta envío de información" };

    try (Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      Sheet sheet = workbook.createSheet("Participantes");
      Font headerFont = workbook.createFont();
      headerFont.setBold(true);
      headerFont.setColor(IndexedColors.WHITE.getIndex());

      CellStyle headerCellStyle = workbook.createCellStyle();
      headerCellStyle.setFont(headerFont);
      headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
      headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      Row headerRow = sheet.createRow(0);
      for (int col = 0; col < columns.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(columns[col]);
        cell.setCellStyle(headerCellStyle);
      }

      int rowIdx = 1;

      for (Info info : infos) {
        Row row = sheet.createRow(rowIdx++);
        row.createCell(0).setCellValue(info.getName());
        row.createCell(1).setCellValue(info.getLastName());
        row.createCell(2).setCellValue(info.getPhone());
        row.createCell(3).setCellValue(info.getActivity());
        row.createCell(4).setCellValue(String.join(", ", info.getReasons()));
        row.createCell(5).setCellValue(info.getAccept() ? "Sí": "No");
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    }
  }
}
