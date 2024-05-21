package com.emsi.salesmasterbe2.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VenteResponseTable extends VenteResponse{
    private int totalProductTypes;
}
