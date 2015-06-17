package $2_tools.examples.collections;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;

public class Fellowship implements Iterable<Fellow> {
    private final List<Fellow> fellows;

    public Fellowship(Fellow... fellows) {
        this.fellows = newArrayList(fellows);
    }

    @Override
    public Iterator<Fellow> iterator() {
        return fellows.iterator();
    }

    public Fellow get(int index) {
        return fellows.get(index);
    }

    public List<Fellow> hobbits() {
        return filterByRace("Hobbit");
    }

    public List<Fellow> men() {
        return filterByRace("Man");
    }

    public List<Fellow> elfs() {
        return filterByRace("Elf");
    }

    private List<Fellow> filterByRace(String race) {
        return fellows.stream().filter(fellow -> fellow.getRace().equals(race)).collect(toList());
    }
}
