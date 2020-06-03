package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.ClassType;

public interface ClassTypeService {

    List<ClassType> getAllClassTypes();

    ClassType getClassType(Long id);

    boolean removeClassType(Long id);

    boolean addClassType(ClassType newClassType);
}
