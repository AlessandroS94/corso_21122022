package it.corso.example.thymeleafproject.business.impl;

import it.corso.example.thymeleafproject.business.interfaces.AssetBO;
import it.corso.example.thymeleafproject.model.Asset;
import it.corso.example.thymeleafproject.repository.AssetRepository;
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

    //documentation conversion on https://www.blockchain.com/explorer/api/exchange_rates_api
    public float getCurrentValue() {

        final String uri = "https://blockchain.info/tobtc?currency=EUR&value=10000";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return ((Float.valueOf(result)));
    }

}
