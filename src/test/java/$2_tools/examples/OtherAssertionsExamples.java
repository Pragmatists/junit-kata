
package $2_tools.examples;

import static com.google.common.collect.Maps.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.data.MapEntry.entry;

import java.util.Map;

import org.junit.Test;

/**
 * Array assertions examples.
 */
public class OtherAssertionsExamples extends AbstractAssertionsExamples {


    @Test
    public void number_assertions_examples() throws Exception {
        // equals / no equals assertions
        assertThat(sam.age).isEqualTo(38);
        assertThat(frodo.age).isEqualTo(33).isNotEqualTo(sam.age);

        // <= < > >= assertions
        assertThat(sam.age).isGreaterThan(frodo.age).isGreaterThanOrEqualTo(38);
        assertThat(frodo.age).isLessThan(sam.age).isLessThanOrEqualTo(33);
        assertThat(sam.age).isBetween(frodo.age, gimli.age);

        // shortcuts for assertions : > 0, < 0 and == 0
        assertThat(frodo.age - frodo.age).isZero();
        assertThat(frodo.age - sauron.age).isNegative();
        assertThat(gandalf.age - frodo.age).isPositive();

        assertThat(frodo.age - frodo.age).isNotNegative();
        assertThat(frodo.age - frodo.age).isNotPositive();
        assertThat(gandalf.age - frodo.age).isNotNegative();
        assertThat(frodo.age - sauron.age).isNotPositive();
    }


    @Test
    public void map_contains_examples() throws Exception {

        Map<String, TolkienCharacter> characters = newLinkedHashMap();
        characters.put(frodo.getName(), frodo);
        characters.put(galadriel.getName(), galadriel);
        characters.put(gandalf.getName(), gandalf);
        characters.put(sam.getName(), sam);

        assertThat(characters).containsOnly(
                entry(sam.getName(), sam),
                entry(frodo.getName(), frodo),
                entry(gandalf.getName(), gandalf),
                entry(galadriel.getName(), galadriel));

    }

}
