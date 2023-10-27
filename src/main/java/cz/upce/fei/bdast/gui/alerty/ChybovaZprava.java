package cz.upce.fei.bdast.gui.alerty;

/**
 * Tento výčtový typ je určen k definici typů chybových zpráv a obsahuje
 * konkrétní hodnoty těchto chybových zpráv
 */
public enum ChybovaZprava {
    VYTVORENI_ELEKTRIKY("Špatně nastavena pole pro měření elektriky"),
    VYTVORENI_VODY("Špatně nastavena pole pro měření vody"),
    ZALOHOVANI("Seznam nebyl uložen: chyba s operacemi vstupu a výstupu (I/O)"),
    OBNOVENI("Seznam nebyl načten: chyba s operacemi vstupu a výstupu (I/O)"),
    MAXIMALNI_SPOTREBA("Špatně nastavena data: číslo by mělo být celočíselné"),
    DEN_SPOTREBA_ZADNE_PRVKY("Nebyly nalezeny žádné prvky s takým id v ten den"),
    DEN_SPOTREBA_SPATNA_DATA("Špatně zadány id nebo datum"),
    PRUMER_SPOTREBA("Nebylo možno spočítat přůměrnou hodnotu");

    private final String zprava;

    ChybovaZprava(String zprava) { this.zprava = zprava; }

    public String getZprava() { return zprava; }
}
