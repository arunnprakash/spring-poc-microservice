package com.example.demo.mapper;

import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.example.demo.dto.BaseDTO;
import com.example.demo.model.BaseModel;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

/**
 * @author __ArunPrakash__
 *
 */
@MapperConfig
public interface BaseMapper<D extends BaseDTO, M extends BaseModel> {
	public D toDTO(M model);
	@Mappings({
		@Mapping(target = "id", ignore = true)
	})
	public M toModel(D dto);
	@Mappings({
		@Mapping(target = "id", ignore = true)
	})
	public M updateModel(D dto, @MappingTarget M model);
}