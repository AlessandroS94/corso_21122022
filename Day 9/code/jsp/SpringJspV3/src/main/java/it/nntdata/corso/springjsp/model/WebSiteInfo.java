package it.nntdata.corso.springjsp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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

    @OneToMany(mappedBy = "webSiteInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CryptoInfo> cryptoInfoes = new LinkedHashSet<>();

}