package com.ftnxml.vehiclemanagement.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ftnxml.vehiclemanagement.service.ImageService;
import com.ftnxml.vehiclemanagement.service.VehicleService;

@RestController
@RequestMapping("{vehicleId}/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    VehicleService vehicleService;

    @GetMapping(value = "/{imageName}")
    public ResponseEntity getImage(@PathVariable("vehicleId") Long vehicleId,
            @PathVariable("imageName") String imageName) {
        Resource image = imageService.loadResource(imageName, vehicleId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFilename() + "\"")
                .body(image);
    }

    @GetMapping()
    public ResponseEntity listUploadedFiles(@PathVariable("vehicleId") Long vehicleId) throws IOException {

        return ResponseEntity.ok(imageService.loadPaths(vehicleId).toArray());
    }
//
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getImage(@PathVariable Long id) {
//        return ResponseEntity.ok(imageService.getImage(id));
//    }
//
//    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity removeImage(@PathVariable Long id) {
//        if (imageService.removeImage(id))
//            return ResponseEntity.ok().build();
//        else
//            return ResponseEntity.notFound().build();
//    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Long vehicleId) {

        if (vehicleService.getVehicle(vehicleId) == null)
            return ResponseEntity.badRequest().build();

        if (imageService.store(file, vehicleId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
