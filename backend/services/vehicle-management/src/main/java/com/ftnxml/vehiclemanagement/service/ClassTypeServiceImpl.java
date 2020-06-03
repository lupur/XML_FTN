package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.ClassType;
import com.ftnxml.vehiclemanagement.repository.ClassTypeRepository;

@Service
public class ClassTypeServiceImpl implements ClassTypeService {

    @Autowired
    ClassTypeRepository classTypeRepository;

    @Override
    public List<ClassType> getAllClassTypes() {
        return classTypeRepository.findAll();
    }

    @Override
    public ClassType getClassType(Long id) {
        try {
            ClassType b = classTypeRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeClassType(Long id) {
        try {
            ClassType b = classTypeRepository.findById(id).get();
            classTypeRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addClassType(ClassType newClassType) {
        if (getClassType(newClassType.getId()) != null) {
            return false;
        }

        classTypeRepository.save(newClassType);
        return true;
    }

}
