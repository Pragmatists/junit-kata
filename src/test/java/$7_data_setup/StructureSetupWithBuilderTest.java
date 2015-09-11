package $7_data_setup;

import data_setup.EpitaphWriter;
import hero.Hero;
import hero.HeroOrigin;
import org.junit.Test;

import java.util.ArrayList;

import static $7_data_setup.HeroBuilder.aHero;
import static org.assertj.core.api.Assertions.assertThat;

public class StructureSetupWithBuilderTest {

    private EpitaphWriter epitaphWriter = new EpitaphWriter();

    @Test
    public void epitaph_for_any_hero() {
        Hero anyHero = aHero().withAlias("batman").fromEngland().build();

        String epitaph = epitaphWriter.epitaphFor(anyHero);

        assertThat(epitaph).isEqualTo("Batman is dead");
    }


}
