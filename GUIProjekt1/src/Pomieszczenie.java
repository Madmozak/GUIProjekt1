import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
class Pomieszczenie extends JPanel{
	UUID ID = UUID.randomUUID();
	String nazwaPomieszczenia;
	Osoba osoba;
	boolean wynajety = false;
	boolean aktywny = true;
	int objetosc;
	int pozostalaObjetosc;
	LocalDate dataWynajmu;
	int iloscDniWynajmu;
	boolean wybranePomieszczenie = false;
	List<Przedmiot> przedmioty = new ArrayList<Przedmiot>();
	Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	//List<JButton> buttonList = new ArrayList<JButton>();
	
	Pomieszczenie(String nazwaPomieszczenia, int objetosc, int iloscDniWynajmu){
		this.nazwaPomieszczenia=nazwaPomieszczenia;
		this.objetosc=objetosc;
		this.iloscDniWynajmu=iloscDniWynajmu;
		pozostalaObjetosc = objetosc;
		
		
		
		this.setBackground(Color.GREEN);
		this.setBorder(border);
		this.setLayout(new GridLayout(10,3));
		this.setPreferredSize(new Dimension(300,400));
		//.setLayout(new GridLayout(4,4));
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				getRootPane().setBackground(Color.CYAN);
			}
			
		});
		
	}
	Pomieszczenie(String nazwaPomieszczenia, int objetosc, int iloscDniWynajmu, Osoba osoba){
		this.nazwaPomieszczenia=nazwaPomieszczenia;
		this.objetosc=objetosc;
		this.iloscDniWynajmu=iloscDniWynajmu;
		pozostalaObjetosc = objetosc;
		this.osoba=osoba;
		this.wynajety=true;
		this.dataWynajmu = LocalDate.now();
		
		
		this.setBackground(Color.GREEN);
		this.setBorder(border);
		this.setLayout(new GridLayout(10,3));
		this.setPreferredSize(new Dimension(300,400));
		//.setLayout(new GridLayout(4,4));
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				getRootPane().setBackground(Color.CYAN);
			}
			
		});
		
	}
	public void setRemont() {
		aktywny=false;
		setBackground(Color.red);
		repaint();
	}
	
	public void addPrzedmiot(Przedmiot przedmiot) throws TooManyThingsException  {	
				if((pozostalaObjetosc > przedmiot.getRozmiar())) {
					przedmioty.add(przedmiot);
					pozostalaObjetosc = pozostalaObjetosc-przedmiot.getRozmiar();	
					add(przedmiot);
					przedmiot.setText(przedmiot.getNazwa());
					repaint();
					revalidate();
					System.out.println("Dodano do " + nazwaPomieszczenia);
					
				}	
				else {
					System.out.println("za malo miejsca w "+ getNazwaPomieszczenia());
						throw new TooManyThingsException("Za malo miejsca");
			}
	
		}/*
	public void przenies(Przedmiot przedmio) throws TooManyThingsException {
			if((pozostalaObjetosc > przedmio.getRozmiar())) {
				przedmioty.add(przedmio);
				pozostalaObjetosc = pozostalaObjetosc-przedmio.getRozmiar();	
				add(przedmio);
				przedmio.setText(przedmio.getNazwa());
				repaint();
				revalidate();
				System.out.println("Dodano do " + nazwaPomieszczenia);
				createPrzedmiot(przedmio.getNazwa(), przedmio.getRozmiar(), przedmio.getTyp());
				repaint();
			}

	}
	*/

	
	public void createPrzedmiot(String nazwa, int rozmiar, String typ) throws TooManyThingsException {
		Przedmiot przedm = new Przedmiot(nazwa, rozmiar, typ);
		addPrzedmiot(przedm);
	}

	public void removePrzedmiot(String przedmiot) {
		for(Przedmiot przed : przedmioty) {
			if(przed.getNazwa().equals(przedmiot)) {
				remove(przed);
				przedmioty.remove(przed);
				pozostalaObjetosc=pozostalaObjetosc+(przed.getRozmiar());
				repaint();
				revalidate();
			}
		}
	}

	public UUID getID() {
		return ID;
	}
	public String getPrzedmioty() {
		for(int i = 0; i<przedmioty.size(); i++) {
			if(przedmioty.get(i)!=null)
				przedmioty.get(i).getNazwa();
		}
		return przedmioty.toString();
	}

	public List<Przedmiot> getPrzedmioty3(){
		return przedmioty;
	}
	public void setOsoba(Osoba os) {
		if(aktywny==true) {
			osoba=os;
		}
	}
	public void setWybranePomieszczenie(boolean bool) {
		setBackground(Color.CYAN);
		wybranePomieszczenie=bool;
	}
	public Osoba getOsoba() {
		return osoba;
	}
	public int getObjetosc() {
		return objetosc;
	}
	public boolean getWynajety() {
		return wynajety;
	}
	public void setWynajety(boolean bool) {
		wynajety=bool;
	}
	public String getNazwaPomieszczenia() {
		return nazwaPomieszczenia;
	}
	public String getWybranePomieszczenie() {
		if(aktywny==true)
			return nazwaPomieszczenia;
		return null;
	}
	public boolean getAktywny() {
		return aktywny;
	}
	public void setAktywny(boolean bool) {
		aktywny=bool;
	}
	@Override
	public String toString() {
		return "ID: " + ID + "\r\n Nazwa pomieszczenia: " + nazwaPomieszczenia 
		+ "\r\n Wlasciciel: " + osoba
		+ "\r\n" + " Rozmiar: " + objetosc + "\r\n Pozostale miejsce: "
		+ pozostalaObjetosc + "\r\n" + " Data wynajmu: " + dataWynajmu
		+ "\r\n" + " Ilosc dni wynajmu: " + iloscDniWynajmu + 
		"\r\n" + " Przedmioty: \r\n" + getPrzedmioty().toString()+
		"\r\n";
	}

	
}




