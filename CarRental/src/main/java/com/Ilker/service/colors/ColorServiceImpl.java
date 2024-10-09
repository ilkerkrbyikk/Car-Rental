package com.Ilker.service.colors;

import com.Ilker.entitiy.Color;
import com.Ilker.exceptions.BrandAlreadyExistsException;
import com.Ilker.exceptions.ColorAlreadyExistsException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.ColorRepository;
import com.Ilker.request.AddColorRequest;
import com.Ilker.request.UpdateColorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color add(AddColorRequest request) {
         checkIsNotExistsByColorName(request.getColorName());
         Color color = new Color();
         color.setName(request.getColorName());

         return colorRepository.save(color);
    }

    @Override
    public Color update(UpdateColorRequest request, Long id) {
        checkIsExistsByColorId(id);
        Color color = colorRepository.getColorById(id);
        color.setName(request.getColorName());

        return colorRepository.save(color);
    }

    @Override
    public void delete(Long colorId) {
        checkIsExistsByColorId(colorId);
        colorRepository.deleteById(colorId);

    }
    public void checkIsNotExistsByColorName(String name){
        if(colorRepository.existsByName(name)){
            throw new ColorAlreadyExistsException("Color already exists.");
        }
    }

    public void checkIsExistsByColorId(Long id){
        if(!colorRepository.existsById(id)){
            throw new ResourceNotFoundException("Color not found.");
        }
    }

}
