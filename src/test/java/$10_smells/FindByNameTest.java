package $10_smells;

import static com.google.common.collect.Lists.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import hero.Hero;
import hero.HeroRepository;
import hero.HeroService;

@RunWith(MockitoJUnitRunner.class)
public class FindByNameTest {

    @Mock
    private HeroRepository heroRepository;
    @InjectMocks
    private HeroService service = new HeroService();

    @Test
    public void exclude_not_matching_by_alias() {
        Hero superman = new Hero().alias("Superman");
        Hero batman = new Hero().alias("Batman");
        when(heroRepository.fetchAll()).thenReturn(newArrayList(superman, batman));

        List<Hero> heroes = service.findByAliasPrefix("S");

        assertThat(heroes).doesNotContain(batman);
    }

}
