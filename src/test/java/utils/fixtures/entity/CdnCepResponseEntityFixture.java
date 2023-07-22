package utils.fixtures.entity;

import br.com.mazzatech.infrastructure.adapter.output.rest.entity.CdnCepResponseEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CdnCepResponseEntityFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        entityValido();
    }

    private void entityValido() {
        Fixture.of(CdnCepResponseEntity.class).addTemplate(VALIDO, new Rule() {{
            add("code", "69078150");
            add("address", "Rua Nova");
            add("district", "Japiim");
            add("city", "Manaus");
            add("state", "AM");
            add("status", 200L);
        }});
    }
}
