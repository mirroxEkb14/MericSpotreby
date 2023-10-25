package cz.upce.fei.bdast.spravce;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.model.MereniElektrika;
import cz.upce.fei.bdast.data.model.MereniVoda;
import cz.upce.fei.bdast.data.vycty.Pozice;
import cz.upce.fei.bdast.generator.MereniGenerator;
import cz.upce.fei.bdast.kolekce.AbstrDoubleList;
import cz.upce.fei.bdast.kolekce.IAbstrDoubleList;
import cz.upce.fei.bdast.perzistence.MereniPerzistence;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Tento modul umožnuje správu měření z jednotlivých měření od senzorů
 * <p>
 * Je reprezentantem návrhového vzoru "Singleton" (jedináček), zajišťující,
 * že v rámci aplikace existuje pouze jedna unikátní instance této třídy
 *
 * @author amirov 10/4/2023
 */
public final class SpravceMereni implements Ovladani {

    /**
     * Konstanta se používá při ošetřování na hodnotu nula
     */
    private static final int NULTA_HODNOTA = 0;

    private final MereniGenerator generator = new MereniGenerator();
    private final MereniPerzistence perzistence = new MereniPerzistence();

    private static SpravceMereni instance;

    private IAbstrDoubleList<Mereni> seznam;

    /**
     * Tovární metoda (factory method) pro vytvoření a získání instance Singletonu
     *
     * @return vratí existující nebo nově vytvořenou instanci
     */
    public static SpravceMereni getInstance() {
        if (instance == null)
            instance = new SpravceMereni();
        return instance;
    }

    /**
     * Privátní konstruktor, který znemožňuje vytváření nových instancí třídy mimo
     * samotnou třídu
     */
    private SpravceMereni() { nastav(); }

    private void nastav() {
        this.seznam = new AbstrDoubleList<>();
    }

    @Override
    public void vlozMereni(Mereni mereni, Pozice pozice) {
        switch (pozice) {
            case PRVNI -> seznam.vlozPrvni(mereni);
            case POSLEDNI -> seznam.vlozPosledni(mereni);
            case NASLEDNIK -> seznam.vlozNaslednika(mereni);
            case PREDCHUDCE -> seznam.vlozPredchudce(mereni);
        }
    }

    @Override
    public Mereni zpristupniMereni(Pozice pozice) {
        return switch (pozice) {
            case PRVNI -> seznam.zpristupniPrvni();
            case POSLEDNI -> seznam.zpristupniPosledni();
            case NASLEDNIK -> seznam.zpristupniNaslednika();
            case PREDCHUDCE -> seznam.zpristupniPredchudce();
        };
    }

    @Override
    public Mereni odeberMereni(Pozice pozice) {
        return switch (pozice) {
            case PRVNI -> seznam.odeberPrvni();
            case POSLEDNI -> seznam.odeberPosledni();
            case NASLEDNIK -> seznam.odeberNaslednika();
            case PREDCHUDCE -> seznam.odeberPredchudce();
        };
    }

    @Override
    public Iterator<Mereni> iterator() { return seznam.iterator(); }

    /**
     * Popis logiky:
     * <ol>
     * <li> vytvoří nový spojový seznam pro uchovávání nalezených prvků
     * <li> vytvoří iterátor seznamu
     * <li> dokud je v seznamu další prvek, vstoupí do cyklu {@code while}
     * a pak:
     *     <ol>
     *     <li> vytvoří nové měření, reprezentující aktuální prvek seznamu
     *     <li> vytvoří {@code boolean} proměnnou představující, zda daný
     *     prvek seznamu vyhovuje všem argumentům ({@code id} a {@code datum})
     *     <li> pokud je {@code boolean} proměnna {@code true}, tak:
     *         <ol>
     *         <li> vloží daný prvek do dočasného seznamu
     *         </ol>
     *     </ol>
     * <li> vratí dočasný seznam s nalezenými prvky
     * </ol>
     */
    @Override
    public IAbstrDoubleList<Mereni> MereniDen(int idSenzoru, LocalDate datum) {
        final IAbstrDoubleList<Mereni> vsechnaMereni = new AbstrDoubleList<>();
        final Iterator<Mereni> seznamIterator = seznam.iterator();
        while (seznamIterator.hasNext()) {
            final Mereni mereni = seznamIterator.next();
            final boolean jeMereniNalezeno = jeMereni(mereni, idSenzoru, datum);
            if (jeMereniNalezeno) {
                vsechnaMereni.vlozPosledni(mereni);
            }
        }
        return vsechnaMereni;
    }

