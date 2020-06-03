package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftnxml.vehiclemanagement.model.Image;
import com.ftnxml.vehiclemanagement.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImage(Long id) {
        try {
            Image b = imageRepository.findById(id).get();
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean removeImage(Long id) {
        try {
            Image b = imageRepository.findById(id).get();
            imageRepository.delete(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addImage(Image newImage) {
        if (getImage(newImage.getId()) != null) {
            return false;
        }

        imageRepository.save(newImage);
        return true;
    }

}
