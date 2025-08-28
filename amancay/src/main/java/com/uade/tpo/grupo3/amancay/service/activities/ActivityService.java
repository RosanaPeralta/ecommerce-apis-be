package com.uade.tpo.grupo3.amancay.service.activities;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uade.tpo.grupo3.amancay.entity.Activity;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.activities.ActivityResponse;

public interface ActivityService {
    
    public Page<Activity> getActivities(PageRequest pageRequest);

    public Optional<Activity> getActivityById(Long activityId);

    public Activity createActivity(String name, String description);

    public void deleteActivity(Long activityId);

    public ActivityResponse updateActivity(ActivityRequest entity) throws InvalidParameterException;

}