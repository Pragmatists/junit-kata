package $7_data_setup;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import data_setup.HeroRowMapper;
import hero.Hero;
import hero.HeroOrigin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TabularDataTest {

    private ResultSet rs = mock(ResultSet.class);
    private HeroRowMapper mapper = new HeroRowMapper();

    @Test
    public void map_hero_with_origin() throws SQLException {
        when(rs.getString(1)).thenReturn("Batman");
        when(rs.getString(2)).thenReturn("Bruce");
        when(rs.getString(3)).thenReturn("Wayne");
        when(rs.getInt(4)).thenReturn(11);
        when(rs.getString(5)).thenReturn("Gotham City");

        Hero hero = mapper.mapRow(rs, 0);

        assertThat(hero.getAlias()).isEqualTo("Batman");
        assertThat(hero.getRealFirstName()).isEqualTo("Bruce");
        assertThat(hero.getRealLastName()).isEqualTo("Wayne");
        assertThat(hero.getOrigin()).isEqualTo(new HeroOrigin(11, "Gotham City"));
    }

    @Test
    public void map_hero_with_no_alias() throws SQLException {
        when(rs.getString(1)).thenReturn(null);
        when(rs.getString(2)).thenReturn("Sherlock");
        when(rs.getString(3)).thenReturn("Holmes");
        when(rs.getInt(4)).thenReturn(12);
        when(rs.getString(5)).thenReturn("London");

        Hero hero = mapper.mapRow(rs, 0);

        assertThat(hero.getAlias()).isNull();
        assertThat(hero.getRealFirstName()).isEqualTo("Sherlock");
        assertThat(hero.getRealLastName()).isEqualTo("Holmes");
        assertThat(hero.getOrigin()).isEqualTo(new HeroOrigin(12, "London"));
    }

}
