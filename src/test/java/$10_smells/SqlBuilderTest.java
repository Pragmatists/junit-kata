package $10_smells;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import sql.HeroQueryBuilder;

@RunWith(MockitoJUnitRunner.class)
public class SqlBuilderTest {

    private HeroQueryBuilder builder = new HeroQueryBuilder();

    @Test
    public void select_by_origin_name() {
        String query = builder.createSelectByOriginNameQuery("Gotham City");

        assertThat(query).isEqualTo("SELECT h FROM Hero h WHERE h.origin.name = 'Gotham City'");
    }

}
