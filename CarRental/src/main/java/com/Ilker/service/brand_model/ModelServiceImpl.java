package com.Ilker.service.brand_model;

import com.Ilker.entitiy.Brand;
import com.Ilker.entitiy.Model;
import com.Ilker.exceptions.ModelAlreadyExistException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.BrandRepository;
import com.Ilker.repository.ModelRepository;
import com.Ilker.request.AddModelRequest;
import com.Ilker.request.UpdateModelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService{

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public void deleteModel(Long id) {
        modelRepository.findById(id).ifPresentOrElse(modelRepository::delete,
                () -> {throw new ResourceNotFoundException("Model is not exists.");});

    }

    @Override
    public Model add(AddModelRequest request) {
        // Check if model exist.
        if(modelRepository.existsById(request.getId())){
            throw new ModelAlreadyExistException("Model already exists.");
        }
        Model newModel = new Model();
        Brand brand = brandRepository.getBrandById(request.getBrandId());

        newModel.setName(request.getName());
        newModel.setBrand(brand);

        return modelRepository.save(newModel);
    }

    @Override
    public Model update(UpdateModelRequest request, Long id) {
        if(modelRepository.existsById(id)){
            Model model = modelRepository.getModelById(id);
            model.setName(request.getName());
            return modelRepository.save(model);
        }
        throw new ResourceNotFoundException("Model is not exist.");
    }

    @Override
    public void checkIsNotExistsByModelName(String name){
        if(modelRepository.existsByName(name)){
            throw new ModelAlreadyExistException("Model already exists.");
        }
    }

    @Override
    public void checkIsExistsByModelId(Long id){
        if(!modelRepository.existsById(id)){
            throw new ResourceNotFoundException("Brand already exists.");
        }
    }
}
