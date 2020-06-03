package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.Model;
import com.ftnxml.vehiclemanagement.repository.ModelRepository;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public Model getModel(Long id) {
        try {
            Model b = modelRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeModel(Long id) {
        try {
            Model b = modelRepository.findById(id).get();
            modelRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addModel(Model newModel) {
        if (getModel(newModel.getId()) != null) {
            return false;
        }

        modelRepository.save(newModel);
        return true;
    }
}
