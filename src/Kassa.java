import java.awt.*;
import java.util.Iterator;

public class Kassa {
    private double geldInKas;
    public KassaRij rij;
    private int aantalArtikelen;


    /**
     * Constructor
     */
    public Kassa(KassaRij kassarij) {
        rij=kassarij;
        geldInKas=0;
        aantalArtikelen=0;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op.
     * Tel deze gegevens op bij de controletotalen die voor
     * de kassa worden bijgehouden. De implementatie wordt
     * later vervangen door een echte betaling door de persoon.
     */
    public void rekenAf() throws TeWeinigGeldException {
        Dienblad klant = rij.eerstePersoonInRij();

        int aantal=klant.getAantalArtikelen();
        double totaal = 0;

        Iterator it = klant.getArtikelen();
        while(it.hasNext()){
            Artikel element = (Artikel)it.next();
            totaal+=element.getPrijs();
            it.remove();
        }
        double teBetalen;
        if(klant.getKlant() instanceof KortingskaartHouder){
            KortingskaartHouder persoon = (KortingskaartHouder)klant.getKlant();
            double max = persoon.geefMaximum();
            //System.out.println(+totaal+" "+persoon.getClass()+" "+persoon.heeftMaximum());
            teBetalen = totaal*(1-(persoon.geefKortingsPercentage()/100));
            if(persoon.heeftMaximum() && (totaal-teBetalen)>max) {
                teBetalen = totaal - max;
                //System.out.println("25");
            }/*else if(persoon.heeftMaximum()){
                System.out.println("25");
            } else{
                System.out.println("35");
            }*/

            //System.out.print(teBetalen);
            //System.out.println("\n");
        } else {
            teBetalen = totaal;
        }


        Betaalwijze betaalwijze = klant.getKlant().getBetaalwijze();

        if(betaalwijze instanceof Pinpas || betaalwijze instanceof Contant){
            betaalwijze.betaal(teBetalen);
            aantalArtikelen+=aantal;
            geldInKas+=teBetalen;
        } else{
            //System.out.println(betaalwijze.saldo);
        }



    }
    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    /*public int getAantalArtikelen(Dienblad klant) {
        int aantal=0;
        Iterator it = klant.getArtikelen();
        //System.out.println(it.hasNext());
        while(it.hasNext()){
            Artikel element = (Artikel)it.next();
            System.out.println("getAantalArtikelen() while element.getPrijs "+element.getPrijs());
            aantal+=1;
            it.remove();
        }
        return aantal;
    }*/

    /**
     * Methode om de totaalprijs van de artikelen
     * op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
   /* public double getTotaalPrijs(Dienblad klant) {
        double totaal = 0;
        Iterator it = klant.getArtikelen();
        while(it.hasNext()){
            Artikel element = (Artikel)it.next();
            System.out.println("getTotaalPrijs() while element.getPrijs "+element.getPrijs());
            totaal+=element.getPrijs();
            it.remove();
        }
        return totaal;
    }*/
    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd,
     * vanaf het moment dat de methode resetWaarden is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return aantalArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kass
     * zijn gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return geldInKas;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en
     * de totale hoeveelheid geld in de kassa.
     */
    public void resetKassa() {
        geldInKas=0;
        aantalArtikelen=0;
    }
}