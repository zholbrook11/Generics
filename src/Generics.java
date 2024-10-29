import java.util.Random;
import java.util.Scanner;

public class Generics<T extends Comparable<T>> {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter the size of random array to generate:");
        int arrSize = scnr.nextInt();

        System.out.println("Choose the type of array to generate: (1) Integer, (2) Double");
        int typeChoice = scnr.nextInt();

        Generics<Integer> integerSorter = new Generics<>();
        Generics<Double> doubleSorter = new Generics<>();

        if (typeChoice == 1) {
            Integer[] randArray = createRandomArray(Integer.class, arrSize);
            if (randArray.length <= 20){
                System.out.println("Integer array generated:");
                printArray(randArray);
            }
            runSort(scnr, integerSorter, randArray);
        } else if (typeChoice == 2) {
            Double[] randArray = createRandomArray(Double.class, arrSize);
            if (randArray.length <= 20){
                System.out.println("Double array generated:");
                printArray(randArray);
            }
            runSort(scnr, doubleSorter, randArray);
        } else {
            System.out.println("Invalid option.");
        }
        scnr.close();
    }

    private static <T extends Comparable<T>> void runSort(Scanner scnr, Generics<T> sorter, T[] randArray) {
        System.out.println("Would you like to use BubbleSort (type 'b') or MergeSort (type 'm')?");
        String userChoice = scnr.next();

        System.out.println("Array before sorting:");
        printArray(randArray);

        long start = System.currentTimeMillis();

        if (userChoice.equalsIgnoreCase("b")) {
            sorter.bubbleSort(randArray);
        } else if (userChoice.equalsIgnoreCase("m")) {
            sorter.mergeSort(randArray, 0, randArray.length - 1);
        } else {
            System.out.println("Invalid sorting option.");
            return;
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");

        System.out.println("Array after sorting:");
        printArray(randArray);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; ++i)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            rightArr[j] = arr[mid + 1 + j];


        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    private void merge(T[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        T[] leftArr = (T[]) new Comparable[n1];
        T[] rightArr = (T[]) new Comparable[n2];

        for (int i = 0; i < n1; ++i)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public void bubbleSort(T[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i=0; i < array.length - 1; i++){
            if (array[i] > array[i+1]){
                return false;
            }     
        }
        return true;
    }

    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = rand.nextInt(101);
        }
        return randomArray;
    } 

    public static <T> T[] createRandomArray(Class<T> type, int arrayLength) {
        Random rand = new Random();
        T[] randomArray = (T[]) java.lang.reflect.Array.newInstance(type, arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            if (type == Integer.class) {
                randomArray[i] = type.cast(rand.nextInt(101));
            } else if (type == Double.class) {
                randomArray[i] = type.cast(rand.nextDouble() * 100);
            }
        }
        return randomArray;
    }
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void printArray(int[] array){
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}