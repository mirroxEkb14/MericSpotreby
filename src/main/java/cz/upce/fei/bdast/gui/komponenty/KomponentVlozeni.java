package cz.upce.fei.bdast.gui.komponenty;

// <editor-fold defaultstate="collapsed" desc="Importy">
import cz.upce.fei.bdast.data.model.Mereni;
import cz.upce.fei.bdast.data.vycty.TypSenzoru;
import cz.upce.fei.bdast.gui.Titulek;
import cz.upce.fei.bdast.gui.alerty.ChybovaZprava;
import cz.upce.fei.bdast.gui.alerty.ErrorAlert;
import cz.upce.fei.bdast.gui.dialogy.DialogVlozeni;
import cz.upce.fei.bdast.gui.dialogy.TypSenzoruValidator;
import cz.upce.fei.bdast.gui.kontejnery.*;
import cz.upce.fei.bdast.gui.koreny.PrikazPanel;
import cz.upce.fei.bdast.gui.koreny.SeznamPanel;
import cz.upce.fei.bdast.tvurce.TvurceMereni;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ButtonBar.ButtonData;

import java.util.Optional;
// </editor-fold>

/**
 * U této implementace dochází k vytvoření panelu s výběrovými poli ({@link ChoiceBox})
 * pro vložení prvků do seznamu. Označení těchto tlačítek je reprezentováno enumerací
 * {@link Titulek}. Tato tlačítka jsou uspořádány v kontejnerové třídě {@link GridPane},
 * která umožňuje organizovat prvky do řádků a sloupců
 */
public final class KomponentVlozeni extends TitulkovyPanel {

    /**
     * Deklarace výběrových polí
     */
    private final ChoiceBox<String> cbVlozPrvni, cbVlozPosledni, cbVlozNaslednika, cbVlozPredchudce;
    /**
     * Implementace {@link TypSenzoruValidator} pro ověření typu senzorů a
     * následné vracení nalezeného typu
     */
    private final TypSenzoruValidator<String, TypSenzoru> typValidator = e -> {
        for (TypSenzoru typ : TypSenzoru.values()) {
            if (e.equalsIgnoreCase(typ.getNazev()))
                return typ;
        }
        return null;
    };

    /**
     * Konstruktor inicializuje veškerá tlačítka a nastaví výchozí hodnotu
     * (tj. vybírá položku)
     */
    public KomponentVlozeni() {
        this.cbVlozPrvni = new VyberovePole(
                Titulek.PRVNI.getNadpis());
        this.cbVlozPrvni.getSelectionModel().select(
                Titulek.PRVNI.getNadpis());
        this.cbVlozPrvni.setOnAction(actionEvent -> nastavUdalostVlozPrvni());

        this.cbVlozPosledni = new VyberovePole(
                Titulek.POSLEDNI.getNadpis());
        this.cbVlozPosledni.getSelectionModel().select(
                Titulek.POSLEDNI.getNadpis());

        this.cbVlozNaslednika = new VyberovePole(
                Titulek.NASLEDNIK.getNadpis());
        this.cbVlozNaslednika.getSelectionModel().select(
                Titulek.NASLEDNIK.getNadpis());

        this.cbVlozPredchudce = new VyberovePole(
                Titulek.PREDCHUDCE.getNadpis());
        this.cbVlozPredchudce.getSelectionModel().select(
                Titulek.PREDCHUDCE.getNadpis());

        nastavKomponentVlozeni();
    }

    private void nastavKomponentVlozeni() {
        this.setText(Titulek.KOMPONENT_VLOZENI.getNadpis());
        this.setContent(dejGridPane());
    }

