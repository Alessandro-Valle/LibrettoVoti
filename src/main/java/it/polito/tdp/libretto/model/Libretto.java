package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Libretto {
	
	private List<Voto> voti;

	public Libretto() {
		super();
		this.voti = new ArrayList<Voto>();
	}
	
	/**
	 * Aggiungi un nuovo voto al libretto
	 * (per ora non fa nessun controllo)
	 * @param v è il voto da aggiungere
	 * @return true
	 */
	
	public boolean add(Voto v) {
		if(this.esisteVotoDuplicato(v)|| this.esisteVotoConflitto(v))
			throw new IllegalArgumentException("Voto errato: " + v);
		return this.voti.add(v);		
	}
	
	public void stampa() {
		for (Voto v : this.voti)
			System.out.println(v.toString());
	}
	
	public void stampaPuntiUguali(int valore) {
		for (Voto v : this.voti)
			if(v.getPunti() == valore)
				System.out.println(v.toString());
	}

	public Voto cercaVotoPerNome(String corso) {
		for (Voto v : this.voti)
			if(v.getCorso().equals(corso))
				return v;
		return null;
	}
	
	public boolean esisteVoto(Voto nuovo) {
		for (Voto v : this.voti)
			if(v.equalsCorsoPunti(nuovo))
				return true;
		return false;
	}
	
	public boolean esisteVotoDuplicato(Voto nuovo) {
		for(Voto v : this.voti)
			if(v.isDuplicato(nuovo))
				return true;
		return false;
	}
	
	public boolean esisteVotoConflitto(Voto nuovo) {
		for(Voto v : this.voti)
			if(v.isConflitto(nuovo))
				return true;
		return false;
	}
	
	/**
	 * Metodo 'factory' per creare un nuovo libretto con i voti migliorati
	 * @return 
	 */
	
	
	public Libretto librettoMigliorato() {
		
		Libretto migliore = new Libretto();
		migliore.voti = new ArrayList<>();   // Creco un clone della lista this.voti    MA HO CREATO UN CLONE SUPERFICIALE
		
		for(Voto v : this.voti)
			migliore.voti.add(v.clone());           // DEEP COPY     (meglio evitarle se si può)
			//migliore.voti.add(new Voto(v));		
//		 UN altro metodo è quello di inserire un altro costruttore che prenda come parametro un oggetto di tipo Voto 
				
		for(Voto v: migliore.voti) {
			v.setPunti(v.getPunti() + 2);
		}
		return migliore;
	}
	
	
	public void cancellaVotiInferiori(int punti) {
		
		List<Voto> daCancellare = new ArrayList <Voto>();
		
		for(Voto v : this.voti) {
			if(v.getPunti() < punti) {
				daCancellare.add(v);
			}
		}
		this.voti.removeAll(daCancellare);
	}
	
	public Libretto librettoOrdinatoAlfabeitcamente() {
		
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>();
		
		for(Voto v : this.voti)
			ordinato.voti.add(v);
		
		ordinato.voti.sort(new ComparatorByName());
		Collections.sort(ordinato.voti, new ComparatorByName());
		
		return ordinato;
	}
	
	public Libretto librettoOrdinatoPerVoto() {
		
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>();
		
		for(Voto v : this.voti)
			ordinato.voti.add(v);
		
		ordinato.voti.sort(new Comparator<Voto>() { 
			@Override			
			public int compare(Voto o1, Voto o2) {
				return o2.getPunti()-o1.getPunti();   //Ordinamento decrescente
			}
		});
		
		return ordinato;
	}
	
	public String toString() {
		return this.voti.toString();
	}



	
	
	
	
	
	
	
	
}
