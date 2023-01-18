package it.nntdata.corso.springjsp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "section")
public class CryptoInfo extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Lob @Lazy
    @Column(name = "image",length = 10000000)
    private byte[] image;

    @Column(name = "typeImg")
    String typeImg;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "web_site_info_id")
    private WebSiteInfo webSiteInfo;

}