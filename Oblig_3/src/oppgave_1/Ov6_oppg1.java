package oppgave_1;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;


public class Ov6_oppg1 {
	public static void main(String[] args) {
		int nEle = 50000;
		int maxSize = nEle;
		Integer[] arr =lagTabellStigende(nEle, maxSize);
		Integer[] kopi;
		Instant foer;
		long kjoeretid;

		
		kopi = arr.clone();
		foer = Instant.now();
		InsertionSort.insertionSortSTD(kopi);
		kjoeretid = Duration.between(foer, Instant.now()).toMillis();
		System.out.println(
				"'insertionSortSTD()' brukte " + kjoeretid + " millisekunder på å sortere " + nEle + " elementer.");
		sjekkTabell(kopi);
		

		kopi = arr.clone();
		foer = Instant.now();
		InsertionSort.insertionSortModifisert(kopi);
		kjoeretid = Duration.between(foer, Instant.now()).toMillis();
		System.out.println(
				"'insertionSortModifisert()' brukte " + kjoeretid + " millisekunder på å sortere " + nEle + " elementer.");
		sjekkTabell(kopi);
		
		kopi = arr.clone();
		foer = Instant.now();
		InsertionSort.insertionSortToISlengen(kopi);
		kjoeretid = Duration.between(foer, Instant.now()).toMillis();
		System.out.println("'insertionSortToISlengen()' brukte " + kjoeretid + " millisekunder på å sortere " + nEle
				+ " elementer.");
		sjekkTabell(kopi);
		
		kopi = arr.clone();
		foer = Instant.now();
		SelectionSort.selectionSort(kopi);
		kjoeretid = Duration.between(foer, Instant.now()).toMillis();
		System.out.println("'selectionSort()' brukte " + kjoeretid + " millisekunder på å sortere " + nEle
				+ " elementer.");
		sjekkTabell(kopi);
		
	}
	private static Integer[] lagTabell(int nEle, int maxEle) {
		Random rand = new Random();
		Integer[] arr = new Integer[nEle];
		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(maxEle);
		return arr;
	}
	
	private static Integer[] lagTabellStigende(int nEle, int maxEle) {
		Integer [] arr = new Integer[nEle];
		for (int i=0;i<arr.length;i++)
			arr[i]=i;
		return arr;
	}
	
	private static Integer[] lagTabellAvtagende(int nEle, int maxEle) {
		Integer [] arr = new Integer[nEle];
		int topp = arr.length-1;
		for (int i=0;i<arr.length;i++) {
			arr[i]=topp;
			topp--;
		}
			
		return arr;
	}
	
	private static void sjekkTabell(Integer[] arr) {
		boolean sortert = true;
		for (int i = 0;i<arr.length-2;i++) 
			if (arr[i]>arr[i+1]) {
				System.out.println("Tabellen er ikke sortert. Element '"+arr[i]+"' på indeks "+i+" er større enn element '"+arr[i+1]+"' på posisjon "+(i+1));
				sortert = false;
			}
				
		if (sortert) System.out.println("Tabellen er korrekt sortert");
	}

}
