package com.Ilker.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperServiceImpl {
//    private final ModelMapper modelMapper;
//
//
//    public ModelMapperServiceImpl(ModelMapper modelMapper) {
//        super();
//        this.modelMapper = modelMapper;
//    }
//
//    @Bean
//    public ModelMapper forDto() {
//        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
//        return modelMapper;
//    }
//
//    @Bean
//    public ModelMapper forRequest() {
//        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
//        return modelMapper;
//    }
}
