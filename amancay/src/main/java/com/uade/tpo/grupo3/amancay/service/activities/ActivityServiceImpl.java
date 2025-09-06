package com.uade.tpo.grupo3.amancay.service.activities;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Page<Activity> getActivities(PageRequest pageable) {
        return activityRepository.findAll(pageable);
    }

    public Optional<Activity> getActivityById(Long activityId) {
        return activityRepository.findById(activityId);
    }

    public Activity createActivity(String name, String description) {
        return activityRepository.save(new Activity(name, description));
    }

    public GenericResponse deleteActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NotFoundException("Activity not found with ID " + activityId));

        activityRepository.delete(activity);
        
        return new GenericResponse(activityId, "Activity deleted successfully.");
    }

    public GenericResponse updateActivity(Long activityId, ActivityRequest activityRequest) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NotFoundException("Activity with ID " + activityId + " was not found."));

        activity.setName(activityRequest.getName());
        activity.setDescription(activityRequest.getDescription());

        activityRepository.saveAndFlush(activity);

        return new GenericResponse(activityId, "Activity with ID " + activityId + " was updated.");
    }
    
}