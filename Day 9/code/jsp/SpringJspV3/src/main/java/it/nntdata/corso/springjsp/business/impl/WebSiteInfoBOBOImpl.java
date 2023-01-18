package it.nntdata.corso.springjsp.business.impl;

import it.nntdata.corso.springjsp.business.interfaces.WebSiteInfoBO;
import it.nntdata.corso.springjsp.model.WebSiteInfo;
import it.nntdata.corso.springjsp.repository.WebSiteInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class WebSiteInfoBOBOImpl implements WebSiteInfoBO {
    @Autowired
    WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public it.nntdata.corso.springjsp.model.WebSiteInfo getWebSiteInfo() throws DataAccessException{
        return webSiteInfoRepository.findFirstByOrderByIdDesc();
    }

    @Override
    public void insertWebSiteInfo(it.nntdata.corso.springjsp.model.WebSiteInfo webSiteInfo) throws DataAccessException{
        webSiteInfoRepository.save(webSiteInfo);
    }

    @Override
    public void deleteWebSiteInfo(long id) throws DataAccessException{
        webSiteInfoRepository.deleteById(id);
    }

    @Override
    public it.nntdata.corso.springjsp.model.WebSiteInfo findByIdFile(Long id) throws DataAccessException {
        return webSiteInfoRepository.findById(id).get();
    }


    @Override
    public void uploadFile(Long id, MultipartFile data) throws IOException {
        WebSiteInfo _webSiteInfo = webSiteInfoRepository.getReferenceById(id);
        _webSiteInfo.setData(data.getBytes());
        _webSiteInfo.setType(data.getContentType());
        webSiteInfoRepository.save(_webSiteInfo);
    }

}
