package oppgave_1;

public class InsertionSort {
	/**
	 * 'Standard' insertion sort implementasjon
	 * 
	 * @param <T>
	 * @param arr - arrayen som skal sorteres
	 */
	public static <T extends Comparable<? super T>> void insertionSortSTD(T[] arr) {
		T temp = null;
		int bunnIndeks = 0;
		int toppIndeks = arr.length-1;
//Vi kan betrakte indeks 1 som første element i usortert del, siden sortert del bare består av ett element og da er sortert
		for (int i = bunnIndeks+1; i <= toppIndeks; i++) {
			int j = i - 1;
			temp = arr[i];
			while (j >= bunnIndeks && temp.compareTo(arr[j]) < 0) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
	}
/**
 * Insertion sort der minste element gis riktig plass først
 * @param <T>
 * @param arr - arrayen som skal sorteres
 */
	public static <T extends Comparable<? super T>> void insertionSortModifisert(T[] arr) {

		int bunnIndeks = 0;
		int toppIndeks = arr.length-1;

// Først finner vi posisjon til arrayens minste element, så bytter vi denne med element på indeks 0
		int minsteIndeks = finnMinsteIndeks(arr, bunnIndeks, toppIndeks);
		byttOm(arr, 0, minsteIndeks);

//Element på indeks 2 blir da første element i usortert del
		for (int i = bunnIndeks + 2; i < toppIndeks; i++) {
			T aktuell = arr[i];
			int størsteIndeksSortertDel = i - 1;
//While loopen kan nå forenkles, siden aktuell.compareto(størsteindekssortertdel) er garantert å returnere >=0.
			while (aktuell.compareTo(arr[størsteIndeksSortertDel]) < 0) {
				arr[størsteIndeksSortertDel + 1] = arr[størsteIndeksSortertDel];
				størsteIndeksSortertDel--;
			}
				
			arr[størsteIndeksSortertDel+1] = aktuell;
		}
	}
/**
 * Insertion sort implementasjon som angriper to element om gangen.
 * @param <T>
 * @param arr - arrayen som skal sorteres
 */
	public static <T extends Comparable<? super T>> void insertionSortToISlengen(T[] arr) {
		insertionSortToISlengen(arr,0,arr.length-1);
	}
/**
 * Hjelpemetode til InsertionSortToISlengen. Denne metoden utfører den faktiske sorteringen.
 * @param <T>
 * @param arr
 * @param bunnIndeks
 * @param toppIndeks
 */
	private static <T extends Comparable<? super T>> void insertionSortToISlengen(T[] arr, int bunnIndeks,
			int toppIndeks) {

//Vi begynner med å plassere det minste elementet på indeks 0, slik at vi senere kan ha en indre løkke med kun en betingelse
//Etter kodeblokk nedefor er kjørt, så vil sortert del bestå av element 0 og 1, og usortert fra og med 2.
		int minsteIndeks = finnMinsteIndeks(arr, bunnIndeks, toppIndeks);
		if (minsteIndeks != bunnIndeks) 
			byttOm(arr, bunnIndeks, minsteIndeks);
		
			
		
//Det er ønskelig at tabellens usorterte del består av ett partall antall elementer.
// Vi løser dette med å sortere ned enda ett element hvis usortert del har oddetall antall element.
		if ((bunnIndeks+2 + toppIndeks) % 2 == 0) {// oddetall antall element hvis true
			minsteIndeks = finnMinsteIndeks(arr, bunnIndeks, toppIndeks);
			if (minsteIndeks != bunnIndeks)
				byttOm(arr, bunnIndeks, minsteIndeks);
			insertionSortToISlengen(arr, bunnIndeks +1, toppIndeks); //Rekursivt kall der ny bunnindeks blir 1
		}

//Når vi kommer hit, så har usortert del ett partall antall element.
//Hvis vi havnet her etter ett rekursivt kall, så er bunnindeks lik 1.
		else {
			for (int i = bunnIndeks+2; i < toppIndeks; i = i + 2) {
				int indeksStorsteElement;
				int indeksMinsteElement;
				if (arr[i].compareTo(arr[i + 1]) > 0) {
					indeksStorsteElement = i;
					indeksMinsteElement = i + 1;
				} else {
					indeksStorsteElement = i + 1;
					indeksMinsteElement = i;
				}
				int storstIndeksSortertDel = i - 1;
				plasser(arr, indeksMinsteElement, indeksStorsteElement, storstIndeksSortertDel);
			}
		}
	}

	
/**
 * Hjelpemetode som finner indeksen til det minste elementet på ett intervall i en array.
 * @param <T>
 * @param arr - arrayen som skal sjekkes
 * @param bunnIndeks - laveste indeks som skal søkes i
 * @param toppIndeks - Høyeste indeks som skal søkes i
 * @return - indeksen til det minste elementet fra og med bunnIndeks til og med Toppindeks
 */
	private static <T extends Comparable<? super T>> int finnMinsteIndeks(T[] arr, int bunnIndeks, int toppIndeks) {
		int minsteIndeks = bunnIndeks;
		for (int i = bunnIndeks + 1; i <= toppIndeks; i++)
			if (arr[i].compareTo(arr[minsteIndeks]) < 0)
				minsteIndeks = i;
		return minsteIndeks;
	}
/**
 * Hjelpemetode som bytter om posisjon på to element i en array.
 * @param <T>
 * @param arr - arrayen som elementene ligger i
 * @param indeks1 - indeks til første element
 * @param indeks2 - indeks til andre element
 */
	private static <T extends Comparable<? super T>> void byttOm(T[] arr, int indeks1, int indeks2) {
		T temp = arr[indeks1];
		arr[indeks1] = arr[indeks2];
		arr[indeks2] = temp;
	}
/**
 * Hjelpemetode som sorterer inn to element på riktig posisjon i sortert del av en array
 * @param <T>
 * @param arr - arrayen det arbeides på
 * @param minsteIndeks - indeksen til det minste av elementene som skal sorteres inn
 * @param storsteIndeks - indeksen til det største elementet som 
 * @param bunnIndeks - den minste indeksen i arrayens sorterte del
 * @param storstIndeksSortertDel - Den største indeksen i arrayens sorterte del 
 */
	private static <T extends Comparable<? super T>> void plasser(T[] arr, int minsteIndeks, int storsteIndeks,
			int storstIndeksSortertDel) {
		T minste = arr[minsteIndeks];
		T storste = arr[storsteIndeks];
		
		while (storste.compareTo(arr[storstIndeksSortertDel]) < 0) {
			arr[storstIndeksSortertDel + 2] = arr[storstIndeksSortertDel];
			storstIndeksSortertDel--;
		}
		arr[storstIndeksSortertDel + 2] = storste;
		while (minste.compareTo(arr[storstIndeksSortertDel]) < 0) {
			arr[storstIndeksSortertDel + 1] = arr[storstIndeksSortertDel];
			storstIndeksSortertDel--;
		}
		arr[storstIndeksSortertDel + 1] = minste;
	}
}
