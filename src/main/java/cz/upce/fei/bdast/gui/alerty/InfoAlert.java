package cz.upce.fei.bdast.gui.alerty;

import javafx.scene.control.Alert;

/**
 * Tato třída slouží k zobrazení informačního dialogového okna (alertu) v  grafickém
 * uživatelském rozhraní
 */
public final class InfoAlert extends Alert {

    /**
     * Definice anonymní třídy, která je typem funkce přijímající řetězec a
     * zobrazuje dialogové okno {@link InfoAlert} s tímto řetězcem
     */
    private static final AlertKonzument<String> infoLog = t -> {
        final Alert infoOkenko = new InfoAlert(t);
        infoOkenko.showAndWait();
    };

    /**
     * Konstanty pro název dialogového okna (alertu) s informacemi a pro nadpis
     * dialogového okna, který se zobrazuje nad samotnou zprávou
     */
    private final String TITULEK = "Informační Alert";
    private final String ZAHLAVI = "Aktuální Prvek";

    /**
     * Konstruktor inicializuje dialogové okno typu {@link AlertType#INFORMATION} (informační
     * alert s ikonou informace), nastavuje titulek a nadpis dialogového okna na předem
     * definované konstanty
     *
     * @param zprava text, který bude zobrazen v těle dialogového okna (alertu)
     */
    public InfoAlert(String zprava) {
        super(AlertType.INFORMATION);

        this.setTitle(TITULEK);
        this.setHeaderText(ZAHLAVI);
        this.setContentText(zprava);
    }

    /**
     * Slouží k volání funkce {@code infoLog} a tím zobrazuje dialogové okno {@link InfoAlert}
     * s předanou zprávou, čímž umožňuje rychlé zobrazení informačního dialogového okna s
     * určitou zprávou
     *
     * @param zprava informační zpráva
     */
    public static void nahlasInfoLog(String zprava) { infoLog.akceptovat(zprava); }
}
