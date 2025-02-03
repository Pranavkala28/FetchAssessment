package com.FetchAssessment.ReceiptProcessor.Service;

import com.FetchAssessment.ReceiptProcessor.Model.Item;
import com.FetchAssessment.ReceiptProcessor.Model.Receipt;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class ReceiptService {
    //Used to store Ids and Receipts
    private final Map<String,Receipt> receiptDatabase = new HashMap<>();

    //Used to store Ids and points
    private final Map<String, Integer> pointsDatabase = new HashMap<>();

    //generates ID for a receipt
    public String processReceipt(Receipt receipt){
        String receiptID = UUID.randomUUID().toString();

        int points = calculatePoints(receipt);

        receiptDatabase.put(receiptID,receipt);
        pointsDatabase.put(receiptID,points);

        return receiptID;
    }

    //Retrieves the points for a receipt
    public int getReceiptPoints(String id){
        if(!pointsDatabase.containsKey(id)){
            throw new IllegalArgumentException("Receipt not found");
        }
        return pointsDatabase.get(id);
    }



    //calculates the points for a receipt
    public int calculatePoints(Receipt receipt){
        int points = 0;
        int itemsCount = receipt.getItems().size();

        points += countAlphanumeric(receipt.getRetailer());
        points += shortDescriptionPoints(receipt);
        points += checkDate(receipt.getPurchaseDate());
        points += checkTime(receipt.getPurchaseTime());

        if(Double.parseDouble(receipt.getTotal()) % 1 == 0){
            points += 50;
        }

        if(Double.parseDouble(receipt.getTotal()) % 0.25 == 0){
            points += 25;
        }

        if(itemsCount > 1 ){
            points += (itemsCount/2) * 5;
        }

        return points;
    }

    //calculates the points based on description length
    public int shortDescriptionPoints(Receipt receipt){
        int count = 0;

        for(Item item : receipt.getItems()){
            String shortDescription = item.getShortDescription().trim();
            int descriptionLength = shortDescription.length();

            if(descriptionLength % 3 == 0){
                Double price = Double.parseDouble(item.getPrice());
                count += (int) Math.ceil(price * 0.2);
            }
        }
        return count;
    }

    //checks if the day is odd
    public int checkDate(String date){
        String[] dateParts = date.split("-");
        int day = Integer.parseInt(dateParts[2]);
        if(day % 2 != 0){
            return 6;
        }
        return 0;
    }

    //checks if the purchase time is between 2PM and 4PM
    public int checkTime(String time){
        String[] timeParts = time.split(":");
        int hh = Integer.parseInt(timeParts[0]);

        if(hh >= 14 && hh <= 16){
            return 10;
        }
        return 0;
    }

    //counts the alphanumeric characters in retailer name
    public int countAlphanumeric(String input){
        int count = 0;
        for(char c : input.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                count++;
            }
        }
        return count;
    }
}
