package $2_tools.examples.collections;

import com.google.common.collect.Maps;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class FellowshipOfTheRingTest {

    private Fellowship fellows = readFellowshipMembersFromTheBook();

    // Asercje kontekstowe na obiektach pozwalają sprawdzać przynależność do kolekcji
    // TODO: Popraw skład drużyny aby test przeszedł
    @Test
    public void frodo_is_in() {
        assertThat(Frodo()).is(aHobbit()) .isIn(fellows);
        assertThat(Sauron()).isNotIn(fellows);
    }

    private Condition<? super Fellow> aHobbit() {
        return new Condition<Fellow>() {
            @Override
            public boolean matches(Fellow value) {
                return value.getRace().equals("Hobbit");
            }
        };
    }

    // Fest zawiera asercje specyficzne dla kolekcji i jeszcze bardziej specyficzne dla list
    @Test
    public void races_in_fellowship() {
        // dla jednego elfa mamy to samo co w poprzednim teście
        assertThat(fellows.elfs()).hasSize(1).contains(Legolas());
        // .contains() nie uwzględnia kolejności
        assertThat(fellows.men()).contains(Boromir(), Aragorn())
                .doesNotContain(Gandalf())
                .doesNotHaveDuplicates(); // TODO: usuń duplikat w składzie drużyny
        assertThat(fellows.hobbits()).hasSize(4).containsSubsequence(Sam(), fellow().named("Merry").race("Hobbit"),/* TODO: uzupełnij sekwencję */ Pippin());
        assertThat(fellows.hobbits()).containsExactly(Sam(), fellow().named("Merry").race("Hobbit"), /* TODO: uzupełnij */ Pippin(), Frodo());
        // TODO: dodaj jeszcze kilka asercji poznając API festa
    }

    // nie zawsze interesuje nas sprawdzanie wszystkich pól obiektu
    @Test
    public void fellows_have_names() {
        Fellowship fellows = readFellowshipMembersFromTheBook();
        assertThat(fellows).extracting("name").startsWith("Sam");
    }

    // Fest zawiera także asercje specyficzne dla map
    @Test
    public void ring_bearers() {
        Map<Ring, Fellow> ringBearers = createRingBearers();

        assertThat(ringBearers)
                .as("Size")
                .hasSize(3)
                .doesNotContainValue(Sam())
                .doesNotContain(entry(Ring.oneRing, Aragorn()))
                .containsEntry(Ring.nenya, Galadriel())
                .as("nenya")
                .contains(entry(Ring.nenya, Frodo()))

        ;
    }

    private Map<Ring, Fellow> createRingBearers() {
        HashMap<Ring,Fellow> bearers = Maps.newHashMap();
        bearers.put(Ring.oneRing, Frodo());
        bearers.put(Ring.nenya, Galadriel());
        return bearers;
    }

    private Fellow Galadriel() {
        return fellow().named("Galadriel").race("Elf");
    }


    private Fellow Gandalf() {
        return fellow().named("Gandalf").race("Maiar");
    }

    private Fellow Aragorn() {
        return fellow().named("Aragorn").race("Man");
    }

    private Fellow Boromir() {
        return fellow().named("Boromir").race("Man");
    }

    private Fellow Pippin() {
        return fellow().named("Pippin").race("Hobbit");
    }

    private Fellow Sam() {
        return fellow().named("Sam").race("Hobbit");
    }


    private Fellow Legolas() {
        return fellow().named("Legolas").race("Elf");
    }

    private Fellow Frodo() {
        return fellow().named("Frodo").race("Hobbit");
    }


    private Fellow Sauron() {
        return fellow().named("Sauron").race("Ainur");
    }

    private Fellowship readFellowshipMembersFromTheBook() {
        return new Fellowship(
                Sam(),
                fellow().named("Merry").race("Hobbit"),
                Pippin(),
                Gandalf(),
                Aragorn(),
                Legolas(),
                fellow().named("Gimli").race("Dwarf"),
                Boromir(),
                Frodo()
        );
    }

    private FellowBuilder fellow() {
        return new FellowBuilder();
    }

}
