import java.io.IOException;


public class Main {
	
	public static void main(String[] args) throws TooManyThingsException, NeverRentException, IOException {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Magazyn magazyn = new Magazyn();
            	Osoba osoba1 = new Osoba("Jan", "Kowalski", 1234599,"Warszawa");
            	Osoba osoba2 = new Osoba("Michal", "Schmidt", 876543391,"Berlin");
            	Osoba osoba3 = new Osoba("Patryk", "Alterman", 32427287, "Berlin");
            	Osoba osoba4 = new Osoba("Mariusz", "Pudzian", 44742378,"Warszawa");
            	Osoba osoba5 = new Osoba("Grzegorz", "Lewandowski", 324655588,"Warszawa");
        		Pomieszczenie pom1 = new Pomieszczenie("Pom1",500, 20, osoba1);
        		Pomieszczenie pom2 = new Pomieszczenie("Pom2",50, 20);
        		Pomieszczenie pom3 = new Pomieszczenie("Pom3",50, 20);
        		Pomieszczenie pom4 = new Pomieszczenie("Pom4",220, 200, osoba1);
        		Pomieszczenie pom5 = new Pomieszczenie("Pom5",50, 20);
        		Pomieszczenie pom6 = new Pomieszczenie("Pom6",50, 20);
        		Pomieszczenie pom7 = new Pomieszczenie("Pom7",50, 20);
        		Pomieszczenie pom8 = new Pomieszczenie("Pom8",50, 20);
        		Pomieszczenie pom9 = new Pomieszczenie("Pom9",30, 20);
        		Pomieszczenie pom10 = new Pomieszczenie("Pom10",8, 8);
        		//Przedmiot przed = new Przedmiot("Pomarancze", 10, "Skrzynia");
        		//Przedmiot przed2 = new Przedmiot("Ogorki", 5, "Paczka");
        		//Przedmiot przed3 = new Przedmiot("Arbuzy", 25,"Kontener");
        		
        		magazyn.addPomieszczenie(pom1);
        		magazyn.addPomieszczenie(pom2);
        		magazyn.addPomieszczenie(pom3);
        		magazyn.addPomieszczenie(pom4);
        		magazyn.addPomieszczenie(pom5);
        		magazyn.addPomieszczenie(pom6);
        		magazyn.addPomieszczenie(pom7);
        		magazyn.addPomieszczenie(pom8);
        		magazyn.addPomieszczenie(pom9);
        		magazyn.addPomieszczenie(pom10);
        		
        		
        		magazyn.chosenPomieszczenie=pom1;
        		magazyn.wybranaOsoba=osoba2;

            	magazyn.addOsoba(osoba1);
        		magazyn.addOsoba(osoba2);
        		magazyn.addOsoba(osoba3);
        		magazyn.addOsoba(osoba4);
        		magazyn.addOsoba(osoba5);

        	
        		
        		
            
     
		//HashMap<Pomieszczenie, Osoba> Przynaleznosc = new HashMap<>();

            }
        });

	}

}



@SuppressWarnings("serial")
class TooManyThingsException extends Exception {
	TooManyThingsException(String message){
		super(message);
	}
	
}
@SuppressWarnings("serial")
class NeverRentException extends Exception {
	NeverRentException(String message){
		super(message);
	}
	
}