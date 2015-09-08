package $8_assertions;

import static com.googlecode.catchexception.CatchException.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.Assert.fail;

import com.googlecode.catchexception.CatchException;
import hero.Hero;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class HeroBeingDeadExceptionsTest {

    @Test
    public void hero_cannot_fight_when_is_dead() throws Exception {
        Hero hero = new Hero().health(0);

        try {
            hero.fight(anyHero());
            //Exception was not thrown
        } catch (Hero.DeadException exception) {
            assertThat(exception).isInstanceOf(Hero.DeadException.class);
        }
    }

    @Ignore
//    @Test(xxx)
    public void hero_cannot_fight_when_is_dead_v2() throws Exception {
        Hero hero = new Hero().health(0);

        hero.fight(anyHero());

        //exception expected
    }

    //googlecode.CatchException
    @Ignore
    @Test
    public void hero_cannot_fight_when_is_dead_v3() throws Exception {
        Hero hero = new Hero().health(0);

        CatchException.catchException(hero).fight(anyHero());

//        assertThat(xxx).isExactlyInstanceOf(Hero.DeadException.class);
    }

    //java 8
    @Ignore
    @Test
    public void hero_cannot_fight_when_is_dead_v4() {
        Hero hero = new Hero().health(0);

        catchThrowable(() -> { hero.fight(anyHero()); });

        //assertThat(xxx).isInstanceOf(Hero.DeadException.class).hasMessageContaining("RIP");
    }


    private Hero anyHero() {
        return new Hero();
    }
}
