package com.diemme.domain.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "file_layout")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileLayout extends BaseModel {

	@Column(name = "name", nullable = true)
	private String name;

	@Lob
	@Column(name = "content_img", length = 3000000, nullable = false)
	byte[] contentImg;

}
