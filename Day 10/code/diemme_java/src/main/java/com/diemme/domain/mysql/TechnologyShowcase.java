package com.diemme.domain.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tecnology_showcase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TechnologyShowcase extends BaseModel {
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	@Lob
	@Column(name = "content_img", length = 100000, nullable = false)
	private byte[] contentImg;
	@Lob
	@Column(name = "compress_img", length = 100000, nullable = true)
	private byte[] compressImg;
	@Column(name = "description", nullable = false)
	@NotBlank
	private String description;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}
