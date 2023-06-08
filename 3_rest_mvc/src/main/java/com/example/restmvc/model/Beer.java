package com.example.restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
public class Beer {
    private UUID id;
    private String name;
    private BeerStyle style;
    private String upc;
    private BigDecimal price;
}
