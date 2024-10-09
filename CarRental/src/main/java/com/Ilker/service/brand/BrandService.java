package com.Ilker.service.brand;

import com.Ilker.entitiy.Brand;
import com.Ilker.request.AddBrandRequest;
import com.Ilker.request.UpdateBrandRequest;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand add(AddBrandRequest request);
    Brand update(UpdateBrandRequest request, Long id);
    void delete(Long id);
    void checkIsNotExistsByBrandName(String name);
    void checkIsExistsByBrandId(Long id);
}
