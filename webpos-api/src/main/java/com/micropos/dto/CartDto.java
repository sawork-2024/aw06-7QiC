package com.micropos.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.micropos.dto.ItemDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A cart.
 */

@Schema(name = "Cart", description = "A cart.")
@JsonTypeName("Cart")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-19T20:34:28.679042203+08:00[Asia/Shanghai]", comments = "Generator version: 7.5.0")
public class CartDto {

  @Valid
  private List<ItemDto> items = new ArrayList<>();

  private Integer id;

  public CartDto items(List<ItemDto> items) {
    this.items = items;
    return this;
  }

  public CartDto addItemsItem(ItemDto itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * The items of the cart.
   * @return items
  */
  @Valid 
  @Schema(name = "items", description = "The items of the cart.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("items")
  public List<ItemDto> getItems() {
    return items;
  }

  public void setItems(List<ItemDto> items) {
    this.items = items;
  }

  public CartDto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * The ID of the cart.
   * @return id
  */
  
  @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY, description = "The ID of the cart.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    CartDto cart = (CartDto) o;
    return Objects.equals(this.items, cart.items) &&
        Objects.equals(this.id, cart.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CartDto {\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