    private GridPane dejGridPane() {
        final GridPane gridPane = new MrizkovyPanel();
        gridPane.add(cbVlozPrvni, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(cbVlozPosledni, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_PRVNI);
        gridPane.add(cbVlozNaslednika, MrizkovyPanel.SLOUPCOVY_INDEX_PRVNI, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        gridPane.add(cbVlozPredchudce, MrizkovyPanel.SLOUPCOVY_INDEX_DRUHY, MrizkovyPanel.RADKOVY_INDEX_DRUHY);
        return gridPane;
    }

    /**
     * Metoda {@link Button#setOnAction(EventHandler)} se používá k nastavení akce
     * (události) nebo kódu, který se má provést po stisknutí tohoto tlačítka, což
     * dává tlačítkům funkcionalitu
     * <p>
     * Akce může být definována jako lambda výraz
     * <p>
     * U implementrace tlačítka {@code Prvni} dojde k vytvoření dialogu podle
     * zvoleného uživateli měření a vytvoření nového měření podle dat z dialogu
     */
    private void nastavUdalostVlozPrvni() {
        final Optional<Mereni> noveMereni = dejNoveMereni();
        System.out.println(noveMereni);
        cbVlozPrvni.getSelectionModel().select(Titulek.PRVNI.getNadpis());
    }

    /**
     * Přivátní pomocní metoda
     * <p>
     * Pokud uživatel ve výběrovém poli ({@link ChoiceBox}) zvolil {@link TypSenzoru#ELEKTRIKA}
     * anebo {@link TypSenzoru#VODA}, tak po ošetření vytvoří nové měření podle požadovaných
     * dat a přidá ho do seznamu. Pokud během ošetření vstupu uživatele dojde v výjimce, tak
     * se dialog zavře a uživateli se zobrazí alert. Pokud ve výběrovém poli uživatel zvolil
     * {@link Titulek#PRVNI}, {@link Titulek#POSLEDNI}, {@link Titulek#NASLEDNIK} anebo
     * {@link Titulek#PREDCHUDCE}, tak dialog se neotevře
     *
     * @return vratí {@link Optional} s nově vytvořeným {@link Mereni}m, pokud uživatel zvolil
     * nějaký typ měření z {@link ChoiceBox}, v opačném přídaně {@link Optional#empty()}
     */
    private Optional<Mereni> dejNoveMereni() {
        final String vstupniTyp = cbVlozPrvni.getSelectionModel().getSelectedItem();
        if (!jeTypSenzoru(vstupniTyp))
            return Optional.empty();

        final TypSenzoru typSenzoru = typValidator.aplikovat(vstupniTyp);
        final int idSenzoru = SeznamPanel.getInstance().getItems().size() + PrikazPanel.ZVETSOVAC_SEZNAMU;

        final DialogVlozeni dialogVlozeni = new DialogVlozeni(idSenzoru, typSenzoru);
        Optional<ButtonType> odpoved = dialogVlozeni.showAndWait();
        try {
            if (odpoved.isPresent() && jeTlacitkoOk(odpoved.get()))
                return TvurceMereni.dejNoveMereni(idSenzoru, typSenzoru);
            return Optional.empty(); // nedosažitelný kód
        } catch (NumberFormatException | NullPointerException ex) {
            ErrorAlert.nahlasErrorLog(ChybovaZprava.VYTVORENI_ELEKTRIKY.getZprava());
            return Optional.empty();
        }
    }

    /**
     * Ověří, zda {@link ButtonType} reprezentuje výchozí tlačítko v dialogu nebo scéně.
     * Výchozí tlačítko obvykle bývá aktivováno, když uživatel stiskne klávesu "Enter"
     * nebo "Return" a poskytuje pohodlný způsob pro vykonání akce:
     * <ul>
     * <li> <b>ButtonType</b>: Reprezentuje konkrétní typ tlačítka v dialogu nebo scéně,
     * například OK, Storno, Ano, Ne, atd.
     * <li> <b>getButtonData()</b>: Získává {@link ButtonData}, který je spojen s {@link ButtonType}.
     * {@link ButtonData} poskytuje informace o roli tlačítka, například zda jde o
     * tlačítko Storno, OK, atd.
     * <li> <b>isDefaultButton()</b>: Když je volána na objektu {@link ButtonData},
     * ověřuje, zda je tlačítko výchozí
     * </ul>
     *
     * @param odpoved typ tlačítka z dialogu vložení
     *
     * @return vratí {@code true}, když bylo stisknuto {@code OK}, v opačném případě {@code false}
     */
    private boolean jeTlacitkoOk(ButtonType odpoved) {
        return odpoved.getButtonData().isDefaultButton();
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Ošetřuje, zda při stisknutí {@link ChoiceBox}u uživatel zvolil nějaký navržený
     * typ anebo zvolil název tohoto {@link ChoiceBox}u, což, například, u {@code cbVlozPrvni}
     * je {@link Titulek#PRVNI}
     *
     * @param vstupniTyp zvolený uživatelem typ
     *
     * @return vratí {@code true}, pokud byl vybrán nějaký z typů senzorů, jinak byla
     * vybraná hodnota z Enumu {@link Titulek} a proto {@code false}
     */
    private boolean jeTypSenzoru(String vstupniTyp) {
        return !vstupniTyp.equalsIgnoreCase(Titulek.PRVNI.getNadpis());
    }

/**
 * Editor folding v Javě je funkce textových editorů, která umožňuje skrývat a
 * rozbalovat bloky kódu, což zvyšuje přehlednost a usnadňuje navigaci v kódu
 * <p>
 * Když blok kódu je skryt, většina vývojových prostředí zobrazí záhlaví, které
 * obsahuje název bloku kódu a umožňuje rozbalit ho zpět:
 * <ul>
 * <li> <b>Seskupení importů</b>: je možné seskupit importy na začátku souboru,
 * aby bylo jasné, které balíčky jsou použity
 * <li> <b>Seskupení konstant</b>: je možné seskupit konstanty nebo enum hodnoty,
 * aby bylo možné rychle najít potřebnou hodnotu
 * </ul>
 */
// <editor-fold defaultstate="collapsed" desc="Gettery">
    public ChoiceBox<String> getBtnVlozPrvni() { return cbVlozPrvni; }

    public ChoiceBox<String> getBtnVlozPosledni() { return cbVlozPosledni; }

    public ChoiceBox<String> getBtnVlozNaslednika() { return cbVlozNaslednika; }

    public ChoiceBox<String> getBtnVlozPredchudce() { return cbVlozPredchudce; }
// </editor-fold>
}
