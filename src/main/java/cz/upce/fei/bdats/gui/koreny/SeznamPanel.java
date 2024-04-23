package cz.upce.fei.bdats.gui.koreny;

import cz.upce.fei.bdats.data.model.Mereni;
import cz.upce.fei.bdats.data.vycty.Pozice;
import cz.upce.fei.bdats.spravce.SpravceMereni;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.text.Font;
import cz.upce.fei.bdats.gui.Titulek;

import java.util.stream.Stream;

/**
 * Tato třída, reprezentující {@link ListView}:
 * <ul>
 * <li> představuje vizuální komponentu určenou k zobrazení seznamu položek
 * uživatelského rozhraní
 * <li> umožňuje uživateli procházet a vybírat jednotlivé položky ze seznamu
 * pomocí myši nebo klávesnice
 * <li> umožňuje přizpůsobit vzhled a chování položek seznamu pomocí buňkových
 * továren (cell factories), týmž lze dosáhnout zobrazení složitějších prvků
 * než pouze textové řetězce
 * <li> může reagovat na akce uživatele, jako je kliknutí na položku seznamu
 * (tj. podporuje události)
 * </ul>
 * U této implementaci dochází k tomu, že tento seznam obsahuje prvky typu
 * {@link Mereni} a pomocí tlačítek z druhé části okna ({@link PrikazPanel})
 * je možné manipulovat těmito položkami
 * <p>
 * Třída je Singleton
 */
public final class SeznamPanel extends ListView<Mereni> {

    /**
     * Konstanta vyjadřuje minimální možnou šiřku seznamu prvků
     */
    private static final int MIN_SIRKA_SEZNAMU = 660;
    /**
     * Konstanty jsou potřebné pro nastavení této třídy v metodě {@link SeznamPanel#nastavSeznamPanel()}
     */
    private static final String NAZEV_SEZNAM_FONTU = "Monospaced";
    private static final int DIMENZE_SEZNAM_FONTU = 13;
    private static final String PRAZDNY_RETEZEC = "";
    /**
     * Privátní kosntanta pro kontroly na číslo nula. Používá se, aby se vyhnout
     * magickým číslem (magic numbers) v kódu
     */
    private final int NULOVA_HODNOTA = 0;
    /**
     * Privátní kosntanta pro kontroly na číslo jedna. Používá se, aby se vyhnout
     * magickým číslem (magic numbers) v kódu
     */
    private final int JEDNICKA = 1;
    /**
     * Privátní konstanta pro použití hodnoty pro zvětšení čehokoli o jedničku
     */
    private final int HODNOTA_INKREMENTU = 1;
    /**
     * Instance na správu seznamu {@link SpravceMereni}
     */
    private final SpravceMereni seznamMereni = SpravceMereni.getInstance();
    /**
     * Konstanta reprezentuje první pozici v seznamu. Používá se hlavně pri přidávání
     * prvků na začátek seznamu
     *
     * @see SeznamPanel#pridej(Mereni, Pozice)
     */
    private final int INDEX_PRVNIHO_PRVKU = 0;
    /**
     * Veřejná konstanta vyjadřuje číslo zvyšující velikost seznamu o jedničku
     * <p>
     * Používá se hlavně při dostávání id pro nový prvek v seznamu (například, při
     * operaci vložení), který je možné dostat sečtením aktuální velikosti seznamu
     * s touto konstantou, což vlastně dá číslo, které by bylo možné vyjadřit jako
     * id posledního prvku seznamu + jednička
     */
    public final int ZVETSOVAC_SEZNAMU = 1;
    /**
     * Konstanta vyjadřuje číslo zmenšující velikost seznamu o jedničku
     * <p>
     * Používá se hlavně při operací zpřistupňování, když je stisknuto tlačítko {@link Titulek#POSLEDNI}
     */
    private final int ZMENSOVAC_SEZNAMU = 1;
    /**
     * Konstanta slouží pro vložení prvku za prvek, na kterém je aktuální index zaměřený
     * pomocí vzorečku {@code aktualniIndex + 1}
     */
    private final int ZVETSOVAC_NASLEDNIKA = 1;
    /**
     * Konstanta obsahuje hodnotu, která říká, že aktuální prvek není nastaven
     */
    private final int VYCHOZI_HODNOTA_AKTUALNIHO_INDEXU = Integer.MIN_VALUE;
    /**
     * Privátní atribut sloužící k uchování indexu aktuálního vybraného prvku v seznamu.
     * Tento index označuje, na který prvek v seznamu je aktuálně zaměřený. Tím umožňuje
     * sledovat, který prvek v seznamu je aktuálně vybrán, což je zapotřebí pro navigaci
     * a manipulaci s prvky v seznamu
     */
    private int aktualniIndex = VYCHOZI_HODNOTA_AKTUALNIHO_INDEXU;

