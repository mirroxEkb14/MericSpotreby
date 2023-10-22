package cz.upce.fei.bdast.gui.koreny;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeni;
import cz.upce.fei.bdast.gui.komponenty.KomponentZpristupnovani;
import cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel;
import cz.upce.fei.bdast.spravce.SpravceMereni;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;

/**
 * Tato třída, reprezentující {@link VBox}:
 * <ul>
 * <li> představuje jednoduchý layout sloužící k uspořádání prvků ve vertikálním sloupci
 * (tj. odshora dolů)
 * <li> je užitečný pro organizaci prvků uživatelského rozhraní ve sloupcích, kde
 * jednotlivé prvky jsou zobrazeny pod sebou, což umožňuje vytvářet dobře čitelné
 * rozložení prvků
 * <li> může být použita jako kořenový prvek výkonné scény, nebo může být zanořena do
 * jiných kontejnerů a rozvržení pro složitější rozložení
 * </ul>
 * U této implementaci dochází k tomu, že tento kontejner obsahuje tlačítka pro
 * manipulaci se seznamem ({@link SeznamPanel}), nosící prvky typu {@link Mereni}
 */
public final class PrikazPanel extends VBox {

    /**
     * Konstanta vyjadřuje minimální možnou šiřku panelu s tlačítky
     */
    private static final int MIN_SIRKA_PRIKAZ_PANELU = 220;

    /**
     * Reference na již existující {@link SeznamPanel} vytvořený
     * v rámci třídy {@link Okno}, aby byl možným přístup ke seznamu s prvky
     */
    private final SeznamPanel seznamPanel;

    /**
     * Deklarace jednotlivých komponent okna
     */
    private TitulkovyPanel komponentVlozeni;
    private TitulkovyPanel komponentZpristupnovani;

    /**
     * Instance na správu seznamu
     */
    private final SpravceMereni seznamMereni = SpravceMereni.getInstance();

    /**
     * Konstruktor zajistí inicializaci hodnot privátních instančních proměnných a
     * provede postupné vytvoření kontejnerů pro uživatelskou navigaci
     *
     * @param seznamPanel instance na {@link ListView} seznam
     */
    public PrikazPanel(SeznamPanel seznamPanel) {
        this.seznamPanel = seznamPanel;

        nastavPrikazPanel();
        nastavZpristupnovani();
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Nastaví veškeré grafické komponenty obsahující tlačítka pro
     * ovládání panelem se seznamem
     */
    private void nastavPrikazPanel() {
        nastavVlozeni();
    }

    private void nastavVlozeni() {
        komponentVlozeni = new KomponentVlozeni();
        this.getChildren().add(komponentVlozeni);
    }

    private void nastavZpristupnovani() {
        komponentZpristupnovani = new KomponentZpristupnovani();
        this.getChildren().add(komponentZpristupnovani);
    }
}
