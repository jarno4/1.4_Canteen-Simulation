public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	public Datum(int dag, int maand, int jaar) {
		this.dag = dag;
		this.maand = maand;
		this.jaar = jaar;
	}

	public Datum() {
		dag = 0;
		maand = 0;
		jaar = 0;
	}
	// TODO

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		// TODO
		return false;
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */

	public int getDag() {
		return dag;
	}

	public int getMaand() {
		return maand;
	}

	public int getYear() {
		return jaar;
	}

	public void setDag(int dag) {
		this.dag = dag;
	}

	public void setMaand(int maand) {
		this.maand = maand;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}


	public String getDatumAsString() {
		// TODO
		return dag + "-" + maand + "-" + jaar;
	}

	public boolean bestaatDatum() {
		if(jaar>=1900&&jaar<=2100&&maand>=1&&maand<=12&&dag>=1){
			if(maand==1 || maand==3 || maand==5 || maand==7 || maand==8 || maand==10 || maand==12){
				if(dag<=31){	return true;	}
			}
			if(maand==4 || maand==6 || maand==9 || maand==11){
				if(dag<=30){	return true;	}
			}
			if(maand==2 && jaar % 4 == 0){
				if(jaar%100==0){
					if(jaar%400==0){
						if(dag<=29){	return true;	}
					}
				} else{
					if(dag<=29){	return true;	}

				}
				if(dag<=28){	return true;	}
			}
		}
		return false;
	}
}
