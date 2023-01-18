package com.diemme.domain.mysql;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

	@Column(name = "name", nullable = false)
	@NotEmpty(message = "*Please provide your name")
	private String name;
	@Column(name = "user_name", nullable = false, unique = true)
	@Length(min = 5, message = "*Your user name must have at least 5 characters")
	@NotEmpty(message = "*Please provide a user name")
	private String userName;
	@Column(name = "surname", nullable = false)
	@NotEmpty
	private String surname;
	@Column(name = "email", nullable = false)
	@Email
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	@Column(name = "password", nullable = false)
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "active", nullable = true)
	private Boolean active;

	@Column(name = "fiscal_code", nullable = true)
	private String fiscalCode;
	@Column(name = "address_shipment", nullable = true)
	private String addressShipment;

	@Column(name = "p_iva", nullable = true)
	private String pIva;

	@Column(name = "company_name", nullable = true)
	private String companyName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<ProductShowcase> productShowcases;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<TechnologyShowcase> technologyShowcases;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<QuotationShowcase> quotationShowcases;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<NewsShowcase> newsShowcases;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<ContactShowcase> contactShowcases;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "user_layout", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true), inverseJoinColumns = @JoinColumn(name = "layout_id", referencedColumnName = "id"))
	private Set<Layout> layouts;

}
