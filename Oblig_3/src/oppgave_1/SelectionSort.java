package oppgave_1;

public class SelectionSort {
	public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
//Her har vi en sortert del og en usortert del. 
//Vi plukker konsekvent det laveste element i usortert del og plasserer det fremst i usortert del.
//Deretter utvides sortert del med 1 indeks, slik at den nå inkluderer det nylig plasserte elementet.
		
		
		for (int i =0;i<arr.length-1;i++) {
			int indeksMinsteEle=i;
			for (int j =i+1;j<arr.length;j++) {
				if (arr[j].compareTo(arr[indeksMinsteEle])<0)
					indeksMinsteEle = j;
			}
			if (indeksMinsteEle !=i) byttOm(arr,indeksMinsteEle,i);
		}
	}
	private static <T extends Comparable<? super T>> void byttOm(T[] arr, int indeks1, int indeks2) {
		T temp = arr[indeks1];
		arr[indeks1] = arr[indeks2];
		arr[indeks2] = temp;
	}
}
