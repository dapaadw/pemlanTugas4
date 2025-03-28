package Semester_2.Praktikum.Swalayan;

import java.util.Scanner;

public class Swalayan {

    // Array untuk menyimpan data pelanggan
    private static Pelanggan[] pelanggan = {
            new Pelanggan("Puja", 100000, "38123", "1111"),
            new Pelanggan("Ajes", 200000, "56123", "2222"),
            new Pelanggan("Rajistha", 300000, "74123", "3333")
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SWALAYAN TINY ---");
            System.out.println("1. Top-Up");
            System.out.println("2. Pembelian");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan anda: ");
            int pil = sc.nextInt();
            sc.nextLine();

            if (pil == 3) {
                break;
            }

            System.out.print("Masukkan nomor pelanggan: ");
            String nomor = sc.nextLine();

            Pelanggan pel = cariPelanggan(nomor);
            
            // Cek apakah pelanggan ditemukan
            if (pel == null) {
                System.out.println("Nomor pelanggan tidak ditemukan. Coba lagi.");
                continue;
            }

            // Cek apakah akun diblokir
            if (pel.isDiblokir()) {
                System.out.println("Akun ini diblokir.");
                continue;  
            }

            // Looping autentifikasi PIN (maksimal 3 kali)
            boolean auten = false;
            for (int i = 0; i < 3; i++) {
                System.out.print("Masukkan PIN: ");
                String pin = sc.nextLine();
                
                if (pel.autentifikasi(pin)) {
                    auten = true;
                    break;
                }
            }

            // Jika autentifikasi gagal setelah 3 kali percobaan, blokir akun
            if (!auten) {
                continue;
            }

            if (pil == 1) {
                System.out.print("Masukkan jumlah top-up: Rp");
                double jumlah = sc.nextDouble();
                pel.topUp(jumlah);
            } else if (pil == 2) {
                System.out.print("Masukkan harga pembelian: Rp");
                double harga = sc.nextDouble();
                pel.beli(harga);
            } else {
                System.out.println("Pilihan tidak valid. Silakan coba lagi.");     
            }
        }
    }

    // Method untuk mencari pelanggan berdasarkan nomor pelanggan
    // Menggunakan loop untuk mencari pelanggan dalam array pelanggan
    private static Pelanggan cariPelanggan(String num) {
        for (Pelanggan p : pelanggan) {
            if (p.getNomorPelanggan().equals(num)) {
                return p;
            }
        }
        return null;
    }
}
