package it.nntdata.corso.springjsp.business.interfaces;

import it.nntdata.corso.springjsp.model.WebSiteInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WebSiteInfoBO {

    public WebSiteInfo getWebSiteInfo() throws DataAccessException;

    void insertWebSiteInfo(WebSiteInfo webSiteInfo) throws DataAccessException;

    void deleteWebSiteInfo(long id) throws DataAccessException;

    WebSiteInfo findByIdFile(Long id) throws DataAccessException;
    void uploadFile(Long id, MultipartFile data) throws IOException ;
}
