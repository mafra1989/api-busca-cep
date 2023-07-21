package br.com.mazzatech.infrastructure.adapter.input.rest.mapper;

import org.mapstruct.Mapper;

import br.com.mazzatech.domain.model.Cep;
import br.com.mazzatech.infrastructure.adapter.input.rest.dto.response.CepDtoResponse;

@Mapper(componentModel = "spring")
public interface CepInputMapper {

	CepDtoResponse toResponseDto(Cep domain);

}
