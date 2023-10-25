package cz.upce.fei.bdast.gui.koreny;

import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.gui.komponenty.*;
import javafx.scene.layout.VBox;

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
     * Konstruktor zajistí inicializaci hodnot privátních instančních proměnných a
     * provede postupné vytvoření kontejnerů pro uživatelskou navigaci
     */
    public PrikazPanel() {
        nastavPrikazPanel();
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Nastaví veškeré grafické komponenty obsahující tlačítka pro
     * ovládání panelem se seznamem
     */
    private void nastavPrikazPanel() {
        this.getChildren().add(KomponentVlozeni.getInstance());
        this.getChildren().add(KomponentZpristupnovani.getInstance());
        this.getChildren().add(KomponentOdebrani.getInstance());
        this.getChildren().add(KomponentPrikazu.getInstance());
        this.getChildren().add(KomponentSpotreba.getInstance());
        this.getChildren().add(KomponentSouboru.getInstance());
    }
}
