package com.example.rain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @ApiModelProperty(notes="Varchar 42 GUID")
    public String id;

    @ApiModelProperty(notes="0 default value")
    public BigDecimal version = BigDecimal.ZERO;

    @ApiModelProperty(notes="System generated")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date dateCreated;

    @ApiModelProperty(notes="System generated")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date lastUpdated;

    @ApiModelProperty(notes="Item Name")
    public String name;

    @ApiModelProperty(notes="Elaboration on that of name given")
    public String description;

    @ApiModelProperty(notes="Item Code")
    public String code;
}
