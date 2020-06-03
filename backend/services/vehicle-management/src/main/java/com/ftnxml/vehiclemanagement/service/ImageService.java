package com.ftnxml.vehiclemanagement.service;

import java.util.List;

import com.ftnxml.vehiclemanagement.model.Image;

public interface ImageService {
    List<Image> getAllImages();

    Image getImage(Long id);

    boolean removeImage(Long id);

    boolean addImage(Image image);
}
