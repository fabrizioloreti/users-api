package com.test.usersapi.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-03-04T14:21:23.880Z[GMT]")


public class User   {

  public User() {

  }

  public User(BigDecimal id, String name, String surname, String email, String password, String address, String city, String zipcode, String country) {
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.email = email;
      this.password = password;
      this.address = address;
      this.city = city;
      this.zipcode = zipcode;
      this.country = country;
  }

  @JsonProperty("id")
  private BigDecimal id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("surname")
  private String surname = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("zipcode")
  private String zipcode = null;

  @JsonProperty("country")
  private String country = null;

  public User id(BigDecimal id) {
    this.id = id;
    return this;
  }

  /**
   * the user id
   * @return id
   **/
  @Schema(description = "the user id")
  
    @Valid
    public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * the user name
   * @return name
   **/
  @Schema(required = true, description = "the user name")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User surname(String surname) {
    this.surname = surname;
    return this;
  }

  /**
   * the user surname
   * @return surname
   **/
  @Schema(required = true, description = "the user surname")
      @NotNull

    public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * the user email
   * @return email
   **/
  @Schema(required = true, description = "the user email")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * the user password
   * @return password
   **/
  @Schema(required = true, description = "the user password")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User address(String address) {
    this.address = address;
    return this;
  }

  /**
   * the user address
   * @return address
   **/
  @Schema(description = "the user address")
  
    public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public User city(String city) {
    this.city = city;
    return this;
  }

  /**
   * the user city
   * @return city
   **/
  @Schema(description = "the user city")
  
    public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public User zipcode(String zipcode) {
    this.zipcode = zipcode;
    return this;
  }

  /**
   * the user zipcode
   * @return zipcode
   **/
  @Schema(description = "the user zipcode")
  
    public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public User country(String country) {
    this.country = country;
    return this;
  }

  /**
   * the user country
   * @return country
   **/
  @Schema(description = "the user country")
  
    public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.surname, user.surname) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.address, user.address) &&
        Objects.equals(this.city, user.city) &&
        Objects.equals(this.zipcode, user.zipcode) &&
        Objects.equals(this.country, user.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, surname, email, password, address, city, zipcode, country);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    zipcode: ").append(toIndentedString(zipcode)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
