package com.lbravo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbravo.entity.Info;
import com.lbravo.repository.InfoRepository;

@Service
public class InfoService {
  @Autowired
  private InfoRepository infoRepository;

  public Info save(Info product) {
    return infoRepository.save(product);
  }

  public List<Info> getAll(String name) {
    if (name == null || name.isEmpty()) {
      return infoRepository.findAllByOrderByCreatedAtDesc();
    } else {
      return infoRepository.findByFullNameContaining(name);
    }
  }

  public Optional<Info> getById(String id) {
    return infoRepository.findById(id);
  }

  public List<Info> findAllByIds(List<String> ids) {
    return infoRepository.findAllById(ids);
  }

  public Info updateWinnerInfo(String id, Boolean winner) {
    Info oldProduct = infoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    oldProduct.setWinner(winner);
    return infoRepository.save(oldProduct);
  }

  public Info update(String id, Info product) {
    Info oldProduct = getById(id)
        .orElseThrow(() -> new RuntimeException("Info not found"));

    oldProduct.setPhone(product.getPhone());
    oldProduct.setName(product.getName());
    oldProduct.setReasons(product.getReasons());
    oldProduct.setActivity(product.getActivity());

    return infoRepository.save(oldProduct);
  }

  public void deleteProduct(String id) {
    Info oldProduct = getById(id)
        .orElseThrow(() -> new RuntimeException("Info not found"));

    infoRepository.delete(oldProduct);
  }

  public void deleteAll() {
    infoRepository.deleteAll();
  }
}
