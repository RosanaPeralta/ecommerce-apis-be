package com.uade.tpo.grupo3.amancay.service.discounts;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.Discount;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountResponse;

public interface DiscountService {

    Page<Discount> getDiscounts(PageRequest pageRequest);

    Optional<Discount> getDiscountById(Long discountId);

    Discount createDiscount(Double percentage, String description) throws InvalidParameterException;

    void deleteDiscount(Long discountId);

    DiscountResponse updateDiscount(DiscountRequest entity) throws InvalidParameterException;

    List<Discount> searchDiscounts(String description);
}
