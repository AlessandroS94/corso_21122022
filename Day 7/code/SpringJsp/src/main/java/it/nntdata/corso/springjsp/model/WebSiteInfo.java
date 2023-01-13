package it.nntdata.corso.springjsp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "web_site_info")
public class WebSiteInfo extends BaseEntity {
    private String name;
    private String description;
}