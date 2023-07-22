package utils.fixtures.domain;

import br.com.mazzatech.domain.model.CepDomain;
import br.com.mazzatech.domain.model.enumerators.PaisEnum;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CepDomainFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        domainValido();
    }

    private void domainValido() {
        Fixture.of(CepDomain.class).addTemplate(VALIDO, new Rule() {{
            add("logradouro", "Rua Nova");
            add("bairro", "Japiim");
            add("cidade", "Manaus");
            add("estado", "AM");
            add("pais", PaisEnum.BRASIL.getNome());
            add("ibgeCidade", 1302603L);
        }});
    }
}
