package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.Model;

public interface ModelService {
    List<Model> getAllModels();

    Model getModel(Long id);

    boolean removeModel(Long id);

    boolean addModel(Model newModel);
}
