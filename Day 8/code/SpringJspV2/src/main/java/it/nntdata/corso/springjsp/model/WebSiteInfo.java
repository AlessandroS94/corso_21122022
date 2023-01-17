package it.nntdata.corso.springjsp.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "web_site_info")
public class WebSiteInfo extends BaseEntity {
    
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] data;

    @Getter
    @Setter
    private String type;

}