class KantineSimulatie_1 {

    private Kantine kantine;

    public static final int DAGEN = 7;

    /**
     * Constructor
     */
    public KantineSimulatie_1() {
        kantine = new Kantine();
    }

    /**
     * Deze methode simuleert een aantal dagen in het
     * verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {

        // herhaal voor elke dag
        for(int i = 0;i<DAGEN;i++) {

            // per dag nu even vast 10 + i personen naar binnen
            // laten gaan, wordt volgende week veranderd...

            // for lus voor personen
            for(int j = 0; j < 10 + i; j++){
                kantine.loopPakSluitAan(new Dienblad(),new String[] {"Koffie"});
            }

            // verwerk rij voor de kassa
            // toon dagtotalen (artikelen en geld in kassa)
            // reset de kassa voor de volgende dag
            kantine.verwerkRijVoorKassa();
            System.out.println("Totale omzet: "+kantine.getKassa().hoeveelheidGeldInKassa());
            kantine.getKassa().resetKassa();
        }
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
        KantineSimulatie_1 sim = new KantineSimulatie_1();
        sim.simuleer(dagen);
    }
}