package cz.upce.fei.bdast.gui.alerty;

import javafx.scene.control.Alert;

/**
 * Tato třída slouží k vytváření chybových výzev (chybových oken)
 */
public final class ErrorAlert extends Alert {

    /**
     * Konstanta reprezentující instance rozhraní {@link ErrorLogKonzument}, která je
     * inicializována anonymní funkcí
     */
    private static final ErrorLogKonzument<String> errorLog = t -> {
        final Alert chyboveOkenko = new ErrorAlert(t);
        chyboveOkenko.showAndWait();
    };

    /**
     * Tyto soukromé a neměnné konstanty určují titulek a záhlaví chybového okna
     */
    private final String TITULEK = "Titulek";
    private final String ZAHLAVI = "Zahlavi";

    /**
     * Konstruktor přijímá zprávu o chybě jako parametr a vytváří nový chybový alert
     * {@link AlertType#ERROR} s nastaveným titulkem, záhlavím a obsahem na základě
     * předdefinovaných konstant a zprávy o chybě
     *
     * @param zprava zpráva o chybě
     */
    public ErrorAlert(String zprava) {
        super(AlertType.ERROR);

        this.setTitle(TITULEK);
        this.setHeaderText(ZAHLAVI);
        this.setContentText(zprava);
    }

    /**
     * Veřejná statická metoda
     * <p>
     * Umožňuje zaznamenat chybu do logu
     *
     * @param zprava zprávu o chybě
     */
    public static void nahlasErrorLog(String zprava) { errorLog.akceptovat(zprava); }
}
