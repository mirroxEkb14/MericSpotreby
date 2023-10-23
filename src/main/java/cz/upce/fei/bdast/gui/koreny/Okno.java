package cz.upce.fei.bdast.gui.koreny;

import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

/**
 * Kořenový prvek (root) v kontextu třídy {@link Scene} je:
 * <ul>
 * <li> grafickým kontejnerem, který slouží jako rodič pro všechny ostatní grafické
 * prvky nebo uzly zobrazené na scéně
 * <li> základním prvkem hierarchie scény a všechny ostatní vizuální prvky a
 * kontejnery jsou přidávány jako potomci kořene
 * <li> výchozím bodem pro vytváření vizuální struktury scény
 * </ul>
 * U této implementaci dochází k rozdělení okna na dvě velké části:
 * <ol>
 * <li> část vlevo: {@link SeznamPanel}, což je {@link ListView}
 * <li> část vpravo: {@link PrikazPanel}, což je {@link VBox}
 * </ol>
 */
public final class Okno extends HBox {

    /**
     * Atribut, reprezentující levou část okna (panel se seznam prvků)
     */
    private final SeznamPanel seznam;
    /**
     * Atribut, reprezentující pravou část okna (panel se tlačítky)
     */
    private final PrikazPanel panelPrikazu;

    public Okno() {
        seznam = SeznamPanel.getInstance();
        panelPrikazu = new PrikazPanel(seznam);
        nastavOkno();
    }

    private void nastavOkno() {
        this.getChildren().addAll(seznam, panelPrikazu);
    }
}
