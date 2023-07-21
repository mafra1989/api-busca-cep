package br.com.mazzatech.infrastructure.adapter.output.rest.mapper;

import br.com.mazzatech.domain.model.Cep;
import br.com.mazzatech.domain.model.enumerators.PaisEnum;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.BrasilApiResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.CdnCepResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.ViaCepResponseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {PaisEnum.class})
public interface CepOutputMapper {

    @Mapping(target = "cidade", source = "localidade")
    @Mapping(target = "estado", source = "uf")
    @Mapping(target = "ibgeCidade", source = "ibge")
    @Mapping(target = "pais", expression = "java(PaisEnum.BRASIL.getNome())")
    Cep viaCeptoDomain(ViaCepResponseEntity entity);

    @Mapping(target = "logradouro", source = "address")
    @Mapping(target = "bairro", source = "district")
    @Mapping(target = "cidade", source = "city")
    @Mapping(target = "estado", source = "state")
    @Mapping(target = "pais", expression = "java(PaisEnum.BRASIL.getNome())")
    Cep cdnCeptoDomain(CdnCepResponseEntity entity);

    @Mapping(target = "logradouro", source = "street")
    @Mapping(target = "bairro", source = "neighborhood")
    @Mapping(target = "cidade", source = "city")
    @Mapping(target = "estado", source = "state")
    @Mapping(target = "pais", expression = "java(PaisEnum.BRASIL.getNome())")
    Cep brasilApitoDomain(BrasilApiResponseEntity entity);

}
