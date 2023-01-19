package it.corso.example.thymeleafproject.business.interfaces;

import it.corso.example.thymeleafproject.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssetBO {
    List<Asset> getAsset();

    Page<Asset> findAll(Pageable paging);
    float getCurrentValue();
}
