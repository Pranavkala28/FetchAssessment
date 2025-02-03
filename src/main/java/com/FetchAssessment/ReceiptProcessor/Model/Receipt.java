package com.FetchAssessment.ReceiptProcessor.Model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Receipt {
    @NotBlank(message = "Retailer name is required.")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Invalid retailer name.")
    private String retailer;

    @NotBlank(message = "purchaseDate is required.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Use YYYY-MM-DD.")
    private String purchaseDate;

    @NotBlank(message = "purchaseTime is required.")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Invalid time format. Use HH:mm.")
    private String purchaseTime;

    @NotBlank(message = "items list cannot be null.")
    @Size(min = 1, message = "At least one item is required.")
    private List<Item> items;

    @NotNull(message = "Total amount is required.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format. Use two decimal places.")
    private String total;

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
