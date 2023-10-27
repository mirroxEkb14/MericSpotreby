package cz.upce.fei.bdast.gui.dialogy;

import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.komponenty.KomponentDenSpotreba;
import cz.upce.fei.bdast.gui.komponenty.KomponentMaxSpotreba;
import cz.upce.fei.bdast.gui.komponenty.KomponentPrumerSpotreba;
import cz.upce.fei.bdast.gui.komponenty.PolozkaKomponenty;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Tato třída je rozšířením třídy {@link Dialog} a implementuje rozhraní {@link DialogovyKomponent}.
 * Slouží k vytvoření dialogového okna pro vkládání spotřeby a je konkrétní implementací dialogu pro
 * různé typy spotřeby
 */
public final class DialogSpotreba extends Dialog<ButtonType>
        implements DialogovyKomponent {

    /**
     * Statický atribut pro pozdější získání zvenčí
     */
    private static PolozkaKomponenty dialogovyKomponent;

    /**
     * Konstruktor nastaví titulek dialogového okna na hodnotu z konstanty, pak nastaví tlačítka
     * dialogového okna pomocí výchozí metody z rozhraní
     *
     * @param typSpotreby parametr reprezentuje  zadaný typ spotřeby
     */
    public DialogSpotreba(TypSpotreby typSpotreby) {
        this.setTitle(Titulek.DIALOG_SPOTREBA.getNadpis());
        this.setDialogPane(this.dejNastavenaTlacitkaDialogu(this.getDialogPane()));
        nastavDialogSpotreba(typSpotreby);
    }

    /**
     * Volí konkrétní dialogový komponent na základě zadaného typu spotřeby {@code typSpotreby}.
     * Pak se přiřazuje do statického atributu {@code dialogovyKomponent} - obsah dialogového
     * okna se nastavuje na tento dialogový komponent
     *
     * @param typSpotreby konkrétní typ spotřeby
     */
    private void nastavDialogSpotreba(TypSpotreby typSpotreby) {
        switch (typSpotreby) {
            case MAX -> dialogovyKomponent = new KomponentMaxSpotreba();
            case DEN -> dialogovyKomponent = new KomponentDenSpotreba();
            case PRUMER -> dialogovyKomponent = new KomponentPrumerSpotreba();
        }
        this.getDialogPane().setContent((Node) dialogovyKomponent);
    }

// <editor-fold defaultstate="collapsed" desc="Gettery">
    public static PolozkaKomponenty getDialogovyKomponent() { return dialogovyKomponent; }
// </editor-fold>
}
