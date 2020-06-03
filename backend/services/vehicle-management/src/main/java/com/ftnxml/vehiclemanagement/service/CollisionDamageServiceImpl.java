package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.CollisionDamageWaiver;
import com.ftnxml.vehiclemanagement.repository.CollisionDamageWaiverRepository;

@Service
public class CollisionDamageServiceImpl implements CollisionDamageService {

    @Autowired
    CollisionDamageWaiverRepository collisionDamageRepository;

    @Override
    public List<CollisionDamageWaiver> getAllCollisionDW() {
        return collisionDamageRepository.findAll();
    }

    @Override
    public CollisionDamageWaiver getCollisionDW(Long id) {
        try {
            CollisionDamageWaiver b = collisionDamageRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeCollisionDW(Long id) {
        try {
            CollisionDamageWaiver b = collisionDamageRepository.findById(id).get();
            collisionDamageRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addCollisionDW(CollisionDamageWaiver newCollisionDamage) {
        if (getCollisionDW(newCollisionDamage.getId()) != null) {
            return false;
        }

        collisionDamageRepository.save(newCollisionDamage);
        return true;
    }

}
