package $6_command_query;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import hero.Hero;

@RunWith(MockitoJUnitRunner.class)
public class MjollnirPowerTest {

    private static final double GOLD_DENSITY = 0.0193;

    @Test
    public void get_mjollnir_power() {
        Mjollnir mjollnir = new Mjollnir(30, 50, 100);

        int mjollnirPower = mjollnir.getPower();

        assertThat(mjollnirPower).isEqualTo(111300);
    }

    @Test
    public void power_changed_when_different_head_material_is_used() {
        Mjollnir mjollnir = new Mjollnir(30, 50, 100);

        mjollnir.changeHeadMaterial(GOLD_DENSITY);

        assertThat(mjollnir.getPower()).isEqualTo(272800);
    }

//------BAD
    @Test
    public void should_call_head_weight_when_calculating_mjollnir_power() {
        Mjollnir mjollnir = new Mjollnir().handleLength(100);
        mjollnir.head = mock(HammerHead.class);

        mjollnir.getPower();

        verify(mjollnir.head).getWeight();
    }

    @Test
    public void get_mjollnir_power_using_head_weight() {
        Mjollnir mjollnir = new Mjollnir().handleLength(100);
        mjollnir.head = mock(HammerHead.class);
        when(mjollnir.head.getWeight()).thenReturn(200);

        int mjollnirPower = mjollnir.getPower();

        assertThat(mjollnirPower).isEqualTo(20000);
        verify(mjollnir.head).getWeight();
    }

    @Test
    public void changing_mjollnir_material_affects_head_density() {
        Mjollnir mjollnir = new Mjollnir(30, 50, 100);
        mjollnir.head = mock(HammerHead.class);

        mjollnir.changeHeadMaterial(GOLD_DENSITY);

        verify(mjollnir.head).density(GOLD_DENSITY);
    }

    @Test
    public void changing_mjollnir_material_affects_head_density_lazy_version() {
        Mjollnir mjollnir = new Mjollnir(30, 50, 100);
        mjollnir.head = mock(HammerHead.class);

        mjollnir.changeHeadMaterial(GOLD_DENSITY);

        verify(mjollnir.head).density(anyDouble());
    }

    //COLABORATORS

    @Test
    public void smite_opponent_inflicting_damage_equal_to_power(){
        Mjollnir mjollnir = new Mjollnir(30, 50, 100);
        Hero loki = mock(Hero.class);

        mjollnir.smite(loki);

        verify(loki).inflictDamage(111300);
    }

    @Test
    public void smite_opponent_inflicting_damage_on_oponent(){
        Mjollnir mjollnir = new Mjollnir(30, 50, 100);
        Hero loki = new Hero().health(5);

        mjollnir.smite(loki);

        assertThat(loki.getHealth()).isEqualTo(4);
    }

}

class Mjollnir {

    HammerHead head;
    int handleLength;

    public Mjollnir(int headRadius, int headLength, int handleLength) {
        this.head = new HammerHead(headLength, headRadius);
        this.handleLength = handleLength;
    }

    public Mjollnir() {
    }

    public int getPower() {
        return head.getWeight() * handleLength;
    }

    public Mjollnir handleLength(int handleLength) {
        this.handleLength = handleLength;
        return this;
    }

    public void changeHeadMaterial(double headDensity) {
        head.density(headDensity);
    }

    public void smite(Hero opponent) {
        opponent.inflictDamage(getPower());
    }
}

class HammerHead {

    public static double IRON_DENSITY = 0.007874;

    private int length;
    private int r;
    private double density = IRON_DENSITY;

    HammerHead(int length, int r) {
        this.length = length;
        this.r = r;
    }

    public int getWeight() {
        return (int) (length * r * r * Math.PI * density);
    }

    public void density(double density) {
        this.density = density;
    }
}