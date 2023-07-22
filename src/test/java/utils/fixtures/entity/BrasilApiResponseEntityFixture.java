package utils.fixtures.entity;

import br.com.mazzatech.infrastructure.adapter.output.rest.entity.BrasilApiResponseEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class BrasilApiResponseEntityFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        entityValido();
    }

    private void entityValido() {
        Fixture.of(BrasilApiResponseEntity.class).addTemplate(VALIDO, new Rule() {{
            add("cep", "69078150");
            add("street", "Rua Nova");
            add("neighborhood", "Japiim");
            add("city", "Manaus");
            add("state", "AM");
            add("service", "correios-alt");
        }});
    }
}
