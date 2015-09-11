package $2_tools.examples;

import $2_tools.examples.custom.TolkienCharacterAssert;
import org.assertj.core.api.Assertions;

public class MyAssertions extends Assertions {


    public static TolkienCharacterAssert assertThat(TolkienCharacter actual) {
        return new TolkienCharacterAssert(actual);
    }


}
