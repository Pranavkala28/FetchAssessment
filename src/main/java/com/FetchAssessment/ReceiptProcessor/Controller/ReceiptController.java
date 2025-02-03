package com.FetchAssessment.ReceiptProcessor.Controller;

import com.FetchAssessment.ReceiptProcessor.Model.Receipt;
import com.FetchAssessment.ReceiptProcessor.Service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;


    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processReceipt(@Valid @RequestBody Receipt receipt){
        String receiptId = receiptService.processReceipt(receipt);
        return ResponseEntity.ok(Map.of("id",receiptId));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String, Integer>> getPoints(@PathVariable("id") String id){
        int points = receiptService.getReceiptPoints(id);
        return ResponseEntity.ok(Map.of("points",points));
    }
}
