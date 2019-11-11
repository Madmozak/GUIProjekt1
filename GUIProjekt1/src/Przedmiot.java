import javax.swing.JButton;

@SuppressWarnings("serial")
class Przedmiot extends JButton{
	public String nazwa;
	int rozmiar;
	String typ;
	
	Przedmiot(String nazwa, int rozmiar, String typ){
		this.nazwa=nazwa;
		this.rozmiar=rozmiar;
		this.typ=typ;
		
		
		}
	
	public String getNazwa() {
		return nazwa;
	}
	public int getRozmiar() {
		return rozmiar;
	}
	public String getTyp() {
		return typ;
	}
	@Override
	public String toString() {
		return nazwa;
	}
	public String getNameTyp() {
		return nazwa +"("+typ+")";
	}
	
}