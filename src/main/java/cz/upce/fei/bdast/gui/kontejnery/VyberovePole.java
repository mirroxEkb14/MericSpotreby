package cz.upce.fei.bdast.gui.kontejnery;

import cz.upce.fei.bdast.data.vycty.TypSenzoru;
import javafx.scene.control.ChoiceBox;
import cz.upce.fei.bdast.data.model.MereniElektrika;
import cz.upce.fei.bdast.data.model.MereniVoda;
import cz.upce.fei.bdast.data.vycty.Pozice;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeni;

/**
 * {@link ChoiceBox} je komponentou uživatelského rozhraní, která umožňuje uživatelům vybírat
 * jednu hodnotu z předem definovaného seznamu možností. Je to užitečný
 * prvek pro výběr jedné položky z nabídky
 * <p>
 * U této implementace dochází k vytvoření výběrového pole s dvěma možnostmi: {@link MereniElektrika}
 * a {@link MereniVoda}, protože tato třída se používá pro komponentu vložení ({@link KomponentVlozeni})
 */
public final class VyberovePole extends ChoiceBox<String> {

    /**
     * Konstanta reprezentuje preferovanou šiřku pro všechna výběrová pole -
     * 100 pixelů
     */
    private static final double PREFEROVANA_SIRKA_POLE = 100.0;

    /**
     * Konstruktor přidá možnosti/položky do výběrového seznamu (tj. naplnění seznamem
     * možností, ze kterých může uživatel vybírat). Pak nastaví preferovanou šířku
     * (kolik místa by {@link ChoiceBox} měl zabírat na obrazovce)
     *
     * @param pozicePrvku název pozice prvku v seznamu. U operací vložení to jsou:
     *                    {@link Pozice#PRVNI}, {@link Pozice#POSLEDNI}, {@link Pozice#NASLEDNIK},
     *                    {@link Pozice#PREDCHUDCE}
     */
    public VyberovePole(String pozicePrvku) {
        this.getItems().addAll(
                pozicePrvku, TypSenzoru.ELEKTRIKA.getNazev(), TypSenzoru.VODA.getNazev());
        this.setPrefWidth(PREFEROVANA_SIRKA_POLE);
    }
}
