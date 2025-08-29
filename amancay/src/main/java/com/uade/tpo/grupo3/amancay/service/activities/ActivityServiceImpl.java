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
        return activityRepository.findAll(pageable); //agregar excepción
    }

    public Optional<Activity> getActivityById(Long activityId) {
        return activityRepository.findById(activityId);
    }

    public Activity createActivity(String name, String description) {
        return activityRepository.save(new Activity(name, description)); //agregar excepción si falla la creación
    }

    public GenericResponse deleteActivity(Long activityId) {
        activityRepository.deleteById(activityId);
        return new GenericResponse(activityId, "Actividad eliminada correctamente");
    }

    public GenericResponse updateActivity(Long activityId, ActivityRequest activityRequest) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NotFoundException("No se encontró la actividad con ID " + activityId));

        activity.setName(activityRequest.getName());
        activity.setDescription(activityRequest.getDescription());

        activityRepository.saveAndFlush(activity);

        return new GenericResponse(activityId, "Se actualizó la actividad con id " + activityId);
    }
    
}