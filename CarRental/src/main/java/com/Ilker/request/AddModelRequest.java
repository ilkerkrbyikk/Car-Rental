package com.Ilker.request;

import com.Ilker.entitiy.Brand;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddModelRequest {

    private Long id;
    private String name;
    private Long brandId;

}
