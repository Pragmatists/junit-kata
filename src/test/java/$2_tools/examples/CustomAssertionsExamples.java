
package $2_tools.examples;

import $2_tools.examples.movie.Movie;
import org.assertj.core.api.Condition;
import org.assertj.core.api.StrictAssertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static $2_tools.examples.Race.HOBBIT;
import static $2_tools.examples.Race.MAN;
import static org.assertj.core.api.Assertions.assertThat;


public class CustomAssertionsExamples extends AbstractAssertionsExamples {

    @Test
    public void custom_assertions_exercise() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        assertThat(frodo.age).isEqualTo(33);
        assertThat(frodo.getName()).isEqualTo("Frodo");

//        assertThat(frodo).hasAge(33).hasName("Frodo");
    }

    @Test
    public void frodo_matches_hobbit_condition_exercise() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        TolkienCharacter sauron = new TolkienCharacter("Sauron", 33, MAN);
        List<TolkienCharacter> tolkienCharacters = Arrays.asList(frodo, sauron);

        assertThat(frodo).is(aHobbit());
        assertThat(sauron).isNot(aHobbit());

        assertThat(tolkienCharacters).filteredOn(aHobbit()).containsOnly(frodo);
    }

    private Condition<? super TolkienCharacter> aHobbit() {
        return new Condition<TolkienCharacter>() {

        };
    }

}
