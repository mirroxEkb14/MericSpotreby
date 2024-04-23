package cz.upce.fei.bdats.gui.dialogy;

import cz.upce.fei.bdats.gui.Titulek;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 * Tato třída rozšiřuje třídu {@link TextInputDialog}, což je typ dialogu, který umožňuje
 * uživatelovi zada textový vstup. Vytváří a konfiguruje dialog pro generování obsahu
 */
public class DialogGenerator extends TextInputDialog {

    /**
     * Konstruktor inicializuje různé vlastnosti dialogu:
     * <ol>
     * <li> Volá konstruktor nadřazené třídy {@link TextInputDialog} a nastaví nadpis
     * dialogu na hodnotu získanou z výčtového typu {@link Titulek}
     * <li> Volá metodu {@link DialogGenerator#nastavDialog()}
     * </ol>
     */
    public DialogGenerator() {
        super(Titulek.NAPOVEDA_DIALOGU_GENERATORU.getNadpis());
        nastavDialog();
    }

    /**
     * Nastaví různé vlastnosti dialogu, jako je titulek, záhlaví, text obsahu, nezměnitelnost
     * okna a text na tlačítkách {@code OK} a {@code Cancel}
     */
    private void nastavDialog() {
        this.setTitle(Titulek.TITULEK_DIALOGU_GENERATORU.getNadpis());
        this.setHeaderText(Titulek.ZAHLAVI_DIALOGU_GENERATORU.getNadpis());
        this.setContentText(Titulek.KONTEXT_DIALOGU_GENERATORU.getNadpis());
        this.setResizable(false);
        ((Button) this.getDialogPane().lookupButton(ButtonType.OK)).setText(Titulek.TLACITKO_FAJN.getNadpis());
        ((Button) this.getDialogPane().lookupButton(ButtonType.CANCEL)).setText(Titulek.TLACITKO_ZRUSIT.getNadpis());
    }
}
