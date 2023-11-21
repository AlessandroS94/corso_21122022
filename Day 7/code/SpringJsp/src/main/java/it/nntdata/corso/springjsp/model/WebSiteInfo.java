package it.nntdata.corso.springjsp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Entity
@Setter
@Getter
@Table(name = "web_site_info")
public class WebSiteInfo extends BaseEntity {
    
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] logo;
    private String contentType;

    public String getLogoAsBase64() {
        byte[] logo = this.logo;
        if (logo != null && logo.length > 0) {
            return Base64.getEncoder().encodeToString(logo);
        }
        return "";
    }

}