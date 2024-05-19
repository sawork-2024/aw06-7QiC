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
 * Editable fields of an item.
 */

@Schema(name = "ItemFields", description = "Editable fields of an item.")
@JsonTypeName("ItemFields")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-19T20:34:28.679042203+08:00[Asia/Shanghai]", comments = "Generator version: 7.5.0")
public class ItemFieldsDto {

  private ProductDto product;

  private Integer amount;

  public ItemFieldsDto() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ItemFieldsDto(ProductDto product, Integer amount) {
    this.product = product;
    this.amount = amount;
  }

  public ItemFieldsDto product(ProductDto product) {
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

  public ItemFieldsDto amount(Integer amount) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemFieldsDto itemFields = (ItemFieldsDto) o;
    return Objects.equals(this.product, itemFields.product) &&
        Objects.equals(this.amount, itemFields.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemFieldsDto {\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

