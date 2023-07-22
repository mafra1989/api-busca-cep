package utils.fixtures.entity;

import br.com.mazzatech.infrastructure.adapter.output.rest.entity.ViaCepResponseEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class ViaCepResponseEntityFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        entityValido();
    }

    private void entityValido() {
        Fixture.of(ViaCepResponseEntity.class).addTemplate(VALIDO, new Rule() {{
            add("cep", "69078150");
            add("logradouro", "Rua Nova");
            add("bairro", "Japiim");
            add("localidade", "Manaus");
            add("uf", "AM");
            add("ibge", 1302603L);
        }});
    }
}
