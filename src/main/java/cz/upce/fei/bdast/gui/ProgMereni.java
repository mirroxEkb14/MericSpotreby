package cz.upce.fei.bdast.gui;

import cz.upce.fei.bdast.gui.koreny.Okno;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * Tato třída je uživatelským formulářovým rozhraním, z níž se budou volat
 * jednotlivé operace
 *
 * @author amirov 10/4/2023
 */
public final class ProgMereni extends Application {

    /**
     * Konstanty pro nastavení hlavní scény/okna. Scéna reprezentuje vizuální
     * komponentu, kde je možné zobrazit grafické prvky
     * <p>
     * Skládá se ze dvou části:
     * <ol>
     * <li> {@link ListView}
     * <li> {@link VBox}
     * </ol>
     */
    private static final int SIRKA_SCENY = 800;
    private static final int VYSKA_SCENY = 600;
    private static final boolean MENITELNOST_SCENY = false;
    private static final String TITULEK_SCENY = "Obousměrně Necyklický Zřetězený Lineární Seznam";

    /**
     * Tento atribut je kořenovým uzelem, což je komponenta, která bude
     * zobrazena jako kořenový prvek scény (třída {@link Scene})
     */
    private final HBox root;
    /**
     * Tento atribut je instance třídy {@link Stage}, představující okno, které
     * bude zobrazeno na obrazovce
     */
    private Stage primarniOkno;

    public ProgMereni() { root = new Okno(); }

    @Override
    public void start(Stage stage) {
        primarniOkno = stage;
        nastavPrimarniOkno();
    }

    /**
     * Popis jednotlivých metod pro nastavení okna {@link Stage}:
     * <ol>
     * <li> {@link Stage#setScene(Scene)} přidá scénu do okna, tj. nastaví scénu, která bude
     * zobrazena v okně ({@link Stage}), která určuje, co se bude na okně zobrazovat
     * <li> {@link Stage#setTitle(String)} nastaví titulek (název) okna, což je text, který
     * se zobrazuje v záhlaví okna
     * <li> {@link Stage#setResizable(boolean)} určí, zda bude okno {@link Stage} měnitelné
     * nebo neměnitelné v jeho velikosti uživatelem
     * <li> {@link Stage#show()} zobrazí okno na obrazovce (zobrazí obsah, který byl nastaven
     * pomocí ({@link Stage#setScene(Scene)})
     * </ol>
     */
    private void nastavPrimarniOkno() {
        var scene = new Scene(root, SIRKA_SCENY, VYSKA_SCENY);
        primarniOkno.setScene(scene);
        primarniOkno.setTitle(TITULEK_SCENY);
        primarniOkno.setResizable(MENITELNOST_SCENY);
        primarniOkno.show();
    }

    public static void main(String[] args) { launch(args); }
}
