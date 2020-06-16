package com.ftnxml.vehiclemanagement.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    boolean store(MultipartFile image, Long vehicleId);

    Resource loadResource(String imageName, Long vehicleId);

    Stream<Path> loadPaths(Long vehicleId);

}
