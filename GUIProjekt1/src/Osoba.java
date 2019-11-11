import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

class Osoba extends JLabel{
	String imie;
	String nazwisko;
	int pesel;
	String adress;
	LocalDate dataUrodzenia;
	LocalDate dataPierwszegoWynajmu;
	Pomieszczenie pomieszczenie;
	boolean wybrany = false;
	List<Pomieszczenie> ownedPomieszczenie = new ArrayList<Pomieszczenie>();
	
	Osoba(String imie, String nazwisko, int pesel, String adress){
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.pesel=pesel;
		this.adress=adress;
		this.dataUrodzenia=dataUrodzenia;
		this.dataPierwszegoWynajmu=dataPierwszegoWynajmu;
		//this.setEditable(false);
		
	}
	Osoba(String imie, String nazwisko, int pesel, String adress, Pomieszczenie pomieszczenie){
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.pesel=pesel;
		this.adress=adress;
		this.pomieszczenie=pomieszczenie;
		this.dataUrodzenia=dataUrodzenia;
		this.dataPierwszegoWynajmu=dataPierwszegoWynajmu;
		//this.setEditable(false);
		ownedPomieszczenie.add(pomieszczenie);
		dataPierwszegoWynajmu=LocalDate.now();
		
	}
	public String getName() {
		return imie + " " + nazwisko;
	}

	public String getNazwisko() {
		return nazwisko;
	}
	public boolean getWybrany() {
		System.out.println("Wybrano " + imie + " : " + wybrany);
		return wybrany;
	}
	public void setWybrany(boolean wybor) {
		this.setBackground(Color.CYAN);
		wybrany = wybor;
		this.setOpaque(wybor);
		
	}
	public void aktualizujOsobe(String name, String nazwisko, int pesel, String adress) {
		this.imie=name;
		this.nazwisko=nazwisko;
		this.pesel=pesel;
		this.adress=adress;
		this.setText(nazwisko);
		repaint();
		
	}
	@Override
	public String toString() {
		return "Imie: " + imie + "\n Nazwisko: "+ nazwisko + "\n PESEL: "+ Integer.toString(pesel) + "\n Adres: " + adress;
	}
	public LocalDate getDataPierwszegoWynajmu() throws NeverRentException {
		if(dataPierwszegoWynajmu != null)
			return dataPierwszegoWynajmu;
		else
			throw new NeverRentException(getName()+" nie wynajal zadnego pomieszczenia");
	}
	
}



