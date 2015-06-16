package $5_level_of_abstraction;


import hero.Hero;
import hero.HeroContext;
import hero.HeroRepository;
import hero.HeroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HeroOrganizationTest {

    @Mock
    HeroRepository heroRepository;
    @Mock
    HeroContext heroContext;

    @InjectMocks
    HeroService heroService = new HeroService();

    @Test
    public void heroes_from_my_company_are_friends_of_mine() {
        Hero hero = new Hero().alias("Batman").organization("Marvell");
        when(heroRepository.findByAlias("Batman")).thenReturn(hero);
        when(heroContext.getCurrentHeroOrganization()).thenReturn("Marvell");

        boolean isFriendOfMine = heroService.isFriendOfMine(hero);

        assertThat(isFriendOfMine).isTrue();
    }

}
