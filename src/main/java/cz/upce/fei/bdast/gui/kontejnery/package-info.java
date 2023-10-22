/**
 * Modul {@code kontejnery} obsahuje zdrojové soubory potomků dědící od tříd
 * jako jsou {@link javafx.scene.control.Button}, {@link javafx.scene.control.TitledPane}
 * apod, aby bylo možné přizpůsobit tyto potomky podle určitých potřeb:
 * <ul>
 * <li> {@link cz.upce.fei.bdast.gui.kontejnery.Tlacitko}: obsahuje určiná
 * nastavení, která jsou platná pro všechná tlačítka v rámci této aplikace,
 * jako je preferováná šiřka (což znamená, že každé tlačítko má přesně
 * definovanou šitku, a to je v rodičovské třídě {@link cz.upce.fei.bdast.gui.kontejnery.Tlacitko})
 * dědící od {@link javafx.scene.control.Button}
 * <li> {@link cz.upce.fei.bdast.gui.kontejnery.TitulkovyPanel}: obsahuje
 * nastavení, charakteristické pro veškeré komponenty v rámci panelu {@link cz.upce.fei.bdast.gui.koreny.PrikazPanel},
 * jako je komponent {@code Vložení}, {@code Zpřístupňování} apod. K
 * takovýmto nastavením třeba patří {@code setExpanded}, {@code setCollapsible},
 * {@code setAnimated}, která jsou stejná u všech nastavení v rámci programu
 * </ul>
 *
 * @since 1.0
 * @author amirov 10/22/2023
 * @version 1.0
 */
package cz.upce.fei.bdast.gui.kontejnery;