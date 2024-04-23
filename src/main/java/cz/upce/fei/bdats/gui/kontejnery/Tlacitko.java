package cz.upce.fei.bdats.gui.kontejnery;

import javafx.scene.control.Button;

/**
 * Třída představuje tlačítko ({@link Button}), které může být použito v uživatelském rozhraní
 * pro spouštění akcí nebo funkcí
 * <p>
 * Je základním ovládacím prvkem (kontrolka), který je určen pro tvorbu
 * uživatelských rozhraní
 */
public final class Tlacitko extends Button {

    /**
     * Konstanta reprezentuje žádoucí šiřku pro všechna tlačítka, nechť
     * všechna budou s preferovanou šířkou 100 pixelů
     */
    private static final double PREFEROVANA_SIRKA_TLACITKA = 100.0;

    /**
     * Konstruktor vytváří nové tlačítko s textovým popiskem a nastaví textový obsah na
     * tlačítkách (tj. změní textový popisek, který se zobrazuje na tlačítku, což je
     * užitečné pro označení funkce tlačítka nebo zobrazování uživatelsky srozumitelného
     * textu)
     * <p>
     * V deklaraci speciální metody je deklarován <b>parametr</b> (což vlastně je lokální
     * proměnnou), sloužící k přijímání hodnoty zvenčí, která je předávána do metody
     * při jejím volání. Tento parametr definuje, jaký druh hodnot metoda očekává a jak
     * budou tyto hodnoty použity uvnitř metody (parametry jsou deklarovány uvnitř
     * závorkových závorek za názvem metody a odděleny čárkami)
     * <p>
     * U této implementace konstruktor bere jeden <b>argument</b> typu {@link String}
     * (což je vlastně konkrétní hodnota, která je předána do metody při jejím volání),
     * který představuje textový popisek (label), který se zobrazí na tlačítku
     */
    public Tlacitko(String btnText) {
        this.setText(btnText);
        this.setPrefWidth(PREFEROVANA_SIRKA_TLACITKA);
    }
}
