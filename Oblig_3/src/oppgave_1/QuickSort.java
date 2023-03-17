package oppgave_1;

public class QuickSort {

	public static void main(String[] args) {
		Integer[] arr = {8,3,5,6,4,7,2,3};
		quickSort(arr,0,arr.length-1);
		for (Integer i:arr)
			System.out.println(i);
	}

	public static <T extends Comparable<? super T>> void quickSort(T[] arr, int bunnIndeks, int toppIndeks) {
		if (bunnIndeks>=toppIndeks) {
			return;
		}
		preparer(arr, bunnIndeks, toppIndeks);
		int pivotIndeks = partisjoner(arr, bunnIndeks, toppIndeks);
		
		quickSort(arr,bunnIndeks,pivotIndeks-1);
		quickSort(arr,pivotIndeks+1,toppIndeks);
		
	}

	private static <T extends Comparable<? super T>> void preparer(T[] arr, int bunnIndeks, int toppIndeks) {
		int midtIndeks = (bunnIndeks + toppIndeks) / 2;

//sorterer det nederste, midterste og øverste element.
		if (arr[bunnIndeks].compareTo(arr[midtIndeks]) > 0)
			byttOm(arr, bunnIndeks, midtIndeks);

		if (arr[midtIndeks].compareTo(arr[toppIndeks]) > 0)
			byttOm(arr, midtIndeks, toppIndeks);

		if (arr[bunnIndeks].compareTo(arr[midtIndeks]) > 0)
			byttOm(arr, bunnIndeks, midtIndeks);
//Det midterste element nå medianen av de tre, og vil tjene som pivotelement
//Vi 'hyllelegger' pivotelement på nest siste posisjon.
		byttOm(arr, midtIndeks, toppIndeks - 1);
//Tabellen er nå klar for neste steg
	}

	private static <T extends Comparable<? super T>> int partisjoner(T[] arr, int bunnIndeks, int toppIndeks) {
//reminder, pivotelementet befinner seg på posisjon toppIndeks-1
//I tillegg til dette så vet vi at element på posisjon bunnIndeks og toppIndeks ligger på riktig side av pivot.
		int venstre = bunnIndeks+1;
		int høyre = toppIndeks -1;
		while (venstre<høyre) {
			while (arr[venstre].compareTo(arr[toppIndeks-1])<=0&&venstre<høyre)
				venstre++;
			while (arr[høyre].compareTo(arr[toppIndeks-1])>=0&&venstre<høyre)
				høyre--;
			if (venstre<høyre) {
				byttOm(arr,venstre,høyre);
				venstre++;
				høyre--;
			}
				
		}
//Vi setter pivotelementet tilbake der det hører hjemme - på venstre sin plass
		byttOm(arr,toppIndeks-1,venstre);
		return venstre;
		
		
		
	}
	
	private static <T extends Comparable<? super T>> void byttOm(T[] arr, int indeks1, int indeks2) {
		T temp = arr[indeks1];
		arr[indeks1] = arr[indeks2];
		arr[indeks2] = temp;
	}
}
