package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.products.ProductResponse;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;
import com.uade.tpo.grupo3.amancay.service.activities.ActivityService;
import com.uade.tpo.grupo3.amancay.service.Products.ProductsService;

import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("activities")
public class ActivitiesController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProductsService productsService;

    @GetMapping // GET /activities
    public ResponseEntity<Page<Activity>> getActivities(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null || size == null)
            return ResponseEntity.ok(activityService.getActivities(PageRequest.of(0, Integer.MAX_VALUE)));
        return ResponseEntity.ok(activityService.getActivities(PageRequest.of(page, size)));
    }

    @GetMapping("/{activityId}") // GET /activities/{activityId}
    public ResponseEntity<Activity> getActivityById(@PathVariable Long activityId) {
        Optional<Activity> result = activityService.getActivityById(activityId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping // POST /activities
    public ResponseEntity<Object> createActivity(@RequestBody ActivityRequest activityRequest) throws DuplicateException {
        Activity result = activityService.createActivity(activityRequest.getName(), activityRequest.getDescription());
        return ResponseEntity.created(URI.create("/activities/" + result.getId())).body(result);
    }

   @PutMapping // PUT /activities/{id}
   public ResponseEntity<GenericResponse> updateActivity(@PathVariable Long activityId, @RequestBody ActivityRequest activityRequest) throws InvalidParameterException {
      GenericResponse result = activityService.updateActivity(activityId, activityRequest);
      return ResponseEntity.ok().body(result); //revisar esta linea
   }

    @DeleteMapping("/{activityId}") // DELETE /activities/{id}
    public ResponseEntity<GenericResponse> deleteActivity(@PathVariable Long activityId) throws InvalidParameterException {
        GenericResponse result = activityService.deleteActivity(activityId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{activityId}/products")
    public ResponseEntity<Page<ProductResponse>> getProductsByActivity(
            @PathVariable Long activityId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<ProductResponse> productsPage;
        if (page == null || size == null)
            productsPage = productsService.getProductsByActivity(activityId, PageRequest.of(0, Integer.MAX_VALUE))
                    .map(product -> productsService.convertToResponse(product));
        else
            productsPage = productsService.getProductsByActivity(activityId, PageRequest.of(page, size))
                    .map(product -> productsService.convertToResponse(product));
        
        return ResponseEntity.ok(productsPage);
    }
    
}