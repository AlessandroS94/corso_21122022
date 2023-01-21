package it.corso.example.thymeleafproject.business.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.corso.example.thymeleafproject.model.Asset;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssetBO {
    List<Asset> getAsset();

    Page<Asset> findAll(Pageable paging);
    float getCurrentValue();
    @SneakyThrows
    float getCurrentValue(String from,String to);

    void createAsset(Asset asset);

    void deleteAsset(long id);
}
