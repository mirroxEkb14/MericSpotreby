package kolekce;

import cz.upce.fei.bdats.kolekce.AbstrDoubleList;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author amirov 10/9/2023
 */
public class AbstrDoubleListTest {

    /**
     * Testovací třída pro ověření implementace třídy {@link cz.upce.fei.bdats.kolekce.AbstrDoubleList}.
     */
    private static class TestClass {
        int cisloInstance;

        public TestClass(int cisloInstance) { this.cisloInstance = cisloInstance; }

        @Override
        public String toString() { return "T" + cisloInstance; }

    }

    /**
     * Sada instancí testovací třídy.
     */
    private final TestClass T1 = new TestClass(1);
    private final TestClass T2 = new TestClass(2);
    private final TestClass T3 = new TestClass(3);
    private final TestClass T4 = new TestClass(4);

    private AbstrDoubleList<TestClass> instance;

    public AbstrDoubleListTest() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() { instance = new AbstrDoubleList<>(); }

    @After
    public void tearDown() { instance = null; }

    @Test
    public void test_01_01_vlozPrvni() {
        try {
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.zpristupniPrvni(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_01_02_vlozPrvni() {
        try {
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.zpristupniPosledni(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniAktualni()};
            TestClass[] expected = {T2, T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_01_03_vlozPrvni() {
        try {
            instance.vlozPrvni(T3);
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.zpristupniPosledni(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniAktualni(),
                    instance.zpristupniNaslednika()};
            TestClass[] expected = {T3, T1, T1, T2};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_01_04_vlozPrvni() {
        try {
            instance.vlozPrvni(T3);
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.zpristupniPrvni(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T1, T2, T3, T3};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_01_05_vlozPrvni() {
        try {
            instance.vlozPrvni(T3);
            instance.vlozPrvni(T2);
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.zpristupniPosledni(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPrvni()};
            TestClass[] expected = {T3, T2, T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_01_06_vlozPrvni() {
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        TestClass[] result = {instance.zpristupniPrvni(),
                instance.zpristupniNaslednika(),
                instance.zpristupniNaslednika()};
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void test_01_07_vlozPrvni() {
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        TestClass[] result = {instance.zpristupniPosledni(),
                instance.zpristupniPredchudce(),
                instance.zpristupniPredchudce()};
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void test_01_08_vlozPrvni() {
        instance.vlozPrvni(T1);
        TestClass[] result = {instance.zpristupniAktualni()};
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void test_01_09_vlozPrvni() {
        instance.vlozPrvni(T1);
        TestClass[] result = {instance.zpristupniPrvni(),
                instance.zpristupniNaslednika()};
        fail();
    }

    @Test
    public void test_01_10_vlozPrvni() {
        instance.vlozPrvni(T4);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        TestClass[] result = {instance.zpristupniPrvni(),
                instance.zpristupniAktualni(),
                instance.zpristupniNaslednika(),
                instance.zpristupniAktualni(),
                instance.zpristupniNaslednika(),
                instance.zpristupniPrvni(),
                instance.zpristupniAktualni(),
                instance.zpristupniNaslednika(),
                instance.zpristupniNaslednika(),
                instance.zpristupniPosledni()};
        TestClass[] expected = {T1, T1, T2, T2, T3, T1, T1, T2, T3, T4};
        assertArrayEquals(expected, result);
    }

    @Test
    public void test_01_11_vlozPrvni() {
        instance.vlozPrvni(T4);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        TestClass[] result = {instance.zpristupniPosledni(),
                instance.zpristupniPredchudce(),
                instance.zpristupniPredchudce(),
                instance.zpristupniPredchudce(),
                instance.zpristupniPosledni(),
                instance.zpristupniPredchudce(),
                instance.zpristupniPredchudce(),
                instance.zpristupniPredchudce()};
        TestClass[] expected = {T4, T3, T2, T1, T4, T3, T2, T1};
        assertArrayEquals(expected, result);
    }

    @Test
    public void test_01_12_vlozPrvni() {
        instance.vlozPrvni(T4);
        instance.vlozPrvni(T3);
        instance.vlozPrvni(T2);
        instance.vlozPrvni(T1);
        TestClass[] result = {instance.zpristupniPrvni(),
                instance.zpristupniNaslednika(),
                instance.zpristupniNaslednika(),
                instance.zpristupniNaslednika(),
                instance.zpristupniPrvni(),
                instance.zpristupniNaslednika(),
                instance.zpristupniNaslednika(),
                instance.zpristupniNaslednika()};
        TestClass[] expected = {T1, T2, T3, T4, T1, T2, T3, T4};
        assertArrayEquals(expected, result);
    }

    @Test
    public void test_02_01_vlozPosledni() {
        try {
            instance.vlozPosledni(T1);
            TestClass[] result = {instance.zpristupniPrvni(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T1, T1};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_02_02_vlozPosledni() {
        try {
            instance.vlozPosledni(T1);
            instance.vlozPosledni(T2);
            instance.vlozPosledni(T3);
            instance.vlozPosledni(T4);
            TestClass[] result = {instance.zpristupniPrvni(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniAktualni(),
                    instance.zpristupniPosledni(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniAktualni(),
                    instance.zpristupniAktualni(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniNaslednika()};
            TestClass[] expected = {T1, T2, T3, T3, T4, T3, T2, T2, T2, T1, T2};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_03_01_vlozNaslednika() {
        try {
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T4);

            instance.zpristupniPrvni();
            instance.vlozNaslednika(T3);
            instance.vlozNaslednika(T2);

            TestClass[] result = {instance.zpristupniNaslednika(),
                    instance.zpristupniNaslednika()};
            TestClass[] expected = {T2, T3};
            assertArrayEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_03_02_vlozNaslednika() {
        instance.vlozNaslednika(T1);
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void test_03_03_vlozNaslednika() {
        instance.vlozPrvni(T1);
        instance.vlozNaslednika(T2);
        fail();
    }

    @Test
    public void test_03_04_vlozNaslednika() {
        try {
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T2);
            instance.zpristupniNaslednika();
            instance.vlozNaslednika(T3);
            TestClass[] result = {instance.zpristupniPrvni(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniAktualni()};
            TestClass[] expected = {T1, T2, T3, T3};
            assertArrayEquals(expected, result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_03_05_vlozNaslednika() {
        try {
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T2);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T1, T2};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_04_01_Zrus() {
        try {
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T4);
            int result = instance.velikost();
            instance.zrus();
            int expected = 2;
            assertEquals(expected, result);
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void test_05_01_vlozPredchudce() {
        try {
            instance.vlozPosledni(T2);
            instance.zpristupniPosledni();
            instance.vlozPredchudce(T1);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniPredchudce()};
            TestClass[] expected = {T2, T1};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_05_02_vlozPredchudce() {
        try {
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T3);
            instance.zpristupniNaslednika();
            instance.vlozPredchudce(T2);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPredchudce()};
            TestClass[] expected = {T3, T2, T1};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_06_01_odeberPrvni() {
        try {
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T2);
            TestClass[] result = {instance.odeberPrvni(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T1, T2, T2};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_06_02_odeberPrvni() {
        try {
            instance.vlozPrvni(T1);
            TestClass result = instance.odeberPrvni();
            TestClass expected = T1;
            assertEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_06_03_odeberPrvni() {
        try {
            instance.vlozPrvni(T1);
            instance.odeberPrvni();
            int result = instance.velikost();
            int expected = 0;
            assertEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_06_04_odeberPrvni() {
        instance.vlozPrvni(T1);
        instance.odeberPrvni();
        instance.zpristupniPrvni();
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void test_06_05_odeberPrvni() {
        instance.vlozPrvni(T1);
        instance.odeberPrvni();
        instance.zpristupniAktualni();
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void test_06_06_odeberPrvni() {
        instance.vlozPrvni(T1);
        instance.odeberPrvni();
        instance.zpristupniPosledni();
        fail();
    }

    @Test
    public void test_06_07_odeberPrvni() {
        try {
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T3);
            instance.vlozNaslednika(T2);
            instance.vlozPosledni(T4);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniNaslednika(),
                    instance.odeberPrvni(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPrvni()};
            TestClass[] expected = {T1, T2, T3, T1, T2, T2};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_07_01_odeberPosledni() {
        try {
            instance.vlozPosledni(T2);
            instance.vlozPrvni(T1);
            TestClass[] result = {instance.odeberPosledni(),
                    instance.zpristupniPosledni(),
                    instance.zpristupniPrvni()};
            TestClass[] expected = {T2, T1, T1};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_07_02_odeberPosledni() {
        try {
            instance.vlozPosledni(T1);
            TestClass result = instance.odeberPosledni();
            TestClass expected = T1;
            assertEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_07_03_odeberPosledni() {
        try {
            instance.vlozPosledni(T4);
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T3);
            instance.zpristupniNaslednika();
            instance.vlozPredchudce(T2);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPredchudce(),
                    instance.odeberPrvni(),
                    instance.zpristupniPosledni(),
                    instance.odeberPosledni(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T3, T2, T1, T1, T4, T4, T2, T3};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_08_01_odeberNaslednika() {
        try {
            // [T1 -> T2 -> T3 -> T4]
            instance.vlozPosledni(T4);
            instance.vlozPrvni(T1);
            instance.zpristupniPosledni();
            instance.vlozPredchudce(T3);
            instance.zpristupniPredchudce();
            instance.vlozPredchudce(T2);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniNaslednika(),
                    instance.odeberNaslednika()};
            TestClass[] expected = {T3, T2, T1, T2, T3};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_08_02_odeberNaslednika() {
        try {
            instance.vlozPosledni(T4);
            instance.vlozPrvni(T1);
            instance.zpristupniPosledni();
            instance.vlozPredchudce(T3);
            instance.zpristupniPredchudce();
            instance.vlozPredchudce(T2);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniNaslednika(),
                    instance.odeberNaslednika(),
                    instance.zpristupniAktualni(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniPredchudce()};
            TestClass[] expected = {T3, T2, T1, T2, T3, T2, T4, T2, T1};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_08_03_odeberNaslednika() {
        try {
            // [T1 -> T2 -> T3]
            instance.vlozPosledni(T3);
            instance.vlozPrvni(T1);
            instance.zpristupniPosledni();
            instance.vlozPredchudce(T2);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.zpristupniPredchudce(),
                    instance.odeberNaslednika(),
                    instance.zpristupniAktualni(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T3, T2, T3, T2, T1, T2};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_08_04_odeberNaslednika() {
        try {
            // [T1 -> T2]
            instance.vlozPrvni(T1);
            instance.vlozPosledni(T2);
            TestClass[] result = {instance.zpristupniPrvni(),
                    instance.odeberNaslednika(),
                    instance.zpristupniAktualni(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniPosledni()};
            TestClass[] expected = {T1, T2, T1, T1, T1};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_08_05_odeberNaslednika() {
        instance.vlozPosledni(T1);
        instance.zpristupniPosledni();
        instance.odeberNaslednika();
        fail();
    }

    @Test
    public void test_09_01_odeberPredchudce() {
        try {
            // [T1 -> T2 -> T3 -> T4]
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T2);
            instance.zpristupniNaslednika();
            instance.vlozNaslednika(T3);
            instance.zpristupniNaslednika();
            instance.vlozNaslednika(T4);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.odeberPredchudce(),
                    instance.zpristupniPredchudce(),
                    instance.zpristupniNaslednika(),
                    instance.zpristupniNaslednika()};
            TestClass[] expected = {T3, T2, T1, T3, T4};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test
    public void test_09_02_odeberPredchudce() {
        try {
            // [T1 -> T2 -> T3]
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T2);
            instance.zpristupniNaslednika();
            instance.vlozNaslednika(T3);
            TestClass[] result = {instance.zpristupniAktualni(),
                    instance.odeberPredchudce(),
                    instance.zpristupniPrvni(),
                    instance.zpristupniNaslednika()};
            TestClass[] expected = {T2, T1, T2, T3};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void test_09_03_odeberPredchudce() {
        instance.vlozPrvni(T1);
        instance.zpristupniPrvni();
        instance.odeberPredchudce();
        fail();
    }

    @Test
    public void test_10_01_odeberAktualni() {
        try {
            // [T1 -> T2 -> T3]
            instance.vlozPrvni(T1);
            instance.zpristupniPrvni();
            instance.vlozNaslednika(T2);
            instance.zpristupniNaslednika();
            instance.vlozNaslednika(T3);
            TestClass[] result = {instance.odeberAktualni(),
                    instance.zpristupniPosledni(),
                    instance.zpristupniPredchudce()};
            TestClass[] expected = {T2, T3, T1};
            assertArrayEquals(expected,result);
        } catch (NullPointerException ex) {
            fail();
        }
    }
}
