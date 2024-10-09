package com.Ilker.request;

import com.Ilker.entitiy.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateModelRequest {

    private String name;
    private String brandId;


}
