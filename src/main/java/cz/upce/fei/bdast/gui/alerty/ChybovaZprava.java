package cz.upce.fei.bdast.gui.alerty;

/**
 * Tento výčtový typ je určen k definici typů chybových zpráv a obsahuje
 * konkrétní hodnoty těchto chybových zpráv
 */
public enum ChybovaZprava {
    VYTVORENI_ELEKTRIKY("Špatně nastavena pole pro měření vody"),
    VYTVORENI_VODY("Špatně nastavena pole pro měření elektriky");

    private final String zprava;

    ChybovaZprava(String zprava) { this.zprava = zprava; }

    public String getZprava() { return zprava; }
}
