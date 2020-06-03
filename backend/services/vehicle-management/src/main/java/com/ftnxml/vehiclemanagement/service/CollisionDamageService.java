package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.CollisionDamageWaiver;

public interface CollisionDamageService {

    List<CollisionDamageWaiver> getAllCollisionDW();

    CollisionDamageWaiver getCollisionDW(Long id);

    boolean removeCollisionDW(Long id);

    boolean addCollisionDW(CollisionDamageWaiver newCollisionDW);
}
