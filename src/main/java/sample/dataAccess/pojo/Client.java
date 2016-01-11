package sample.dataAccess.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String idNumber;
    private String phoneNumber;
    private String sex;
    private String preferences;

    @Column(unique = true)
    private String pesel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private DictCountry country;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypeId")
    private DictIdType idType;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getIdNumber() {
	return idNumber;
    }

    public void setIdNumber(String idNumber) {
	this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getPreferences() {
	return preferences;
    }

    public void setPreferences(String preferences) {
	this.preferences = preferences;
    }

    public String getPesel() {
	return pesel;
    }

    public void setPesel(String pesel) {
	this.pesel = pesel;
    }

    public DictCountry getCountry() {
	return country;
    }

    public void setCountry(DictCountry country) {
	this.country = country;
    }

    public DictIdType getIdType() {
	return idType;
    }

    public void setIdType(DictIdType idType) {
	this.idType = idType;
    }

}
