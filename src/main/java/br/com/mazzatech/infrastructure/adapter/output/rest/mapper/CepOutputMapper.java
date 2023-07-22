package br.com.mazzatech.infrastructure.adapter.output.rest.mapper;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.model.enumerators.PaisEnum;
import br.com.mazzatech.domain.model.enumerators.URIEnum;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.BrasilApiResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.CdnCepResponseEntity;
import br.com.mazzatech.infrastructure.adapter.output.rest.entity.ViaCepResponseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {PaisEnum.class, URIEnum.class})
public interface CepOutputMapper {

    @Mapping(target = "cidade", source = "localidade")
    @Mapping(target = "estado", source = "uf")
    @Mapping(target = "ibgeCidade", source = "ibge")
    @Mapping(target = "pais", expression = "java(PaisEnum.BRASIL.getNome())")
    @Mapping(target = "origem", expression = "java(URIEnum.VIA_CEPAPI.getNome())")
    CepDomain viaCeptoDomain(ViaCepResponseEntity entity);

    @Mapping(target = "logradouro", source = "address")
    @Mapping(target = "bairro", source = "district")
    @Mapping(target = "cidade", source = "city")
    @Mapping(target = "estado", source = "state")
    @Mapping(target = "pais", expression = "java(PaisEnum.BRASIL.getNome())")
    @Mapping(target = "origem", expression = "java(URIEnum.CDN_CEP.getNome())")
    CepDomain cdnCeptoDomain(CdnCepResponseEntity entity);

    @Mapping(target = "logradouro", source = "street")
    @Mapping(target = "bairro", source = "neighborhood")
    @Mapping(target = "cidade", source = "city")
    @Mapping(target = "estado", source = "state")
    @Mapping(target = "pais", expression = "java(PaisEnum.BRASIL.getNome())")
    @Mapping(target = "origem", expression = "java(URIEnum.BRASIL_API.getNome())")
    CepDomain brasilApitoDomain(BrasilApiResponseEntity entity);

}
