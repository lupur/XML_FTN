package com.ftnxml.vehiclemanagement.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private final String rootLocation = "/images/";

    @Override
    public boolean store(MultipartFile image, Long vehicleId) {
        Path location = Paths.get(rootLocation + vehicleId);
        if (!Files.isDirectory(location)) {
            try {
                Files.createDirectories(Paths.get(rootLocation + vehicleId));
            } catch (IOException e) {
                return false;
            }
        }

        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            if (image.isEmpty()) {
                return false;
            }
            if (filename.contains("..")) {
                // This is a security check
                return false;
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, location.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public Resource loadResource(String imageName, Long vehicleId) {
        Path location = Paths.get(rootLocation + vehicleId);
        if (!Files.isDirectory(location)) {
            return null;
        }

        Path file = location.resolve(imageName);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Stream<Path> loadPaths(Long vehicleId) {
        Path location = Paths.get(rootLocation + vehicleId);
        try {
            return Files.walk(location, 1).filter(path -> !path.equals(location)).map(location::relativize);
        } catch (IOException e) {
            return null;
        }
    }

}
