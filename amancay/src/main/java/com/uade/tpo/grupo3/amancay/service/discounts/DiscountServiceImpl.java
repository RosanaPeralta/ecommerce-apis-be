package com.uade.tpo.grupo3.amancay.service.discounts;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.entity.Discount;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.discounts.DiscountResponse;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.DiscountRepository;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public Page<Discount> getDiscounts(PageRequest pageable) {
        return discountRepository.findAll(pageable);
    }

    @Override
    public Optional<Discount> getDiscountById(Long discountId) {
        return discountRepository.findById(discountId);
    }

    @Override
    public Discount createDiscount(Double percentage, String description) throws InvalidParameterException {
        validatePercentage(percentage);
        Discount d = new Discount();
        d.setPercentage(percentage);
        d.setDescription(description);
        return discountRepository.save(d);
    }

    @Override
    public void deleteDiscount(Long discountId) {
        discountRepository.deleteById(discountId);
    }

    @Override
    public DiscountResponse updateDiscount(DiscountRequest req) throws InvalidParameterException {
        Discount discount = discountRepository.findById(req.getId())
                .orElseThrow(() -> new NotFoundException("Fallo la actualizacion"));

        if (req.getPercentage() != null) {
            validatePercentage(req.getPercentage());
            discount.setPercentage(req.getPercentage());
        }
        if (req.getDescription() != null) {
            discount.setDescription(req.getDescription());
        }

        Discount upd = discountRepository.saveAndFlush(discount);
        return new DiscountResponse(upd.getId());
    }

    @Override
    public List<Discount> searchDiscounts(String description) {
        // Usa tu método parcial del repositorio
        return discountRepository.findByDescriptionPartial(description);
    }

    private void validatePercentage(Double percentage) {
        if (percentage == null || percentage < 0.0 || percentage > 100.0) {
            throw new InvalidParameterException("El porcentaje debe estar entre 0 y 100");
        }
    }
}
