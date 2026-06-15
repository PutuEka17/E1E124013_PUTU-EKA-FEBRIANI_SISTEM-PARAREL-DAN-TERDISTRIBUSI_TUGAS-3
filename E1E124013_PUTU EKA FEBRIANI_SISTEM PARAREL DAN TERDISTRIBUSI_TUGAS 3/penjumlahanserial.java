import java.util.Random;

public class penjumlahanserial {

    public static void main(String[] args) {

        // Ukuran array besar
        int ukuranArray = 10_000_000;

        // Membuat array
        int[] data = new int[ukuranArray];

        // Random angka
        Random random = new Random();

        System.out.println("========================================");
        System.out.println("   PENJUMLAHAN DATA BESAR - SERIAL");
        System.out.println("========================================");

        // Mengisi array dengan angka acak
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

        // Mulai waktu
        long startTime = System.nanoTime();

        // Penjumlahan serial
        long total = 0;

        for (int angka : data) {
            total += angka;
        }

        // Akhir waktu
        long endTime = System.nanoTime();

        // Durasi
        double durasi =
                (endTime - startTime) / 1_000_000.0;

        // Output hasil
        System.out.println("\n========== HASIL EKSEKUSI ==========");
        System.out.println("Metode               : SERIAL");
        System.out.println("Total Penjumlahan    : " + total);
        System.out.printf("Waktu Eksekusi       : %.3f ms%n", durasi);
        System.out.println("====================================");
    }
}