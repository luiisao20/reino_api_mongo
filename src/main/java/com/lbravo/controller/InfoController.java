package com.lbravo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lbravo.entity.Info;
import com.lbravo.service.InfoService;

@RestController
@RequestMapping("/products")
public class InfoController {
  @Autowired
  private InfoService infoService;

  @GetMapping
  public Page<Info> getAll(@RequestParam(defaultValue = "") String name,
      @PageableDefault(size = 10) Pageable pageable) {
    return infoService.getAll(name, pageable);
  }

  @GetMapping("/{id}")
  public Optional<Info> getById(@PathVariable String id) {
    return infoService.getById(id);
  }

  @PostMapping("/save")
  public Info save(@RequestBody Info product) {
    return infoService.save(product);
  }

  @PutMapping("/update/{id}")
  public Info update(@PathVariable String id, @RequestBody Info product) {

    return infoService.update(id, product);
  }

  @PatchMapping("/updateWinner/{id}")
  public Info updateWinner(@PathVariable String id, @RequestBody Boolean winner) {
    return infoService.updateWinnerInfo(id, winner);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable String id) {
    infoService.deleteProduct(id);
  }

  @DeleteMapping("/delete/all")
  public void deleteAll() {
    infoService.deleteAll();
  }
}
