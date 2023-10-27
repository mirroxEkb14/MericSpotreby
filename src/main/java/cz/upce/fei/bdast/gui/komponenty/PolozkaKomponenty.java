package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
// </editor-fold>

/**
 * Rozhraní slouží k definici společných chování pro všechny typy položek vkládaných do seznamu,
 * to jsou:
 * <ol>
 * <li> {@link KomponentVlozeniElektriky}
 * <li> {@link KomponentVlozeniVody}
 * </ol>
 * Obsahuje konstantu {@code VYCHOZI_KALENDAR}, která představuje výchozí hodnotu pro kalendář
 * (datum), a která je společná pro všechny implementace rozhraní
 * <p>
 * Pak definuje další konstantu {@code VYCHOZI_HODNOTA_SPOTREBY}, což je řetězcovou hodnotou "100".
 * Tímto způsobem rozhraní specifikuje, že všechny třídy implementující toto rozhraní by měly mít
 * přístup k této konstantě, která je použita pro nastavení výchozí hodnoty pro spotřebu (energie
 * a vody)
 * <p>
 * Obsahuje metodu {@link PolozkaKomponenty#dejMesicniKalendar()}, která vrací {@link DatePicker}
 * komponentu pro výběr měsíce
 * <p>
 * Třídy {@link KomponentVlozeniElektriky} a {@link KomponentVlozeniVody} jsou konkrétními
 * implementacemi tohoto rozhraní pro různé typy položek, tj. pro elektrická a vodní měření a
 * obsahují metody a atributy specifické pro každý typ položky
 * <p>
 * Slouží také jako označení pro třídy, které implementují tento "kontrakt" jako jsou {@link KomponentMaxSpotreba},
 * {@link KomponentDenSpotreba} a {@link KomponentPrumerSpotreba}
 */
public interface PolozkaKomponenty {

    LocalDate VYCHOZI_KALENDAR = LocalDate.now();
    String VYCHOZI_HODNOTA_SPOTREBY = "100";

    /**
     * Nastaví minimální a maximální datový rozsah, tj. omezí výběru data na určité období
     * (na jeden měsíc od aktuálního data):
     * <ul>
     * <li> Získá aktuální datum
     * <li> Nastaví minimální a maximální datum pro {@link DatePicker} tak, aby omezovaly
     * výběr na jeden měsíc od aktuálního data:
     *      <ul>
     *      <li> {@link TemporalAdjusters#firstDayOfMonth()}: Výpočet prvního dne v aktuálním měsíci.
     *      Metoda vytvoří objekt {@link TemporalAdjuster}, který posune zadané datum na první den
     *      aktuálního měsíce, což znamená, že časová část datového objektu zůstane zachována, ale
     *      den bude nastaven na 1. Například, pokud metoda je používaná v den 15. července 2023,
     *      výstupem bude 1. července 2023.
     *      <li> {@link TemporalAdjusters#lastDayOfMonth()}: Výpočet posledního dne v aktuálním
     *      měsíci. Vytvoří objekt {@link TemporalAdjuster}, který posune zadané datum na poslední den
     *      aktuálního měsíce. Podobně jako u prvního dne, časová část datového objektu zůstane
     *      zachována, ale den bude nastaven na poslední den měsíce. Například, pokud metoda je
     *      používaná v den 5. července 2023, výstupem bude 31. července 2023
     *      <li> <b>Nastavení minimálního a maximálního data pro výběr na jeden měsíc</b>: Nastaví
     *      {@code DayCellFactory} pro {@link DatePicker} tak, aby omezoval výběr datumů na jeden měsíc
     *      od aktuálního data. Umožní upravit chování jednotlivých buněk výběru data (days) v kalendáři,
     *      který zobrazuje {@link DatePicker}:
     *              <ul>
     *              <li> <b>kalendar.setDayCellFactory(picker -> ...</b> : Nastaví {@code DayCellFactory} pro {@link DatePicker}.
     *              {@code DayCellFactory} je funkce, která umožňuje vytvořit a konfigurovat buňky kalendáře, které zobrazují
     *              jednotlivé dny. Je vyvolána pro každý den, který je zobrazen v kalendáři
     *              <li> <b>new DateCell() { ... }</b>: Anonymní třída, která rozšiřuje třídu {@link DateCell}, sloužící k
     *              vytvoření a konfiguraci buňek kalendáře
     *              <li> {@link DateCell#updateItem(LocalDate, boolean)}: Je volána pro každý den v kalendáři. Parametr
     *              {@code date} představuje datum tohoto dne, a {@code empty} označuje, zda je buňka prázdná
     *              <li> <b>super.updateItem(date, empty)</b>: Volá metodu {@code updateItem} ze třídy {@link DateCell} pro
     *              nastavení základního chování buňky, což zahrnuje zobrazení textu pro daný den
     *              <li> {@link DateCell#setDisable(boolean)}: Provádí omezení pro výběr datumů. Zkontroluje, zda daný den
     *              {@code date} je před prvním dnem měsíce (určeným {@code firstDayOfMonth}) nebo zda je po posledním dni
     *              měsíce (určeným {@code lastDayOfMonth}). Pokud ano, buňka (den) je nastavena jako deaktivovaná
     *              (nevybratelná), což znamená, že uživatel nemůže vybrat datum mimo určený měsíc
     *              </ul>
     *      </ul>
     * </ul>
     */
    default DatePicker dejMesicniKalendar() {
        final DatePicker kalendar = new DatePicker(LocalDate.now());
        final LocalDate prvniDenMesice = VYCHOZI_KALENDAR.with(TemporalAdjusters.firstDayOfMonth());
        final LocalDate posledniiDenMesice = VYCHOZI_KALENDAR.with(TemporalAdjusters.lastDayOfMonth());
        kalendar.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(prvniDenMesice) || date.isAfter(posledniiDenMesice));
            }
        });
        return kalendar;
    }
}
