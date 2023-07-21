package br.com.mazzatech.infrastructure.adapter.output.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class CdnCepResponseEntity {

    private String code;
    private String address;
    private String district;
    private String city;
    private String state;
    private Long status;
    private String ok;
    private String statusText;

}
