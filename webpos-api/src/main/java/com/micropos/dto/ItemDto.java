package com.micropos.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.micropos.dto.ProductDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * An item.
 */

@Schema(name = "Item", description = "An item.")
@JsonTypeName("Item")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-19T20:34:28.679042203+08:00[Asia/Shanghai]", comments = "Generator version: 7.5.0")
public class ItemDto {

  private ProductDto product;

  private Integer amount;

  private Integer id;

  public ItemDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ItemDto(ProductDto product, Integer amount) {
    this.product = product;
    this.amount = amount;
  }

  public ItemDto product(ProductDto product) {
    this.product = product;
    return this;
  }

  /**
   * Get product
   * @return product
  */
  @NotNull @Valid 
  @Schema(name = "product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("product")
  public ProductDto getProduct() {
    return product;
  }

  public void setProduct(ProductDto product) {
    this.product = product;
  }

  public ItemDto amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  /**
   * The Amount of the item.
   * @return amount
  */
  @NotNull 
  @Schema(name = "amount", description = "The Amount of the item.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public ItemDto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the item.
   * @return id
  */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, description = "The ID of the item.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
    ItemDto item = (ItemDto) o;
    return Objects.equals(this.product, item.product) &&
        Objects.equals(this.amount, item.amount) &&
        Objects.equals(this.id, item.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, amount, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemDto {\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

