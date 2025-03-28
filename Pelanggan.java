package Semester_2.Praktikum.Swalayan;

public class Pelanggan {
    private String nama;
    private double saldo;
    private String nomorPelanggan;
    private String pin;
    private boolean diblokir;
    private int percobaan;

    public Pelanggan(String nama, double saldo, String nomorPelanggan, String pin) {
        this.nama = nama;
        this.saldo = saldo;
        this.nomorPelanggan = nomorPelanggan;
        this.pin = pin;
        this.diblokir = false;
        this.percobaan = 0;
    }

    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    public boolean isDiblokir() {
        return diblokir;
    }

    // Method untuk mengautentifikasi PIN
    public boolean autentifikasi(String s) {
        // Jika akun diblokir, kembalikan false
        // Jika akun tidak diblokir, lakukan autentifikasi PIN
        if (diblokir) {
            System.out.println("Akun ini diblokir");
            return false;
        }

        // Jika PIN benar, reset percobaan dan kembalikan true
        // Jika PIN salah, increment percobaan dan kembalikan false
        if (pin.equals(s)) {
            percobaan = 0;
            return true;
        } else {
            percobaan++;
            System.out.println("PIN salah");
            if (percobaan >= 3) {
                diblokir = true;
                System.out.println("Anda telah salah memasukkan PIN sebanyak 3 kali, akun diblokir");
            }
            return false;
        }
    }

    public void topUp(double d) {
        saldo += d;
        System.out.println("Top-up berhasil. Saldo anda sekarang adalah Rp" + saldo);
    }
    
    public boolean beli(double d) {
        if (saldo - d < 10000) {
            System.out.println("Saldo tidak cukup. Minimal saldo setelah transaksi adalah Rp10.000");
            return false;
        }

        double cashback = hitungCashback(d);
        saldo -= d;
        saldo += cashback;
        System.out.println("Pembelian berhasil. Anda mendapatkan cashback sebesar Rp" + cashback);
        System.out.println("Saldo anda sekarang adalah Rp" + saldo);
        return true;
    }

    // Method untuk menghitung cashback berdasarkan nomor pelanggan dan jumlah pembelian
    // Nomor pelanggan diawali dengan 2 digit yang menunjukkan jenis pelanggan
    private double hitungCashback(double d) {
        double cashback = 0;
        String s = nomorPelanggan.substring(0, 2);
        if (d > 1000000) {
            switch (s) {
                case "38":
                    cashback = d * 0.05;
                    break;
                case "56":
                    cashback = d * 0.07;
                    break;
                case "74":
                    cashback = d * 0.10;
                    break;
            }
        } else {
            switch (s) {
                case "56":
                    cashback = d * 0.02;
                    break;
                case "74":
                    cashback = d * 0.05;
                    break;
            }
        }
        return cashback;
    }
}