    private static SeznamPanel instance;

    /**
     * Tovární metoda (factory method) vratí existující nebo nově vytvořenou instanci
     * této třídy
     */
    public static SeznamPanel getInstance() {
        if (instance == null)
            instance = new SeznamPanel();
        return instance;
    }

    private SeznamPanel() { nastavSeznamPanel(); }

    /**
     * Přidá novou instanci třídy {@link Mereni} do seznamu a do vizuálního seznamu
     *
     * @param mereni nově vytvořená instance {@link Mereni}
     */
    public void pridej(Mereni mereni, Pozice pozice) {
        switch (pozice) {
            case PRVNI -> this.getItems().add(INDEX_PRVNIHO_PRVKU, mereni);
            case POSLEDNI -> this.getItems().add(mereni);
            case NASLEDNIK -> pridejNaslednika(mereni);
            case PREDCHUDCE -> pridejPredchudce(mereni);
        }
        seznamMereni.vlozMereni(mereni, pozice);
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Vloží nový prvek za aktuálně vybraný prvek v seznamu
     *
     * @param mereni nový prvek typu {@link Mereni}
     */
    private void pridejNaslednika(Mereni mereni) {
        if (jeIndexPlatny())
            this.getItems().add(aktualniIndex + ZVETSOVAC_NASLEDNIKA, mereni);
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Vloží nový prvek před aktuálně vybraným prvkem v seznamu
     *
     * @param mereni nový prvek typu {@link Mereni}
     */
    private void pridejPredchudce(Mereni mereni) {
        if (jeIndexPlatny()) {
            this.getItems().add(aktualniIndex, mereni);
            zvysAktualniUkazatel();
        }
    }

    /**
     * Nastaví interní ukazatel {@code aktualniIndex} na {@code 0} (první prvek) a zvolí
     * tento prvek v seznamu
     */
    public void posunNaPrvni() {
        aktualniIndex = INDEX_PRVNIHO_PRVKU;
        this.getSelectionModel().select(aktualniIndex);
    }

    /**
     * Nastaví interní ukazatel {@code aktualniIndex} na index posledního prvku v seznamu
     * a vybere tento prvek
     */
    public void posunNaPosledni() {
        aktualniIndex = this.getItems().size() - ZMENSOVAC_SEZNAMU;
        this.getSelectionModel().select(aktualniIndex);
    }

    /**
     * Nastaví interní ukazatel {@code aktualniIndex} na následující prvek v seznamu
     * a vybere tento prvek
     */
    public void posunNaNaslednika() {
        zvysAktualniUkazatel();
        this.getSelectionModel().select(aktualniIndex);
    }

    /**
     * Nastaví interní ukazatel {@code aktualniIndex} na předchozí prvek v seznamu
     * a vybere tento prvek
     */
    public void posunNaPredchudce() {
        snizAktualniUkazatel();
        this.getSelectionModel().select(aktualniIndex);
    }

    public Mereni dejAktualniPrvek() { return this.getItems().get(aktualniIndex); }

    /**
     * Odstraní první prvek ze seznamu a pokud tento prvek byl zároveň aktuální,
     * nastaví tento index an výchozí hodnotu (tj. teď aktuální prvek není nastaven)
     *
     * @return vrací {@code true}, pokud smazaný prvek byl zároveň aktuální , v opačném
     * případě {@code false}
     */
    public boolean smazPrvni() {
        final boolean bylAktualniPrvnim = (aktualniIndex == INDEX_PRVNIHO_PRVKU);
        this.getItems().remove(INDEX_PRVNIHO_PRVKU);
        if (bylAktualniPrvnim) {
            vynulujAktualniUkazatel();
            this.getSelectionModel().clearSelection();
        }
        if (jeNastavenAktualni()) snizAktualniUkazatel();
        return bylAktualniPrvnim;
    }

    /**
     * Provádí odebrání posledního prvku ze seznamu a následně ověří, zda byl tento prvek
     * zároveň aktuálním prvkem
     *
     * @return vrací {@code true}, pokud byl aktuální prvek zároveň posledním, v opačném
     * případě {@code false}
     */
    public boolean smazPosledni() {
        final int posledniIndex = dejVelikost() - ZMENSOVAC_SEZNAMU;
        final boolean bylAktualniPoslednim = (aktualniIndex == posledniIndex);
        this.getItems().remove(posledniIndex);
        if (bylAktualniPoslednim) {
            vynulujAktualniUkazatel();
            this.getSelectionModel().clearSelection();
        }
        if (jeNastavenAktualni()) aktualniIndex = Math.min(aktualniIndex, posledniIndex);
        return bylAktualniPoslednim;
    }

    /**
     * Odebírá prvek, který následuje po aktuálním prvku v seznamu
     */
    public void smazNaslednika() { this.getItems().remove(aktualniIndex + ZVETSOVAC_NASLEDNIKA); }

    /**
     * Odebírá prvek, který předchází aktuálnímu prvku v seznamu
     */
    public void smazPredchudce() {
        snizAktualniUkazatel();
        this.getItems().remove(aktualniIndex);
    }

    /**
     * Smaže aktuální prvek ze seznamu
     */
    public Mereni smazAktualni() {
        final Mereni aktualniMereni = this.getItems().get(aktualniIndex);
        this.getItems().remove(aktualniIndex);
        vynulujAktualniUkazatel();
        this.getSelectionModel().clearSelection();
        return aktualniMereni;
    }

    /**
     * Vyčistí obsah seznamu (všechny položky)
     */
    public void vymaz() {
        this.getItems().clear();
        vynulujAktualniUkazatel();
    }

    /**
     * Veřejná pomocní metoda
     * <p>
     * Zkontroluje, zda je {@code aktualniIndex} v platném rozmezí pro seznam prvků. To znamená, že se
     * ověřuje, zda je aktuální index větší než nebo roven {@code 0} (což značí platný index v seznamu)
     * a zároveň menší než počet prvků v seznamu (což značí, že index nevykazuje mimo rozsah seznamu)
     *
     * @return vrací {@code true}, pokud je index v platném rozmezí, v opačném případě {@code false}
     */
    public boolean jeIndexPlatny() { return aktualniIndex >= NULOVA_HODNOTA && aktualniIndex < getItems().size(); }

    /**
     * Veřejná pomocní metoda
     *
     * @return vrací {@code true}, pokud je aktuální index platný a méně než počet prvků v seznamu - 1
     * (což značí, že nejsme na konci seznamu), v opačném případě {@code false}
     */
    public boolean jeIndexPlatnyProNaslednika() {
        return aktualniIndex >= NULOVA_HODNOTA && aktualniIndex < getItems().size() - ZMENSOVAC_SEZNAMU;
    }

    /**
     * Veřejná pomocní metoda
     *
     * @return vrací {@code true}, pokud aktuální prvek je následníkem posledního prvku v seznamu,
     * a {@code false}, pokud není
     */
    public boolean jeNaslednikPoslednim() { return aktualniIndex == getItems().size() - ZMENSOVAC_SEZNAMU; }

    /**
     * Veřejná pomocní metoda
     *
     * @return vrací {@code true}, pokud aktuální prvek je předchůdcem pvního prvku v seznamu,
     * a {@code false}, pokud není
     */
    public boolean jePredchudcePrvnim() { return aktualniIndex == INDEX_PRVNIHO_PRVKU; }

    /**
     * Veřejná pomocní metoda
     *
     * @return vrací {@code true}, pokud není seznam prázdný a existuje následující prvek
     * za aktuálním prvkem, v opačném případě {@code false}
     */
    public boolean jeNaslednik() { return !jePrazdny() && aktualniIndex < dejVelikost() - ZMENSOVAC_SEZNAMU; }

    /**
     * Veřejná pomocní metoda
     *
     * @return vrací {@code true}, pokud není seznam prázdný a existuje předcházející
     * prvek před aktuálním prvkem, v opačném případě {@code false}
     */
    public boolean jePredchudce() { return !jePrazdny() && aktualniIndex > NULOVA_HODNOTA; }

    public boolean jePrazdny() { return this.getItems().isEmpty(); }

    /**
     * Privátní pomocní metoda
     *
     * @return vrací {@code true}, pokud je aktuální index větší než {@code 0} a méně než počet prvků
     * v seznamu (což značí, že ukazatel není na začátku seznamu), v opačném případě {@code false}
     */
    public boolean jeIndexPlatnyProPredchudce() {
        return aktualniIndex > NULOVA_HODNOTA && aktualniIndex < getItems().size();
    }

    /**
     * Hledá chybějící {@code idSenzor} v seznamu
     * <p>
     * Popis logiky:
     * <ol>
     * <li> Inicializuje proměnnou {@code maxIdSenzoru} s hodnotou {@code 0}, která bude sloužit k
     * určení maximálního {@code idSenzoru} v seznamu
     * <li> Deklaruje pole {@code boolean}, které bude sloužit k označení existujících {@code idSenzorů}
     * <li> První průchod seznamem pro nalezení maximálního {@code idSenzoru}. <b>Cyklus {@code for-each}</b>:
     *      <ol>
     *      <li> Každý prvek je zkoumán, a pokud má vyšší {@code idSenzor} než aktuální {@code maxIdSenzoru},
     *      tak ten {@code maxIdSenzoru} je aktualizován
     *      </ol>
     * <li> Vytvoří pole {@code existujiciIdSenzory} s délkou o jedna vyšší než {@code maxIdSenzoru}, což
     * umožní použít {@code idSenzory} jako indexy v poli
     * <li> Druhý průchod seznamem pro označení existujících {@code}. <b>Cyklus {@code for-each}</b>:
     *      <ol>
     *      <li> Každý prvek je zkoumán, a jeho {@code idSenzor} je použit jako index v poli
     *      {@code existujiciIdSenzory}, kde je nastaven na {@code true}
     *      </ol>
     * <li> Třetí průchod, tentokrát prohledá pole {@code existujiciIdSenzory}, aby našel první chybějící
     * idSenzor. <b>Cyklus {@code for-each}</b>:
     *      <ol>
     *      <li> Pokud narazí na {@code false}, vrátí aktuální {@code i}, což je chybějící {@code idSenzor}
     *      </ol>
     * <li> Vrátí nejbližší {@code idSenzor} za nejvyšším nalezeným {@code idSenzorem} v seznamu, pokud se
     * nepodařilo najít žádný chybějící {@code idSenzor} v předchozím průchodu
     * </ol>
     */
    public int dejUnikatniIdSenzoru() {
        int maxIdSenzoru = NULOVA_HODNOTA;
        boolean[] existujiciIdSenzory;
        for (Mereni mereni : this.getItems()) {
            int idSenzoru = mereni.getIdSenzor();
            if (idSenzoru > maxIdSenzoru) {
                maxIdSenzoru = idSenzoru;
            }
        }
        existujiciIdSenzory = new boolean[maxIdSenzoru + ZVETSOVAC_SEZNAMU];
        for (Mereni mereni : this.getItems()) {
            int idSenzoru = mereni.getIdSenzor();
            existujiciIdSenzory[idSenzoru] = true;
        }
        for (int i = JEDNICKA; i <= maxIdSenzoru; i++) {
            if (!existujiciIdSenzory[i]) {
                return i;
            }
        }
        return maxIdSenzoru + HODNOTA_INKREMENTU;
    }

    /**
     * Aktualizije obsah {@link ListView} na základě dat dodávaných prostřednictvím {@link Stream}
     * z argumentu {@code datovod}:
     * <ol>
     * <li> Vymaže všechny existující položky v seznamu a připraví ho na aktualizaci
     * <li> Iteruje přes prvky v {@code datovod} a přidá každou položku do seznamu
     * </ol>
     *
     * @param datovod nové položky
     */
    public void obnovSeznam(Stream<Mereni> datovod) {
        this.getItems().clear();
        datovod.forEach(this.getItems()::add);
    }

    /**
     * Popis logiky:
     * <ol>
     * <li> Nastaví režim výběru, čímž se omezí možnost výběru na jedinou položku v seznamu,
     * což znamená, že uživatel může vybrat pouze jednu položku najednou:
     *      <ol>
     *      <li> {@link ListView#getSelectionModel()} vrátí odkaz na objekt typu
     *      {@link MultipleSelectionModel}, který spravuje výběr v {@link ListView}
     *      <li> {@link MultipleSelectionModel#setSelectionMode(SelectionMode)}
     *      nastaví režim výběru na {@link SelectionMode#SINGLE}, což znamená, že
     *      uživatel může vybrat pouze jednu položku najednou
     *      </ol>
     * <li> Nastaví minimální šířku pro komponentu (tj. minimální množství horizontálního
     * místa, které {@link ListView} může v rozložení využívat)
     * <li> Nastaví buňkovou továrnu (cell factory), určující, jak budou položky v seznamu
     * zobrazeny. V tomto případě nastaví zobrazení textových popisků (text) pro položky
     * typu {@link Mereni} a také nastaví písmo pro tyto popisky:
     *      <ol>
     *      <li> Vytváří novou buňku {@link ListCell} pro zobrazení položek v seznamu
     *      {@link ListView} pomocí anonymní třídě
     *      <li> Metoda definuje, jakým způsobem se mají aktualizovat buňky (popisky) pro
     *      jednotlivé položky seznamu. Tato metoda se volá automaticky pro každou položku,
     *      když se buňka má aktualizovat
     *      <li> Volá metodu rodičovské třídy (je-li možné provádět potřebné aktualizace)
     *      <li> pokud buňka není prázdná (empty) a zda položka (item) není nulová, tak:
     *              <ol>
     *              <li> nastaví text buňky na řetězec, který vznikne voláním metody
     *              {@code toString()} na objektu typu {@link Mereni} - {@link Mereni#toString()}.
     *              To znamená, že text buňky bude odpovídat textové reprezentaci objektu
     *              {@link Mereni}
     *              <li> nastaví písmo pro text v buňce. V tomto případě je písmo nastaveno
     *              na {@code Monospaced} (pevná šířka) s velikostí 13 bodů
     *              </ol>
     *      <li> pokud buňka je prázdná nebo položka je nulová, tak:
     *              <ol>
     *              <li> nastaví text buňky na prázdný řetězec
     *              </ol>
     *      </ol>
     * </ol>
     */
    private void nastavSeznamPanel() {
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.setMinWidth(MIN_SIRKA_SEZNAMU);
        this.setCellFactory(cell -> new ListCell<>() {
            @Override
            protected void updateItem(Mereni item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty && item != null) {
                    setText(item.toString());
                    setFont(Font.font(NAZEV_SEZNAM_FONTU, DIMENZE_SEZNAM_FONTU));
                } else {
                    setText(PRAZDNY_RETEZEC);
                }
            }
        });
    }

    public int dejVelikost() { return this.getItems().size(); }

    public boolean jeNastavenAktualni() { return aktualniIndex != VYCHOZI_HODNOTA_AKTUALNIHO_INDEXU; }

    /**
     * Privátní pomocní metoda
     * <p>
     * Zvýší aktuální index o {@code 1}
     */
    private void zvysAktualniUkazatel() { aktualniIndex++; }

    /**
     * Privátní pomocní metoda
     * <p>
     * Sníží aktuální index o {@code 1}
     */
    private void snizAktualniUkazatel() { aktualniIndex--; }

    private void vynulujAktualniUkazatel() { aktualniIndex = VYCHOZI_HODNOTA_AKTUALNIHO_INDEXU; }
}
