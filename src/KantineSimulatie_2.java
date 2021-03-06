import java.util.*;

import static java.lang.Math.round;

public class KantineSimulatie_2 {

    // kantine
    private Kantine kantine;


    public static final int DAGEN = 7;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen = new String[]
        {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[]{1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        kantine = new Kantine();
        random = new Random();
        int[] hoeveelheden = getRandomArray(
            AANTAL_ARTIKELEN,
            MIN_ARTIKELEN_PER_SOORT,
            MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(
            artikelnamen, artikelprijzen, hoeveelheden);

        kantine.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen
     * min en max van de gegeven lengte te genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for(int i = 0; i < lengte ;i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl)
     * en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array
     * artikelnamen de bijhorende array van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for(int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        double[] omzet = new double[dagen];
        int[] aantal = new int[dagen];
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {
            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = getRandomValue(MIN_PERSONEN_PER_DAG,MAX_PERSONEN_PER_DAG);
            Random rand = new Random();
            aantal[i] = aantalpersonen;

            // laat de personen maar komen...
            for(int j = 0; j < aantalpersonen; j++) {
                Persoon klant;
                int random = rand.nextInt(aantalpersonen);

                if(random >= 13){
                    klant = new Student(j,"HBO-ICT",j+0+j,"Dit is de voornaam","Dit is de achternaam",new Datum(31,01,2001),'M');
                } else if(random>=2&&j<=12){
                    klant = new Docent("XX"+j,"SCMI",j+0+j,"Dit is de voornaam","Dit is de achternaam",new Datum(31,01,2001),'M');
                } else if(random==1){
                    klant = new Kantinemedewerker(j,true,j+0+j,"Dit is de voornaam","Dit is de achternaam",new Datum(31,01,2001),'M');
                }
                  else{
                    klant = new Persoon(j+0+j,"Dit is de voornaam","Dit is de achternaam",new Datum(31,01,2001),'M');
                }
                //System.out.println(klant.toString());

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(MIN_ARTIKELEN_PER_PERSOON,MAX_ARTIKELEN_PER_PERSOON) ;

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(
                    aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste
                // artikelen, sluit aan
                kantine.loopPakSluitAan(new Dienblad(klant), artikelen);
            }

            // verwerk rij voor de kassa
            // druk de dagtotalen af en hoeveel personen binnen
            // zijn gekomen
            // reset de kassa voor de volgende dag
            kantine.verwerkRijVoorKassa();
            //System.out.println("Totale omzet: "+kantine.getKassa().hoeveelheidGeldInKassa());
            //System.out.println("Aantal klanten: "+aantalpersonen);

            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();

            kantine.getKassa().resetKassa();

        }

        double[] array = Administratie.berekenDagOmzet(omzet);
        int size = array.length;

        for(int i=0;i<size;i++){
            switch(i){
                case 0: System.out.println("Maandag: " + array[i]);break;
                case 1: System.out.println("Dinsdag: " + array[i]);break;
                case 2: System.out.println("Woensdag: " + array[i]);break;
                case 3: System.out.println("Donderdag: " + array[i]);break;
                case 4: System.out.println("Vrijdag: " + array[i]);break;
                case 5: System.out.println("Zaterdag: " + array[i]);break;
                case 6: System.out.println("Zondag: " + array[i]);break;
            }
        }
        System.out.println(Administratie.berekenGemiddeldAantal(aantal));
        System.out.println(Administratie.berekenGemiddeldeOmzet(omzet));
    }
    /**
     * Start een simulatie
     */
    public static void main(String[] args) {
        int dagen;
        if (args.length == 0) {
            dagen = DAGEN;
        } else {
            dagen = Integer.parseInt(args[0]);
        }
        KantineSimulatie_2 sim = new KantineSimulatie_2();
        sim.simuleer(dagen);
    }
}