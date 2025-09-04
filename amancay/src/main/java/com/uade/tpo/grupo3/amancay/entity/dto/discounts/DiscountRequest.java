package com.uade.tpo.grupo3.amancay.entity.dto.discounts;

public class DiscountRequest {

    private Long id;
    private Double percentage;
    private String description;

    public DiscountRequest() {
    }

    public DiscountRequest(Long id, Double percentage, String description) {
        this.id = id;
        this.percentage = percentage;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Double getPercentage() {
        return percentage;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
