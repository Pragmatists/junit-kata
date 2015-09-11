package $7_data_setup;

import hero.Hero;
import hero.HeroOrigin;

import java.util.ArrayList;

class HeroBuilder {

    private String alias = "any";
    private HeroOrigin origin;

    public static HeroBuilder aHero() {
        return new HeroBuilder();
    }

    public Hero build() {
        return new Hero(alias, "any", "any", origin, new ArrayList(), 1, 1, "", 1, "");
    }

    public HeroBuilder withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public HeroBuilder fromEngland() {
        this.origin = HeroOrigin.ENGLAND;
        return this;
    }
}
