package $4_contextual_test;

import static com.google.common.collect.Maps.newHashMap;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hero.Hero;

public class ContextualFightTest {

    @Test
    public void superman_wins_during_day_with_equal_power() {
        Hero superman = new Hero().alias("Superman").power(3);
        Hero lexLuthor = new Hero().realLastName("Lex").realLastName("Luthor").power(3);
        Map<String, String> fightContext = newHashMap();
        fightContext.put("timeOfDay", "day");

        boolean supermanWon = superman.fight(lexLuthor, fightContext);

        assertThat(supermanWon).isTrue();
    }

    @Test
    public void superman_loses_during_day_with_equal_power_against_kryptonite() {
        Hero superman = new Hero().alias("Superman").power(3);
        Hero lexLuthor = new Hero().realLastName("Lex").realLastName("Luthor").power(3).weapon("kryptonite");
        Map<String, String> fightContext = newHashMap();
        fightContext.put("timeOfDay", "day");

        boolean supermanWon = superman.fight(lexLuthor, fightContext);

        assertThat(supermanWon).isFalse();
    }

    @Test
    public void superman_loses_during_night_with_equal_power() {
        Hero superman = new Hero().alias("Superman").power(3);
        Hero lexLuthor = new Hero().realLastName("Lex").realLastName("Luthor").power(3);
        Map<String, String> fightContext = newHashMap();
        fightContext.put("timeOfDay", "night");

        boolean supermanWon = superman.fight(lexLuthor, fightContext);

        assertThat(supermanWon).isFalse();
    }
}
