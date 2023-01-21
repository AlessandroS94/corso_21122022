package it.corso.example.thymeleafproject.business.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.corso.example.thymeleafproject.business.interfaces.AssetBO;
import it.corso.example.thymeleafproject.model.Asset;
import it.corso.example.thymeleafproject.repository.AssetRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AssetBOImpl implements AssetBO {
    @Autowired
    AssetRepository assetRepository;

    @Override
    public List<Asset> getAsset(){
        return assetRepository.findAll();
    }

    @Override
    public Page<Asset> findAll(Pageable paging) {
        return assetRepository.findAll(paging);
    }

    @Override
    public float getCurrentValue() {
        return 0;
    }

    //documentation conversion on https://www.blockchain.com/explorer/api/exchange_rates_api

    @SneakyThrows
    @Override
    public float getCurrentValue(String from,String to)  {
        final String uri = "https://cex.io/api/ticker/"+ from.toUpperCase() + "/" + to.toUpperCase();
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();
        if(!result.equals("{\"error\":\"Invalid Symbols Pair\"}")) {
            float mapped = Float.valueOf(mapper.readTree(result).get("last").asText());
            return mapped;
        }
        return 0;
    }

    @Override
    public void createAsset(Asset asset){
        assetRepository.save(asset);
    }

    @Override
    public void deleteAsset(long id){
        assetRepository.deleteById(id);
    }

}
