package com.FetchAssessment.ReceiptProcessor.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Item {
    @NotBlank(message = "Short description is required.")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid short description format.")
    private String shortDescription;


    @NotNull(message = "Price is required.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format. Use two decimal places.")
    private String price;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
