
package $2_tools.examples;

import static $2_tools.examples.Race.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import $2_tools.examples.movie.Movie;

/**
 * Assertions available for all objects.
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class BasicAssertionsExamples extends AbstractAssertionsExamples {


    @Test
    public void simple_assertions_exercise() {
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

        //age is 33
        //name is "Frodo"
        //name is not "Frodon"
    }

    @Test
    public void description_example() {
        // set a bad age to Mr Frodo, just to see how nice is the assertion error message
        frodo.setAge(34);
        // you can specify a test description with as() method or describedAs(), it supports String format args
        assertThat(frodo.age).isEqualTo(33);
//        assertThat(frodo.age).as("check %s's age", frodo.getName()).isEqualTo(33);
    }

    @Test
    public void meaningful_error_example() {
        // but you still can override the error message if you have a better one :
        final String frodon = "Frodob";
        assertThat(frodo.getName()).as("check Frodo's name")
                .overridingErrorMessage("Hey my name is Frodo not %s", frodon).isEqualTo(frodon);
    }

    @Test
    public void assert_object_reference_assertions_exercise() {
        Object jake = new Person("Jake", 43);
        Object sameJake = jake;
        Object jakeClone = new Person("Jake", 43);

//      What will be the results of this assertions?

//        assertThat(jake).isEqualTo(sameJake);
//        assertThat(jake).isEqualTo(jakeClone);
//        assertThat(jake).isSameAs(jakeClone);
    }

    @Test
    public void better_null_assertions_exercise() {
        Object nullObject = null;
        assertThat(nullObject).isEqualTo(null);
        //find better assertion

        Object nonNullObject = new Object();
        assertThat(nonNullObject).isNotEqualTo(null);
        //find better assertion
    }

    @Test
    public void better_instance_of_assertions_exercise() {
        //find better assertions
        assertThat(gandalf.getClass()).isEqualTo(TolkienCharacter.class);
        assertThat(gandalf.getClass()).isNotEqualTo(Movie.class);
    }

    @Test
    public void assertion_error_message_differentiates_expected_and_actual_persons_example() {
        // Assertion error message is built with toString description of involved objects.
        // Sometimes, objects differs but not their toString description, in that case the error message would be
        // confusing because, if toString returns "Jake" for different objects, isEqualTo would return :
        // "expected:<'Jake'> but was:<'Jake'> ... How confusing !
        // In that case, AssertJ is smart enough and differentiates objects description by adding their class and hashCode.
        Person actual = new Person("Jake", 43);
        Person expected = new Person("Jake", 47);

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void basic_assertions_with_lenient_equals_examples() {
        TolkienCharacter mysteriousHobbit = new TolkienCharacter(null, 33, HOBBIT);

        // Frodo is still Frodo ...
        assertThat(frodo).isEqualToComparingFieldByField(frodo);

        TolkienCharacter frodoClone = new TolkienCharacter("Frodo", 33, HOBBIT);

        // Frodo and his clone are equals by comparing fields
        assertThat(frodo).isEqualToComparingFieldByField(frodoClone);

        // ------------------------------------------------------------------------------------
        // Lenient equality when ignoring null fields of other object
        // ------------------------------------------------------------------------------------

        // Frodo is still Frodo ...
        assertThat(frodo).isEqualToIgnoringNullFields(frodo);

        // Null fields in expected object are ignored, the mysteriousHobbit has null name
        assertThat(frodo).isEqualToIgnoringNullFields(mysteriousHobbit);


        // ------------------------------------------------------------------------------------
        // Lenient equality with field by field comparison expect specified fields
        // ------------------------------------------------------------------------------------

        // Except name and age, frodo and sam both are hobbits, so they are lenient equals ignoring name and age
        assertThat(frodo).isEqualToIgnoringGivenFields(sam, "name", "age");

        // But not when just age is ignored

        //assertThat(frodo).isEqualToIgnoringGivenFields(sam, "age");

        // ------------------------------------------------------------------------------------
        // Lenient equality with field by field comparison on given fields only
        // ------------------------------------------------------------------------------------

        // frodo and sam both are hobbits, so they are lenient equals on race
        assertThat(frodo).isEqualToComparingOnlyGivenFields(sam, "race");

        // but not when accepting name and race
        //assertThat(frodo).isEqualToComparingOnlyGivenFields(sam, "name", "race", "age");
    }




}
