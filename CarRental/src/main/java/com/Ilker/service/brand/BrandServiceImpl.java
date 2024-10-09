package com.Ilker.service.brand;

import com.Ilker.entitiy.Brand;
import com.Ilker.exceptions.BrandAlreadyExistsException;
import com.Ilker.exceptions.ResourceNotFoundException;
import com.Ilker.repository.BrandRepository;
import com.Ilker.request.AddBrandRequest;
import com.Ilker.request.UpdateBrandRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;
    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand add(AddBrandRequest request) {
        if(brandRepository.existsByName(request.getBrandName())){
            throw new BrandAlreadyExistsException("Brand is already exist.");
        }
        Brand brand = new Brand();
        brand.setName(request.getBrandName());
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(UpdateBrandRequest request, Long id) {
       if(brandRepository.existsById(id)) {
           Brand brand = brandRepository.getBrandById(id);
           brand.setName(request.getBrandName());
           return brandRepository.save(brand);
       }
       throw new ResourceNotFoundException("Brand not found.");

    }

    @Override
    public void delete(Long id) {
       brandRepository.findById(id).ifPresentOrElse(brandRepository::delete,
               () -> {throw new ResourceNotFoundException("Brand not found.");});
    }

    @Override
    public void checkIsNotExistsByBrandName(String name){
        if(brandRepository.existsByName(name)){
            throw new BrandAlreadyExistsException("Brand already exists.");
        }
    }

    @Override
    public void checkIsExistsByBrandId(Long id){
        if(!brandRepository.existsById(id)){
            throw new ResourceNotFoundException("Brand already exists.");
        }
    }



}
