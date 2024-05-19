package com.micropos.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A product.
 */

@Schema(name = "Product", description = "A product.")
@JsonTypeName("Product")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-19T20:34:28.679042203+08:00[Asia/Shanghai]", comments = "Generator version: 7.5.0")
public class ProductDto {

  private String name;

  private Double price;

  private String image;

  private String id;

  public ProductDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProductDto(String name, Double price, String image, String id) {
    this.name = name;
    this.price = price;
    this.image = image;
    this.id = id;
  }

  public ProductDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the product.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "The name of the product.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductDto price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * The price of the product.
   * @return price
  */
  @NotNull 
  @Schema(name = "price", description = "The price of the product.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ProductDto image(String image) {
    this.image = image;
    return this;
  }

  /**
   * The path to image.
   * @return image
  */
  @NotNull 
  @Schema(name = "image", description = "The path to image.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("image")
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public ProductDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the product.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The ID of the product.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDto product = (ProductDto) o;
    return Objects.equals(this.name, product.name) &&
        Objects.equals(this.price, product.price) &&
        Objects.equals(this.image, product.image) &&
        Objects.equals(this.id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price, image, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

