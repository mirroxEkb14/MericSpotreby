package cz.upce.fei.bdast.gui.dialogy;

import cz.upce.fei.bdast.data.vycty.TypSenzoru;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeniElektriky;
import cz.upce.fei.bdast.gui.komponenty.KomponentVlozeniVody;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

import java.util.Collection;

/**
 * Třída {@link Dialog} slouží k vytváření dialogových oken a interaktivních dialogů
 * v uživatelském rozhraní. Tato třída umožňuje snadno vytvářet dialogy pro
 * zobrazování zpráv, vstupu dat od uživatele a dalších interakcí. Dialogy jsou často
 * používány pro zobrazování důležitých zpráv, varování nebo pro vyžádání vstupu od
 * uživatele. {@link Dialog} a jeho odvozené třídy jsou užitečné pro komunikaci s
 * uživateli v aplikacích a pro získávání potřebných informací od uživatele v
 * interaktivní formě
 * <p>
 * Třída {@link ButtonType} reprezentuje různé typy tlačítek, které mohou být použity
 * v dialogových oknech a dalších uživatelských rozhraních.  je výčtový typ (enum),
 * což znamená, že definuje konkrétní hodnoty, které mohou být použity pro různé typy
 * tlačítek
 */
public final class DialogVlozeni extends Dialog<ButtonType> {

//    private final TypSenzoruKonzument<TypSenzoru> typKonzument = t -> {
//        switch (t) {
//            case ELEKTRIKA ->;
//            case VODA -> ;
//        }
//    };

    public DialogVlozeni(int idSenzoru, TypSenzoru typSenzoru) {
        this.setTitle(Titulek.DIALOG_VLOZENI.getNadpis());
        nastavTlacitkaDialogu();
        nastavDialogVlozeni(idSenzoru, typSenzoru);
    }

    private void nastavDialogVlozeni(int idSenzoru, TypSenzoru typSenzoru) {
        switch (typSenzoru) {
            case ELEKTRIKA -> this.getDialogPane().setContent(new KomponentVlozeniElektriky(idSenzoru));
            case VODA -> this.getDialogPane().setContent(new KomponentVlozeniVody(idSenzoru));
        }
    }

    /**
     * Privátní pomocní metoda
     * <p>
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
    private void nastavTlacitkaDialogu() {
        final ButtonType okTlacitko = new ButtonType(
                Titulek.TLACITKO_FAJN.getNadpis(), ButtonBar.ButtonData.OK_DONE);
        final ButtonType cancelTlacitko = new ButtonType(
                Titulek.TLACITKO_ZRUSIT.getNadpis(), ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(okTlacitko, cancelTlacitko);
    }
}
