package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.grupo3.amancay.entity.Discount;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountResponse;
import com.uade.tpo.grupo3.amancay.service.discounts.DiscountService;

import java.net.URI;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("discounts")
public class DiscountsController {

    @Autowired
    private DiscountService discountService;

    @GetMapping // GET /discounts
    public ResponseEntity<Page<Discount>> getDiscounts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null) {
            return ResponseEntity.ok(discountService.getDiscounts(PageRequest.of(0, Integer.MAX_VALUE)));
        }
        return ResponseEntity.ok(discountService.getDiscounts(PageRequest.of(page, size)));
    }

    @GetMapping("/{discountId}") // GET /discounts/{discountId}
    public ResponseEntity<Discount> getDiscountById(@PathVariable Long discountId) {
        Optional<Discount> result = discountService.getDiscountById(discountId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Discount>> searchByDescription(@RequestParam String description) {
        List<Discount> list = discountService.searchDiscounts(description);
        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping // POST /discounts
    public ResponseEntity<Discount> createDiscount(@RequestBody DiscountRequest req)
            throws InvalidParameterException {
        Discount result = discountService.createDiscount(req.getPercentage(), req.getDescription());
        return ResponseEntity.created(URI.create("/discounts/" + result.getId())).body(result);
    }

    @PutMapping // PUT /discounts
    public ResponseEntity<DiscountResponse> updateDiscount(@RequestBody DiscountRequest req)
            throws InvalidParameterException {
        DiscountResponse result = discountService.updateDiscount(req);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping // DELETE /discounts
    public void deleteDiscount(@RequestBody DiscountRequest req)
            throws InvalidParameterException {
        discountService.deleteDiscount(req.getId());
    }
}
