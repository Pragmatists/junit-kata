package $8_assertions;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import hero.Hero;
import hero.HeroRepository;
import org.junit.Test;

public class HeroesTest {

    private HeroRepository repository = new HeroRepository();

    @Test
    public void finds_heroes_by_alias() {
        Hero spiderman = repository.save(new Hero().alias("Spiderman"));
        Hero superman = repository.save(new Hero().alias("Superman"));

        List<Hero> heroes = repository.findAllByAlias("Spiderman");

        assertThat(heroes).contains(spiderman).doesNotContain(superman);
    }

    @Test
    public void finds_heroes_by_alias_v2() {
        Hero spiderman = repository.save(namedHero().alias("Spiderman"));
        Hero superman = repository.save(namedHero().alias("Superman"));

        List<Hero> heroes = repository.findAllByAlias("Spiderman");

//        assertThat(heroes).containsOnly(spiderman);
        assertThat(heroes).contains(spiderman).doesNotContain(superman);
    }

    private Hero namedHero() {
        return new Hero() {
            @Override
            public String toString() {
                return getAlias();
            }
        };
    }

    @Test
    public void finds_heroes_by_alias_v3() {
        Hero spiderman = repository.save(new Hero().alias("Spiderman"));
        Hero superman = repository.save(new Hero().alias("Superman"));

        List<Hero> heroes = repository.findAllByAlias("Spiderman");

        assertThat(heroes).overridingErrorMessage("Expecting heroes to contain %s only when searching for 'Spiderman'",
                spiderman.getAlias()).contains(spiderman).doesNotContain(superman);
    }
}
