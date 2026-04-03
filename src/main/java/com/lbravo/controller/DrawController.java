package com.lbravo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lbravo.dto.DrawResponse;
import com.lbravo.entity.Draw;
import com.lbravo.entity.Info;
import com.lbravo.service.DrawService;
import com.lbravo.service.InfoService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/draws")
public class DrawController {
  @Autowired
  private DrawService drawService;

  @Autowired
  private InfoService infoService;

  @GetMapping
  public List<DrawResponse> getAll() {
    List<Draw> draws = drawService.getAll();

    return draws.stream().map(draw -> {
      List<Info> infos = infoService.findAllByIds(draw.getWinners());
      return new DrawResponse(draw.getId(), draw.getCreatedAt(), infos);
    }).toList();
  }

  @GetMapping("/do")
  public DrawResponse doDraw(@RequestParam(defaultValue = "1") String size) {
    List<Info> infos = infoService.getAllList();
    DrawResponse draw = new DrawResponse();
    draw.setInfos(new ArrayList<Info>());
    Random rand = new Random();
    Integer sizeInt = Integer.parseInt(size);
    Integer i = 0;

    while (i < sizeInt) {
      int randomIndex = rand.nextInt(infos.size());
      Info randomInfo = infos.get(randomIndex);
      if (!draw.getInfos().contains(randomInfo)) {
        draw.getInfos().add(randomInfo);
        i++;
      }
    }
    return draw;
  }

  @GetMapping("/{id}")
  public DrawResponse getDraw(@PathVariable String id) {
    Draw draw = drawService.getById(id)
        .orElseThrow(() -> new RuntimeException("Draw not found"));

    List<Info> infos = infoService.findAllByIds(draw.getWinners());
    return new DrawResponse(draw.getId(), draw.getCreatedAt(), infos);
  }

  @PostMapping("/save")
  public Draw save(@RequestBody Draw draw) {
    return drawService.save(draw);
  }

  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable String id) {
    drawService.delete(id);
  }
}
