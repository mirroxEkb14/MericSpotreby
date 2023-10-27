package kolekce;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.model.MereniElektrika;
import cz.upce.fei.bdast.data.model.MereniVoda;
import cz.upce.fei.bdast.data.vycty.Pozice;
import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;
import cz.upce.fei.bdast.spravce.Ovladani;
import cz.upce.fei.bdast.spravce.SpravceMereni;
import org.junit.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * @author amirov 10/17/2023
 */
public class SpravceMereniTest {


    /**
     * Porovnání dvou proměnných typu double nebo float nelze operátorem
     * rovnosti, protože binární reprezentace na první pohled stejných hodnot
     * nemusí stejná. Proto se hodnoty musí porovna výpočtem, zda jejich rozdíl
     * je dostatečně malý.
     */
    private static final double DELTA = Double.MIN_NORMAL;

    /**
     * Sada instancí třídy {@link cz.upce.fei.bdast.data.model.Mereni} pro testování.
     */
    private final MereniElektrika E1 = new MereniElektrika(
            1,
            LocalDateTime.now(),
            0.7,
            8.5);
    private final MereniElektrika E2 = new MereniElektrika(
            2,
            LocalDateTime.now(),
            2.7,
            18);
    private final MereniVoda V1 = new MereniVoda(
            3,
            LocalDateTime.now(),
            .171);
    private final MereniVoda V2 = new MereniVoda(
            4,
            LocalDateTime.now(),
            .171);

    private Ovladani instance;

    public SpravceMereniTest() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() { instance = SpravceMereni.getInstance(); }

    @After
    public void tearDown() { instance = null; }

    @Test
    public void test_01_01_zpristupniMereni() {
        try {
            instance.vlozMereni(E1, Pozice.PRVNI);
            instance.vlozMereni(V1, Pozice.POSLEDNI);
            Mereni[] result = {instance.zpristupniMereni(Pozice.PRVNI),
                    instance.zpristupniMereni(Pozice.POSLEDNI)};
            Mereni[] expected = {E1, V1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_01_02_zpristupniMereni() {
        instance.vlozMereni(E1, Pozice.PRVNI);
        instance.vlozMereni(V1, Pozice.POSLEDNI);
        instance.zpristupniMereni(Pozice.NASLEDNIK);
        fail();
    }

    @Test
    public void test_02_01_odeberMereni() {
        try {
            // [E1, E2, V1, V2]
            instance.vlozMereni(V2, Pozice.PRVNI);
            instance.vlozMereni(V1, Pozice.PRVNI);
            instance.vlozMereni(E2, Pozice.PRVNI);
            instance.vlozMereni(E1, Pozice.PRVNI);
            Mereni[] result = {instance.odeberMereni(Pozice.PRVNI),
                    instance.odeberMereni(Pozice.POSLEDNI),
                    instance.odeberMereni(Pozice.POSLEDNI),
                    instance.odeberMereni(Pozice.PRVNI)};
            Mereni[] expected = {E1, V2, V1, E2};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_03_01_mereniDen() {
        try {
            // [E1, E2, V1, V2]
            instance.vlozMereni(E1, Pozice.POSLEDNI);
            instance.vlozMereni(E2, Pozice.POSLEDNI);
            instance.vlozMereni(V1, Pozice.POSLEDNI);
            instance.vlozMereni(V2, Pozice.POSLEDNI);
            IAbstrDoubleList<Mereni> mereni = instance.mereniDen(1, LocalDate.from(LocalDateTime.now()));
            int result = mereni.velikost();
            int expected = 1;
            assertEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_04_01_maxSpotreba() {
        try {
            // [E1, E2, V1, V2]
            instance.vlozMereni(E1, Pozice.POSLEDNI);
            instance.vlozMereni(E2, Pozice.POSLEDNI);
            instance.vlozMereni(V1, Pozice.POSLEDNI);
            instance.vlozMereni(V2, Pozice.POSLEDNI);
            double result = instance.maxSpotreba(1,
                    LocalDateTime.now().minusDays(1),
                    LocalDateTime.now());
            double expected = 8.5;
            assertEquals(expected, result, DELTA);
        } catch (Exception ex) {
            fail();
        }
    }
}