    /**
     * Privátní pomocná metoda
     * <p>
     * Ošetří, zda je měření, které vstupuje jako argument, vyhovuje
     * požadavkům uživatele:
     * <ol>
     * <li> má stejné id jako je v argumentu {@code idSenzoru}
     * <li> bylo provedeno v ten samý den, který je v argumentu {@code datum}
     * </ol>
     *
     * @param mereni odkaz na měření, které je třeba ošetřit
     * @param idSenzoru id senzoru měření, požadované uživatelem
     * @param datum den tohoto měřené, požadované uživatelem
     *
     * @return vrací {@code true}, pokud měření vyhovuje požadavkům
     * uživatele, v opačném případě {@code false}
     */
    private boolean jeMereni(Mereni mereni,
                             int idSenzoru,
                             LocalDate datum) {
        final int idNalezenehoMereni = mereni.getIdSenzor();
        final LocalDate datumNalezenehoMereni = mereni.getCasMereni().toLocalDate();

        final boolean jsouIdStejne = jsouStejnaCisla(idNalezenehoMereni, idSenzoru);
        return jsouIdStejne && datumNalezenehoMereni.equals(datum);
    }

    /**
     * Popis logiky:
     * <ol>
     * <li> vytvoří proměnnou, která bude uchovávat maximální spotřebu
     * (je také výstupem této metody)
     * <li> vytvoří iterátor seznamu
     * <li> pokud je v seznamu další prvky, vstoupí do cyklu {@code while},
     * pak:
     *     <ol>
     *     <li> vytvoří měření obsahující aktuální prvek seznamu
     *     <li> vytvoří {@code boolean} hodnotu reprezentující, zda toto
     *     měřené splňuje uživatelské požadavky uváděny v argumentech metody
     *     <li> pokud je tato {@code boolean} hodnota {@code true}, tak:
     *         <ol>
     *         <li> pokud je toto měření typu {@link MereniElektrika}, tak:
     *             <ol>
     *             <li> vytvoří {@code double} s VT elektrinou
     *             <li> vytvoří {@code double} s NT elektrinou
     *             <li> vytvoří {@code double} s maximální hodnotou z hodnot
     *             bodů (1) a (2)
     *             <li> nastaví maximální spotřebu na maximum mezi toutéž
     *             hodnotou a hodnotou z bodu (3)
     *             </ol>
     *         <li> pokud je toto měření typu {@link MereniVoda}, tak:
     *             <ol>
     *             <li> vytvoří {@code double} s potřebou vody v m^3
     *             <li> nastaví maximální spotřebu na maximum mezi toutéž
     *             hodnotou a hodnotou z bodu (1)
     *             </ol>
     *         </ol>
     *     </ol>
     * <li> vratí maximální spotřebu
     * </ol>
     */
    @Override
    public double maxSpotreba(int idSenzoru,
                              LocalDateTime datumOd,
                              LocalDateTime datumDo) {
        double maxSpotreba = Double.MIN_VALUE;
        final Iterator<Mereni> seznamIterator = seznam.iterator();
        while (seznamIterator.hasNext()) {
            final Mereni mereni = seznamIterator.next();
            final boolean jeVyhovujiciMereni = jeMereni(mereni, idSenzoru, datumOd, datumDo);
            if (jeVyhovujiciMereni) {
                if (mereni instanceof MereniElektrika mereniElektriky) {
                    double spotrebaVT = mereniElektriky.getSpotrebaVT();
                    double spotrebaNT = mereniElektriky.getSpotrebaNT();

                    final double nejvyssiSpotreba = Math.max(spotrebaVT, spotrebaNT);
                    maxSpotreba = Math.max(maxSpotreba, nejvyssiSpotreba);
                } else if (mereni instanceof MereniVoda mereniVody) {
                    double sportebaM3 = mereniVody.getSpotrebaM3();

                    maxSpotreba = Math.max(maxSpotreba, sportebaM3);
                }
            }
        }
        return maxSpotreba;
    }

    /**
     * Privátní pomocní metoda.
     * <p>
     * Ošetří, zda vstupující měření vyhovuje uživatelským požadavkům:
     * <ol>
     * <li> unikátní identifikátory jsou stejné
     * <li> měření s tímto id existuje v požadovaném intervalu času
     * </ol>
     *
     * @param mereni měření reprezentující aktuální prvek seznamu během interace
     * @param idSenzoru id, zvolené uživetelem
     * @param pocatecneDatum začátek časocého intervalu
     * @param konecneDatum konec intervalu
     *
     * @return vrací {@code true}, pokud měření splňuje požadavky uživatele, v
     * opačném případě {@code false}
     */
    private boolean jeMereni(Mereni mereni,
                             int idSenzoru,
                             LocalDateTime pocatecneDatum,
                             LocalDateTime konecneDatum) {
        final int idNalezenehoMereni = mereni.getIdSenzor();
        final LocalDateTime datumMereni = mereni.getCasMereni();
        final boolean jsouIdStejne = jsouStejnaCisla(idNalezenehoMereni, idSenzoru);
        final boolean jeVIntervalu = jeVRamciIntervalu(datumMereni, pocatecneDatum, konecneDatum);
        return jsouIdStejne && jeVIntervalu;
    }

