package cz.upce.fei.bdast.gui.alerty;

/**
 * Tento výčtový typ je určen k definici typů chybových zpráv a obsahuje
 * konkrétní hodnoty těchto chybových zpráv
 */
public enum ChybovaZprava {
    VYTVORENI_ELEKTRIKY("Špatně nastavena pole pro měření elektriky"),
    VYTVORENI_VODY("Špatně nastavena pole pro měření vody"),
    GENEROVANI("Špatně nastavena data pro generování nových prvků"),
    ZALOHOVANI("Seznam nebyl uložen: chyba s operacemi vstupu a výstupu (I/O)"),
    OBNOVENI("Seznam nebyl načten: chyba s operacemi vstupu a výstupu (I/O)"),
    NEDOSTUPNI("Zatím bohužel není dostupné");

    private final String zprava;

    ChybovaZprava(String zprava) { this.zprava = zprava; }

    public String getZprava() { return zprava; }
}
