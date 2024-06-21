package com.emsi.salesmasterbe2.payload.request;

import java.util.ArrayList;

public class FakeVenteRequest {

    private long clientId;
    private long[] productIds;
    private int[] quantities;

    public FakeVenteRequest(long clientId, long[] productIds, int[] quantities) {
        if (productIds.length != quantities.length) {
            throw new IllegalArgumentException("The size of productIds and quantities arrays must be the same.");
        }
        this.clientId = clientId;
        this.productIds = productIds;
        this.quantities = quantities;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long[] getProductIds() {
        return productIds;
    }

    public void setProductIds(long[] productIds) {
        this.productIds = productIds;
    }

    public int[] getQuantities() {
        return quantities;
    }

    public void setQuantities(int[] quantities) {
        if (productIds.length != quantities.length) {
            throw new IllegalArgumentException("The size of productIds and quantities arrays must be the same.");
        }
        this.quantities = quantities;
    }
}
