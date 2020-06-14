package com.ftnxml.vehiclemanagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftnxml.vehiclemanagement.dto.ModelDto;
import com.ftnxml.vehiclemanagement.dto.NewModelDto;
import com.ftnxml.vehiclemanagement.model.Brand;
import com.ftnxml.vehiclemanagement.model.Model;
import com.ftnxml.vehiclemanagement.service.BrandService;
import com.ftnxml.vehiclemanagement.service.ModelService;

@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    ModelService modelService;

    @Autowired
    BrandService brandService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getModels() {
        List<ModelDto> models = modelService.getAllModels().stream()
                .map(model -> modelMapper.map(model, ModelDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(models);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getModel(@PathVariable Long id) {
        Model m = modelService.getModel(id);
        if (m == null)
            return ResponseEntity.notFound().build();
        ModelDto model = modelMapper.map(m, ModelDto.class);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeModel(@PathVariable Long id) {
        if (modelService.removeModel(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addModel(@RequestBody NewModelDto newModel) {
        if (newModel == null)
            return ResponseEntity.badRequest().build();
        Brand b = brandService.getBrand(newModel.brandId);
        if (b == null)
            return ResponseEntity.notFound().build();

        Model m = new Model(null, newModel.name, b);
        if (modelService.addModel(m))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
