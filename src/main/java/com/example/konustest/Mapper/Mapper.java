package com.example.konustest.Mapper;

public interface Mapper<DTO, ENTITY>{

    DTO entityToDto(ENTITY entity);

    ENTITY dtoToEntity(DTO dto);
}
