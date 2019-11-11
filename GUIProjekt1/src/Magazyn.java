import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Magazyn extends JFrame implements ActionListener{
	//public HashMap<Pomieszczenie, Osoba> Przynaleznosc = new HashMap<>();
	JPanel warehouse = new JPanel();
	JTextField textPanel = new JTextField();
	JPanel przedmiotyPanel = new JPanel();
	JPanel osobyPanel = new JPanel();
	Osoba wybranaOsoba;
	Pomieszczenie chosenPomieszczenie;
	Pomieszczenie przenoszeniePomieszczenie;
	public List<Osoba> Osoby = new ArrayList<Osoba>();
	public static List<Pomieszczenie> Warehouse = new ArrayList<Pomieszczenie>();
	
	
	
	Magazyn(){
		this.setLayout(new BorderLayout());
		this.add(warehouse, BorderLayout.CENTER);
		this.add(textPanel, BorderLayout.SOUTH);
		this.add(osobyPanel, BorderLayout.NORTH);
		warehouse.setLayout(new GridLayout(2,2,2,2));
		osobyPanel.setLayout(new GridLayout(1,10));
		osobyPanel.setPreferredSize(new Dimension(50,50));
		textPanel.setPreferredSize(new Dimension(50,50));
		this.add(przedmiotyPanel, BorderLayout.EAST);
		przedmiotyPanel.setLayout(new GridLayout(10,1));
		
		
		

		textPanel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] str = textPanel.getText().split("\\s");
				//String s = "exit";
				if(str[0].equals("exit")) {
					System.exit(0);
				}
				if(str[0].equals("save")) {
					try {
						writeToFile();
						textPanel.setText("");
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(str[0].equals("WybierzOsobe")) {
					for(Osoba osob : Osoby) {
						osob.setWybrany(false);
						osob.setBackground(Color.GRAY);
						if(osob.getNazwisko().equals(str[1])) {
							osob.setWybrany(true);
							wybranaOsoba=osob;
							textPanel.setText("");
							System.out.println("Wybrano: "+osob.getNazwisko());
						}
					}
				}				
					if(str[0].equals("Wynajmij")){
						if(chosenPomieszczenie.getWynajety()==false) {
							wybranaOsoba.ownedPomieszczenie.add(chosenPomieszczenie);
							textPanel.setText("");
							chosenPomieszczenie.osoba = wybranaOsoba;
							chosenPomieszczenie.dataWynajmu=LocalDate.now();
							chosenPomieszczenie.setWynajety(true);
						}
						else
							System.out.println("Nie mozna wynajac pomieszczenia");
					}
					
				
					if(str[0].equals("WybierzPomieszczenie")) {
						for(Pomieszczenie pomie : Warehouse) {
							if(pomie.aktywny==true) {
								pomie.setBackground(Color.GREEN);
								if(pomie.getNazwaPomieszczenia().equals(str[1])) {
									chosenPomieszczenie = pomie;
									pomie.setWybranePomieszczenie(true);
									textPanel.setText("");
								}
							}
						}

						System.out.println("Wybrano: "+chosenPomieszczenie.getNazwaPomieszczenia());
					}
					if(str[0].equals("OsobaInfo")) {
						textPanel.setText(wybranaOsoba.toString());
						System.out.println(wybranaOsoba.toString());
						
					}
					if(str[0].equals("PomieszczenieInfo")) {
						textPanel.setText(chosenPomieszczenie.toString());
						System.out.println(chosenPomieszczenie.toString());
					}
					if(str[0].equals("Wloz")) {
						try {
							chosenPomieszczenie.createPrzedmiot(str[1], Integer.parseInt(str[2]), str[3]);
							textPanel.setText("");
						} catch (NumberFormatException | TooManyThingsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Wlozono " + str[1]);
						
					}
					if(str[0].equals("Wyjmij")) {
						for(Przedmiot przed : chosenPomieszczenie.przedmioty) {
							if(przed.getNazwa().equals(str[1]))
								chosenPomieszczenie.removePrzedmiot(str[1]);
							
						}
						
			
						/*
							if(chosenPomieszczenie.przedmioty.getNazwa().equals(przedmiot)) {
								remove(przed);
								przedmioty.remove(przed);
								
								pozostalaObjetosc=pozostalaObjetosc+(przed.getRozmiar());
								repaint();
								revalidate();
							}
						*/
						//chosenPomieszczenie.removePrzedmiot(str[1]);
						textPanel.setText("");
						System.out.println("Wyjeto " + str[1]);
						
						repaint();
						revalidate();

					}
					if(str[0].equals("Remont")) {
						for(int i = 0; i<Warehouse.size(); i++) {
							if((Warehouse.get(i).getAktywny()==true) && (Warehouse.get(i).getWynajety())
							&& (Warehouse.get(i).getOsoba().getNazwisko() == (chosenPomieszczenie.getOsoba()).getNazwisko())){
								przenoszeniePomieszczenie = Warehouse.get(i);
								System.out.println(przenoszeniePomieszczenie.getNazwaPomieszczenia());
								
								
								for(int j = 0; j<chosenPomieszczenie.przedmioty.size(); j++) {
									
									try {
										przenoszeniePomieszczenie.addPrzedmiot(chosenPomieszczenie.przedmioty.get(j));
										
									} catch (TooManyThingsException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										}
									chosenPomieszczenie.przedmioty.remove(chosenPomieszczenie.przedmioty.get(j));
									}
								chosenPomieszczenie.setRemont();
							/*
					
								for(Przedmiot przed : chosenPomieszczenie.przedmioty) {
									przenoszeniePomieszczenie.przedmioty.add(przed);
									Przedmiot pButton = new Przedmiot(przed.getNazwa(), przed.getRozmiar(), przed.getTyp());
									przenoszeniePomieszczenie.add(pButton);
									pButton.setText(przed.getNazwa());
									//przenoszeniePomieszczenie.createPrzedmiot(przed.getNazwa(), przed.getRozmiar(), przed.getTyp());
									try {
										przenoszeniePomieszczenie.addPrzedmiot(przed);
									} catch (TooManyThingsException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									repaint();
									revalidate();
								}
								
									System.out.println(Warehouse.get(i).getNazwaPomieszczenia());
									System.out.println(chosenPomieszczenie.getNazwaPomieszczenia());
								*/
							}
						}
						przenoszeniePomieszczenie = null;
						
						chosenPomieszczenie = null;
						
						revalidate();
						textPanel.setText("");
						repaint();
						
					}
					if(str[0].equals("Przenies")) {
						//System.out.println(przenoszeniePomieszczenie.getNazwaPomieszczenia());
						for(int i = 0; i<chosenPomieszczenie.przedmioty.size(); i++) {
								
							try {
								przenoszeniePomieszczenie.addPrzedmiot(chosenPomieszczenie.przedmioty.get(i));
								
							} catch (TooManyThingsException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								}
							chosenPomieszczenie.przedmioty.remove(chosenPomieszczenie.przedmioty.get(i));
							}
						przenoszeniePomieszczenie = null;
						repaint();
						revalidate();
						textPanel.setText("");
						/*
						for(Przedmiot przedmiot : chosenPomieszczenie.przedmioty) {
							if((przenoszeniePomieszczenie.pozostalaObjetosc > przedmiot.getRozmiar())) {
								przenoszeniePomieszczenie.przedmioty.add(przedmiot);
								Przedmiot ppButton = new Przedmiot(przedmiot.getNazwa(), przedmiot.getRozmiar(), przedmiot.getTyp());
								przenoszeniePomieszczenie.pozostalaObjetosc = przenoszeniePomieszczenie.pozostalaObjetosc-przedmiot.getRozmiar();	
								add(ppButton);
								ppButton.setText(przedmiot.getNazwa());
								System.out.println("Dodano do " + przenoszeniePomieszczenie.nazwaPomieszczenia);
								try {
									przenoszeniePomieszczenie.createPrzedmiot(przedmiot.getNazwa(), przedmiot.getRozmiar(), przedmiot.getTyp());
								} catch (TooManyThingsException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								repaint();
								revalidate();
					
							}
						}*/
					}
					if(str[0].equals("Aktualizuj")) {
						System.out.println("Aktualizuje: " +wybranaOsoba.getNazwisko());
						wybranaOsoba.aktualizujOsobe(str[1], str[2], Integer.parseInt(str[3]), str[4]);
						
						textPanel.setText("");
					}
				}
			}
		
			
		);

		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public String getOsobyNames() {
		for(Osoba oso : Osoby) {
			String osoNaz = oso.getNazwisko();
			return osoNaz;
		}
		return null;
	}
	public static void writeToFile() throws IOException{
		String file = "stanMagazynu.txt";
		FileWriter fw = new FileWriter(file);
		BufferedWriter bs = new BufferedWriter(fw);
		String content = null;
			for(Pomieszczenie przy : Warehouse) {
				content = przy.toString();
				bs.write("\n " + content + " " + "\r\n");
				bs.newLine();
			}
			bs.close();
			System.out.println("done");

	}
	

		public void addPomieszczenie(Pomieszczenie pomieszczenie) {
			warehouse.add(pomieszczenie);
			Warehouse.add(pomieszczenie);
	}
	public void addOsoba(Osoba osoba) {
		Osoby.add(osoba);
		osobyPanel.add(osoba);
		osoba.setText(osoba.getNazwisko());
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	
}


