package com.Ilker.service.brand_model;

import com.Ilker.entitiy.Model;
import com.Ilker.request.AddModelRequest;
import com.Ilker.request.UpdateModelRequest;

import java.util.List;

public interface ModelService {
    List<Model> getAll();
    void deleteModel(Long id);
    Model add(AddModelRequest request);
    Model update(UpdateModelRequest request, Long id);
    void checkIsExistsByModelId(Long id);
    void checkIsNotExistsByModelName(String name);


}
