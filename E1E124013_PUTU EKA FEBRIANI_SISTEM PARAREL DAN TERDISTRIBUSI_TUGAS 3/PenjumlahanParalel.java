import java.util.Random;

class ThreadPenjumlah extends Thread {

    private int[] data;
    private int start;
    private int end;
    private long hasil;

    public ThreadPenjumlah(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {

        hasil = 0;

        for (int i = start; i < end; i++) {
            hasil += data[i];
        }
    }

    public long getHasil() {
        return hasil;
    }
}

public class PenjumlahanParalel {

    public static void main(String[] args)
            throws InterruptedException {

        // Ukuran array besar
        int ukuranArray = 10_000_000;

        int[] data = new int[ukuranArray];

        Random random = new Random();

        System.out.println("========================================");
        System.out.println("   PENJUMLAHAN DATA BESAR - PARALEL");
        System.out.println("========================================");

        // Isi data random
        System.out.println("Mengisi array dengan angka acak...");

        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100);
        }

        System.out.println("Jumlah elemen array : " + ukuranArray);

        // Menampilkan sampel data
        System.out.println("\n========== DATA ACAK (20 DATA AWAL) ==========");

        for (int i = 0; i < 20; i++) {
            System.out.print(data[i] + " ");
        }

        System.out.println();

        // Jumlah thread
        int jumlahThread = 4;

        System.out.println("\nJumlah thread       : "
                + jumlahThread);

        int chunkSize = ukuranArray / jumlahThread;

        ThreadPenjumlah[] threads =
                new ThreadPenjumlah[jumlahThread];

        // Mulai waktu
        long startTime = System.nanoTime();

        // Membagi pekerjaan
        for (int i = 0; i < jumlahThread; i++) {

            int start = i * chunkSize;
            int end;

            if (i == jumlahThread - 1) {
                end = ukuranArray;
            } else {
                end = start + chunkSize;
            }

            threads[i] =
                    new ThreadPenjumlah(data, start, end);

            threads[i].start();
        }

        // Tunggu thread selesai
        for (ThreadPenjumlah thread : threads) {
            thread.join();
        }

        // Gabungkan hasil
        long total = 0;

        for (ThreadPenjumlah thread : threads) {
            total += thread.getHasil();
        }

        // Akhir waktu
        long endTime = System.nanoTime();

        double durasi =
                (endTime - startTime) / 1_000_000.0;

        // Output hasil
        System.out.println("\n========== HASIL EKSEKUSI ==========");
        System.out.println("Metode               : PARALEL");
        System.out.println("Jumlah Thread        : "
                + jumlahThread);
        System.out.println("Total Penjumlahan    : "
                + total);
        System.out.printf("Waktu Eksekusi       : %.3f ms%n",
                durasi);
        System.out.println("====================================");
    }
}