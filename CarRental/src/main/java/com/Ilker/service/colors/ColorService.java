package com.Ilker.service.colors;

import com.Ilker.entitiy.Color;
import com.Ilker.request.AddColorRequest;
import com.Ilker.request.UpdateColorRequest;

import java.util.List;

public interface ColorService {

    List<Color> getAll();
    Color add(AddColorRequest request);
    Color update(UpdateColorRequest request, Long id);
    void delete(Long colorId);
}
