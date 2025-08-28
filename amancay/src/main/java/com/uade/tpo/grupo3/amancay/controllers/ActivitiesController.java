package com.uade.tpo.grupo3.amancay.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityResponse;
import com.uade.tpo.grupo3.amancay.exceptions.DuplicateException;
import com.uade.tpo.grupo3.amancay.service.activities.ActivityService;

import java.net.URI;
import java.security.InvalidParameterException;
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
@RequestMapping("activities")
public class ActivitiesController {

    @Autowired
    private ActivityService activityService;

    @GetMapping // GET /activities
    public ResponseEntity<Page<Activity>> getActivities(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
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
    public ResponseEntity<Object> createActivity(@RequestBody ActivityRequest activityRequest)
            throws DuplicateException {
        Activity result = activityService.createActivity(activityRequest.getName(), activityRequest.getDescription());
        return ResponseEntity.created(URI.create("/activities/" + result.getId())).body(result);
    }

   @PutMapping // PUT /activities/{id}
   public ResponseEntity<ActivityResponse> updateActivity(@RequestBody ActivityRequest activityRequest)
            throws InvalidParameterException {
      ActivityResponse result = activityService.updateActivity(activityRequest);
      return ResponseEntity.ok().body(result);
   }

    @DeleteMapping // DELETE /activities/{id}
    public void deleteActivity(@PathVariable Long activityId)
            throws InvalidParameterException {
      activityService.deleteActivity(activityId);
    }
    
}