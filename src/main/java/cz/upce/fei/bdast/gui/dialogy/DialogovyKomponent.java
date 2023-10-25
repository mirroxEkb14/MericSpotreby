package cz.upce.fei.bdast.gui.dialogy;

import cz.upce.fei.bdast.gui.Titulek;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.util.Collection;

/**
 * Rozhraní definuje jednu výchozí metodu, sloužící k nastavení tlačítek v dialogovém panelu
 * {@link DialogPane}. Je obecným typem pro všechny typy spotřeby z výčtového typu {@link TypSpotreby}
 */
public interface DialogovyKomponent {

    /**
     * Nastaví konfiguraci tlačítek dialogového okna ({@link Dialog}) a přidá tlačítka, jako
     * jsou tlačítka "OK" a "Storno" do dialogového okna:
     * <ul>
     * <li> <b>Dialog&lt;ButtonType&gt;</b>: instance dialogového okna, do kterého bude tlačítka
     * přidáno
     * <li> {@link Dialog#getDialogPane()}: získá odkaz na panel dialogového okna, což je vlastně
     * část dialogu, kde jsou zobrazovány obsah a tlačítka
     * <li> {@link DialogPane#getButtonTypes()} získá seznam dostupných typů tlačítek pro dialogové
     * okno. V tomto případě získá seznam, do kterého bude přidány další typy tlačítek
     * <li> {@link ObservableList#addAll(Collection)} přidá nová tlačítka do seznamu tlačítek
     * dialogového okna. V tomto příkladu jsou přidána dvě tlačítka: "OK" a "Storno," reprezentovaná
     * objekty {@code okTlacitko} a {@code cancelTlacitko}. Tato tlačítka budou k dispozici uživateli,
     * aby mohl vybrat odpovídající akci
     * </ul>
     */
    default DialogPane dejNastavenaTlacitkaDialogu(DialogPane dialogovyPanel) {
        final ButtonType okTlacitko = new ButtonType(
                Titulek.TLACITKO_FAJN.getNadpis(), ButtonBar.ButtonData.OK_DONE);
        final ButtonType cancelTlacitko = new ButtonType(
                Titulek.TLACITKO_ZRUSIT.getNadpis(), ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogovyPanel.getButtonTypes().addAll(okTlacitko, cancelTlacitko);
        return dialogovyPanel;
    }
}
