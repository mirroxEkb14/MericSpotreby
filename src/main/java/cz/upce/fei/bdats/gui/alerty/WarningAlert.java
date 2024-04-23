package cz.upce.fei.bdats.gui.alerty;

import javafx.scene.control.Alert;

/**
 * Tato třída slouží k vytvoření varovných oznámení. Warning Alert je typ oznámení, který slouží k
 * informování uživatele o varování nebo potenciálním problému v aplikaci. Tento druh oznámení má
 * ikonu varování a zobrazuje se, když je potřeba upozornit uživatele na něco důležitého, co by
 * mohlo ovlivnit provoz aplikace
 * <p>
 * Rozšiřuje třídu {@link Alert}, což je základní třída pro dialogová oznámení
 */
public class WarningAlert extends Alert {

    /**
     * Deklarace statického pole {@code varovaciLog}, které je typu {@link AlertKonzument},
     * což je to funkčním rozhraním. Reprezentuje akci (konzument), která má být provedena
     * při vzniku varovného oznámení
     */
    private static final AlertKonzument<String> varovaciLog = t -> {
        final Alert varovaciOkenko = new WarningAlert(t);
        varovaciOkenko.showAndWait();
    };

    /**
     * Konstanty pro titulek a záhlaví (header) varovného oznámení
     */
    private final String TITULEK = "Varovací Alert";
    private final String ZAHLAVI = "Odebraný Aktuální Prvek";

    /**
     * Konstruktor volá konstruktor třídy {@link Alert} s typem {@link AlertType#WARNING} a
     * nastavuje titulek, záhlaví a obsah oznámení podle zadaných konstant
     *
     * @param zprava text, který bude zobrazen v varovném oznámení
     */
    public WarningAlert(String zprava) {
        super(AlertType.WARNING);

        this.setTitle(TITULEK);
        this.setHeaderText(ZAHLAVI);
        this.setContentText(zprava);
    }

    /**
     * Slouží k zobrazení varovného oznámení na základě zprávy. Volá akci (konzument) definovanou
     * v poli {@code varovaciLog} s danou zprávou, čímž je umožněno vytvoření varovného oznámení
     * a jeho zobrazení
     *
     * @param zprava text oznamení
     */
    public static void nahlasVarovaciLog(String zprava) { varovaciLog.akceptovat(zprava); }
}