    /**
     * Privátní pomocná metoda.
     * <p>
     * Ošetří, zda datum konktétního měření je v požadovaném užívatelem intervalu
     *
     * @param datumMereni datum jednotlivého měření
     * @param pocatecneDatum začátek požadovaného časového intervalu
     * @param konecneDatum konec intervalu
     *
     * @return vrací {@code true}, pokud je datum měření v požadovaném intervalu,
     * v opačném případě {@code false}
     */
    private boolean jeVRamciIntervalu(LocalDateTime datumMereni,
                                      LocalDateTime pocatecneDatum,
                                      LocalDateTime konecneDatum) {
        final boolean doData = datumMereni.isBefore(pocatecneDatum);
        final boolean poDatu = datumMereni.isAfter(konecneDatum);

        return !doData && !poDatu;
    }

    /**
     * Popis logiky:
     * <ol>
     * <li> vytvoří {@code double} proměnnou reprezentující celkovou spotřebu
     * <li> vytvoří {@code int} proměnnou reprezentující celkový počet všech
     * měření
     * <li> vytvoří interátor seznamu
     * <li> dokud je v seznamu další prvek, vstoupí do cyklu {@code while}:
     *     <ol>
     *     <li> vytvoří novou proměnnou pro ukládání aktuálního prvku seznamu
     *     <li> vytvoří {@code boolean} proměnnou reprezentující, zda toto
     *     měření vyhovuje požadavkům uživatele
     *     <li> ošetří hodnotu z bodu (2) a pokud je {@code true}, tak:
     *         <ol>
     *         <li> pokud je toto měření typu {@link MereniElektrika}, tak:
     *              <ol>
     *              <li> dostane spotřebu elektřiny v VT
     *              <li> dostane spotřebu elektřiny v NT
     *              <li> připočítá součet těchto hodnot k hodnotě celkové spotřeby
     *              </ol>
     *         <li> pokud je toto měření typu {@link MereniVoda}, tak:
     *              <ol>
     *              <li> dostane spotřebu vody v m^3
     *              <li> připočítá tuto hodnotu k hodnotě celkové spotřeby
     *              </ol>
     *         <li> po {@code if-else} blocích, zvyší čítač všech měření o jedničku
     *         </ol>
     *    </ol>
     * <li> vratí návratovou hodnotu metody {@link SpravceMereni#dejPrumernouSpotrebu(double, int)}
     * </ol>
     */
    @Override
    public double prumerSpotreba(int idSenzoru,
                                 LocalDateTime datumOd,
                                 LocalDateTime datumDo) {
        double celkovaSpotreba = Double.MIN_VALUE;
        int citacMereni = Integer.MIN_VALUE;

        final Iterator<Mereni> seznamIterator = seznam.iterator();
        while (seznamIterator.hasNext()) {
            final Mereni mereni = seznamIterator.next();
            final boolean jeVyhovujiciMereni = jeMereni(mereni, idSenzoru, datumOd, datumDo);
            if (jeVyhovujiciMereni) {
                if (mereni instanceof MereniElektrika mereniElektriky) {
                    double spotrebaVT = mereniElektriky.getSpotrebaVT();
                    double spotrebaNT = mereniElektriky.getSpotrebaNT();

                    celkovaSpotreba += (spotrebaVT + spotrebaNT);
                } else if (mereni instanceof MereniVoda mereniVody) {
                    double sportebaM3 = mereniVody.getSpotrebaM3();

                    celkovaSpotreba += sportebaM3;
                }
                citacMereni++;
            }
        }
        return dejPrumernouSpotrebu(celkovaSpotreba, citacMereni);
    }

    @Override
    public boolean zalohuj() {
        try {
            perzistence.ulozBin(seznam);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean obnov() {
        try {
            perzistence.nactiBin(seznam);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public void generuj(int pocet) { generator.generuj(seznam, pocet); }

    /**
     * Privátní pomocná metoda
     * <p>
     * Spočítá průměrnou spotřeby tím, že pokud cítač měření není nula, tak
     * vydělí hodnotu celkové spotřeby na počet všech měření. Pokud čítač
     * měření je nula, vratí hodnotu nula
     *
     * @param celkovaSpotreba celková spotřeba všech senzorů
     * @param citacMereni počet všech nalezených měření
     *
     * @return vrací hodnotu průměrné spotřeby senzorů
     */
    private double dejPrumernouSpotrebu(double celkovaSpotreba, int citacMereni) {
        return citacMereni > NULTA_HODNOTA ? celkovaSpotreba / citacMereni : NULTA_HODNOTA;
    }

    @Override
    public void zrus() { seznam.zrus(); }

    @Override
    public Stream<Mereni> dejDatovod() { return seznam.stream(); }

    /**
     * Privátní pomocná metoda.
     * <p>
     * Ošetří, zda jsou si dva čisla rovny
     *
     * @param x prřvní celé číslo
     * @param y druhé celé číslo
     *
     * @return vrací {@code true}, pokud jsou si čísla v argumentech rovny,
     * v opačném případě {@code false}
     */
    private boolean jsouStejnaCisla(int x, int y) { return x == y; }
}
