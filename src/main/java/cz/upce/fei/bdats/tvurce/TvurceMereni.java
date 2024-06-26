package cz.upce.fei.bdats.tvurce;

import cz.upce.fei.bdats.data.model.Mereni;
import cz.upce.fei.bdats.data.model.MereniElektrika;
import cz.upce.fei.bdats.data.model.MereniVoda;
import cz.upce.fei.bdats.data.vycty.TypSenzoru;
import cz.upce.fei.bdats.gui.alerty.ChybovaZprava;
import cz.upce.fei.bdats.gui.alerty.ErrorAlert;
import cz.upce.fei.bdats.gui.dialogy.DialogVlozeni;
import cz.upce.fei.bdats.gui.komponenty.KomponentVlozeniElektriky;
import cz.upce.fei.bdats.gui.komponenty.KomponentVlozeniVody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Tato třída slouží pro vytvoření nového {@link Mereni} a případně pro ověření
 * dialogu {@link DialogVlozeni}, odkud tato třída čerpá data pro vytvoření
 * {@link Mereni}
 */
public final class TvurceMereni {

    /**
     * Konstanta reprezentuje časovou část (00:00:00) pro vytvoření {@link LocalDateTime}
     */
    private static final LocalTime VYCHOZI_CAS = LocalTime.MIDNIGHT;

    /**
     * Klíčové slovo {@code yield} se používá v kontextu tzv. {@code switch} výrazů (switch expressions).
     * Umožňuje návrat hodnoty (return value) z každého případu (case) v rámci switch výrazu.
     * Představuje alternativu k tradičním {@code switch} příkazům, které nevracejí hodnotu. Nahrazuje
     * tradiční {@code return} a automaticky ukončuje výraz po vrácení hodnoty
     */
    public static Optional<Mereni> dejNoveMereni(int idSenzoru, TypSenzoru typSenzoru) {
        return switch (typSenzoru) {
            case ELEKTRIKA -> {
                final KomponentVlozeniElektriky komponentElektriky = (KomponentVlozeniElektriky) DialogVlozeni.getDialogovyKomponent();
                final Optional<Double> spotrebaVT = dejDouble(komponentElektriky.getTfSpotrebaVT().getText());
                final Optional<Double> spotrebaNT = dejDouble(komponentElektriky.getTfSpotrebaNT().getText());
                final LocalDateTime kalendar = dejKalendar(komponentElektriky.getKalendar().getValue());
                if (spotrebaVT.isEmpty() || spotrebaNT.isEmpty()) {
                    ErrorAlert.nahlasErrorLog(ChybovaZprava.VYTVORENI_ELEKTRIKY.getZprava());
                    yield Optional.empty();
                }
                yield Optional.of(new MereniElektrika(idSenzoru, kalendar, spotrebaVT.get(), spotrebaNT.get()));
            }
            case VODA -> {
                final KomponentVlozeniVody komponentVody = (KomponentVlozeniVody) DialogVlozeni.getDialogovyKomponent();
                final Optional<Double> spotrebaM3 = dejDouble(komponentVody.getTfSpotrebaM3().getText());
                final LocalDateTime kalendar = dejKalendar(komponentVody.getKalendar().getValue());
                if (spotrebaM3.isEmpty()) {
                    ErrorAlert.nahlasErrorLog(ChybovaZprava.VYTVORENI_VODY.getZprava());
                    yield Optional.empty();
                }
                yield Optional.of(new MereniVoda(idSenzoru, kalendar, spotrebaM3.get()));
            }
        };
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Ošetří, zda je možné převést hodnotu argumentu (řetězce {@link String}) na hodnotu
     * s plovoucí desetinnou čárkou ({@link Double}). Provádí konverzi textového řetězce
     *
     * @param hodnota textový řetězec, který reprezentuje číslo s desetinnou čárkou
     *
     * @return vrací {@link Optional} s hodnotou typu {@link Double}
     */
    private static Optional<Double> dejDouble(String hodnota) {
        try {
            final double result = Double.parseDouble(hodnota);
            return Optional.of(result);
        } catch (NumberFormatException | NullPointerException ex) {
            return Optional.empty();
        }
    }

    /**
     * Privátní pomocní metoda
     * <p>
     * Vytvoří nové datum s časem, jde datum byl zadán uživatelem a čas podle konstanty
     *
     * @param datum uživatelský datum
     *
     * @return nový objekt {@link LocalDateTime} s datem v rámci měsíce od aktuálního data
     */
    private static LocalDateTime dejKalendar(LocalDate datum) {
        return LocalDateTime.of(datum, VYCHOZI_CAS);
    }
}
