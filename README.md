# Měřič spotřeby

Motivační příklad:
* V rámci měřícího zařízení jsou shromažďovány data ze senzorů měřící spotřebu
* Spotřeba se měří v hodinových intervalech, přičemž měřící zařízení je schopno měřit (i) spotřebu vody a (ii) spotřebu elektrické energie
* Časové razítko měření ohraničuje hodinový interval, ve kterém je zaznamenána měřená spotřeba

![alt form-image](/resources/form.png)

## Použité datové struktury

* V rámci modulu **AbstrDoubleList** je implementuvaná abstraktní datová struktura (ADS) *obousměrně necyklicky zřetězený lineární seznam* v dynamické paměti (stylizovaně znázorněný v rámci obr. 1):
  * tato třída implementuje rozhraní **IAbstrDoubleList**, které implementuje implicitní rozhraní **Iterable**;
* Rozhraní **IAbstrDoubleList** je definováno následovně:
  * **void zrus()** - zrušení celého seznamu;
  * **boolean jePrazdny()** - test naplněnosti seznamu;
  * **void vlozPrvni(T data)** - vložení prvku do seznamu na první místo;
  * **void vlozPosledni(T data)** - vložení prvku do seznamu na poslední místo;
  * **void vlozNaslednika(T data)** - vložení prvku do seznamu jakožto následníka aktuálního prvku;
  * **void vlozPredchudce(T data)** - vložení prvku do seznamu jakožto předchůdce aktuálního prvku;
  * **T zpristupniAktualni()** - zpřístupnění aktuálního prvku seznamu;
  * **T zpristupniPrvni()** - zpřístupnění prvního prvku seznamu;
  * **T zpristupniPosledni()** - zpřístupnění posledního prvku seznamu;
  * **T zpristupniNaslednika()** - zpřístupnění následníka aktuálního prvku;
  * **T zpristupniPredchudce()** - zpřístupnění předchůdce aktuálního prvku:
    * Pozn.: operace typu zpřístupni, přenastavují pozici aktuálního prvku;
  * **T odeberAktualni()** - odebrání (vyjmutí) aktuálního prvku ze seznamu poté je aktuální prvek nastaven na první prvek;
  * **T odeberPrvni()** - odebrání prvního prvku ze seznamu;
  * **T odeberPosledni()** - odebrání posledního prvku ze seznamu;
  * **T odeberNaslednika()** - odebrání následníka aktuálního prvku ze seznamu;
  * **T odeberPredchudce()** - odebrání předchůdce aktuálního prvku ze seznamu;
  * **Iterator<T> iterator()** - vytvoří iterátor (dle rozhraní Iterable);

![alt dll-image1](/resources/dll-obr1.png)

## Správce měření

Pro ověření funkčnosti implementovaných ADS je vytvořen modul **SpravceMereni**, který umožnuje správu měření z jednotlivých měření od senzorů a implementuje následující rozhraní:
  * **void vlozMereni(Mereni merni, enumPozice pozice)** - vloží nové   měření do seznamu na příslušnou pozici (první, poslední, předchůdce, následník);
  * **Mereni zpristupniMereni(enumPozice pozice)** - zpřístupní měření z požadované pozice (první, poslední, předchůdce, následník, aktuální);
  * **Mereni odeberMereni (enumPozice pozice)** - odebere měření z požadované pozice (první, poslední, předchůdce, následník, aktuální);
  * **Iterator iterator()** –  vrátí iterátor;
  * **IAbstrDoubleList MereniDen(int idSenzoru, LocalDate datum)** – pomocí iterátoru vyhledá  všechna měření daného senzoru v rámci požadovaného dne (seznam je následně možné vypsat);
  * **double maxSpotreba(int idSenzoru, LocalDateTime datumOd, LocalDateTime datumDo)** – pomocí iterátoru vyhledá  maximální spotřebu daného senzoru v rámci požadovaného intervalu:
    * pro elektrický senzor je hodnota dána součtem VT a NT (hodnota je následně zobrazena v GUI);
  * **double prumerSpotreba(int idSenzoru, LocalDateTime datumOd, LocalDateTime datumDo)** – pomocí iterátoru zjistí průměrnou spotřebu v rámci daného:
     * pro elektrický senzor je hodnota dána součtem VT a NT (hodnota je následně zobrazena v GUI);
  * **void zrus()** – zruší všechny měření;

![alt dll-image12](/resources/dll-obr2.png)

Modul **Mereni** – Abstraktní třída **Mereni** disponuje atributy:
  * int idSenzor;
  * enumTypSenzoru typSenzoru //(voda/elektřina);
  * LocalDateTime casMereni //(vždy celá hodina);

a dále konkrétními měřeními jež jsou potomci abstraktní třídy **Mereni**:
  * MereniVoda
    * double spotrebaM3;
  * MereniElektrika
    * double spotrebaVT;
    * double spotrebaNT;

## Demonstrační program

Pro obsluhu aplikace je vytvořeno uživatelské formulářové rozhraní **ProgMereni**, které umožňuje obsluhu programu a volat požadované operace

Zmíněný program:
  * nechť umožňuje zadávání vstupních dat z klávesnice, ze souboru a z generátoru, výstupy z programu;
  * nechť je možné zobrazit na obrazovce a uložit do souboru;
  * generátor umožňuje generovat jednotlivá měření v ohraničeném období (např. v rámci jednoho měsíce);
