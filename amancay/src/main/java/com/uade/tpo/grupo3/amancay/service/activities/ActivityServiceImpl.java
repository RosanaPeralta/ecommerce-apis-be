package com.uade.tpo.grupo3.amancay.service.activities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityResponse;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Page<Activity> getActivities(PageRequest pageable) {
        return activityRepository.findAll(pageable); //agregar excepción
    }

    public Optional<Activity> getActivityById(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NotFoundException("No se encontró la actividad con ID " + activityId))
        
        return activity;
    }

    public Activity createActivity(String name, String description) {
        return activityRepository.save(new Activity(name, description)); //agregar excepción si falla la creación
    }

    public void deleteActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NotFoundException("No se encontró la actividad con ID " + activityId));

        activityRepository.deleteById(activityId);
    }

    public ActivityResponse updateActivity(ActivityRequest activityRequest) {
        Activity activity = activityRepository.findById(activityRequest.getId()).orElseThrow(() -> new NotFoundException("No se encontró la actividad con ID " + activityRequest.getId()));

        activity.setName(activityRequest.getName());
        activity.setDescription(activityRequest.getDescription());

        Activity updatedActivity = activityRepository.saveAndFlush(activity);

        return new ActivityResponse(updatedActivity.getId());
    }
    
}
