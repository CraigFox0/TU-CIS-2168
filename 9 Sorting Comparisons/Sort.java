import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Sort {

    public static void main(String... args) {
        try {
            File timeFile = new File("time.csv");
            timeFile.createNewFile();
            File comparisonsFile = new File("comparisons.csv");
            comparisonsFile.createNewFile();
            File exchangesFile = new File("exchanges.csv");
            exchangesFile.createNewFile();
            FileWriter timeFileWriter = new FileWriter("time.csv", true);
            timeFileWriter.append(", InsertionSort, Quicksort, Shellsort" + System.getProperty("line.separator"));
            timeFileWriter.close();
            FileWriter comparisonsFileWriter = new FileWriter("comparisons.csv", true);
            comparisonsFileWriter.append(", InsertionSort, Quicksort, Shellsort" + System.getProperty("line.separator"));
            comparisonsFileWriter.close();
            FileWriter exchangesFileWriter = new FileWriter("exchanges.csv", true);
            exchangesFileWriter.append(", InsertionSort, Quicksort, Shellsort" + System.getProperty("line.separator"));
            exchangesFileWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int i = 10; i <= 10000; i*=2) {
            int[] a, b, c;
            a = generateRandomIntArray(i);
            b = a.clone();
            c = a.clone();
            long startTime = System.nanoTime();
            int[] insertResults = testInsertionSort(a);
            long insertTime = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            int[] quickResults = testQuickSort(b, 0, b.length-1);
            long quickTime = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            int[] shellResults = testShellSort(c);
            long shellTime = System.nanoTime() - startTime;

            try {
                FileWriter timeFileWriter = new FileWriter("time.csv", true);
                timeFileWriter.append(i + ", " + insertTime + ", " + quickTime + ", " + shellTime + System.getProperty("line.separator"));
                timeFileWriter.close();
                FileWriter comparisonsFileWriter = new FileWriter("comparisons.csv", true);
                comparisonsFileWriter.append(i + ", " + insertResults[0] + ", " + quickResults[0] + ", " + shellResults[0] + System.getProperty("line.separator"));
                comparisonsFileWriter.close();
                FileWriter exchangesFileWriter = new FileWriter("exchanges.csv", true);
                exchangesFileWriter.append(i + ", " + insertResults[1] + ", " + quickResults[1] + ", " + shellResults[1] + System.getProperty("line.separator"));
                exchangesFileWriter.close();
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static int[] generateRandomIntArray(int n){
        int[] list = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list[i] = random.nextInt(10000);
        }
        return list;
    }

    //Sort algorithms based off of those from https://www.geeksforgeeks.org/

    public static int[] testInsertionSort(int[] arr) {
        int compares = 0;
        int exchanges = 0;
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                compares++;
                exchanges++;
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            exchanges++;
            arr[j + 1] = key;
        }
        return new int[]{compares, exchanges};
    }

    public static int[] testQuickSort(int[] arr, int low, int high) {
        int compares = 0;
        int exchanges = 0;
        compares++;
        if (low < high) {
            int[] a = partition(arr, low, high);
            int pi = a[0];
            compares += a[1];
            exchanges += a[2];
            int[] x = testQuickSort(arr, low, pi-1);
            compares += x[0];
            exchanges += x[1];
            int[] y = testQuickSort(arr, pi+1, high);
            compares += y[0];
            exchanges += y[1];
        }
        return new int[]{compares, exchanges};
    }

    public static int[] partition(int[] arr, int low, int high) {
        int compares = 0;
        int exchanges = 0;
        int pivot = arr[high];
        int i = (low-1);
        for (int j=low; j < high; j++) {
            compares++;
            if (arr[j] < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                exchanges += 2;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        exchanges += 2;

        return new int[]{i + 1, compares, exchanges};
    }

    public static int[] testShellSort(int[] arr) {
        int compares = 0;
        int exchanges = 0;
        int n = arr.length;

        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    compares++;
                    exchanges++;
                    arr[j] = arr[j - gap];
                }
                exchanges++;
                arr[j] = temp;
            }
        }
        return new int[]{compares, exchanges};
    }

}