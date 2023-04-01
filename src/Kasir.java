import java.util.Scanner;

public class Kasir {
    private Meja[] daftarMeja;
    private Menu[] daftarMenu;

    public Kasir() {
        daftarMeja = new Meja[10];
        for (int i = 0; i < 10; i++) {
            daftarMeja[i] = new Meja(i + 1);
        }

        daftarMenu = new Menu[5];
        daftarMenu[0] = new Menu("Nasi Goreng", 15000);
        daftarMenu[1] = new Menu("Mi Goreng", 15000);
        daftarMenu[2] = new Menu("Capcay", 20000);
        daftarMenu[3] = new Menu("Bihun Goreng", 17000);
        daftarMenu[4] = new Menu("Ayam Koloke", 25000);
    }

    // digunakan untuk menampilkan daftar meja beserta keterangan ketersediaannya
    // gunakan method isKosong pada class Kasir agar lebih mudah
    public void tampilkanDaftarMeja() {
        System.out.print("Nomor meja yang tersedia: ");
        for (int i = 0; i < 10; i++) {
            if (!daftarMeja[i].isKosong())
                continue;
            System.out.printf("%d ", i+1);
        }
        System.out.println();
    }

    // untuk menambahkan pelanggan pada meja tertentu
    // jika meja kosong tambahkan pelanggan pada meja tersebut
    // jika tidak buatlah keterangan bahwa meja sudah ada pelanggan
    public void tambahPelanggan(int nomorMeja, Pelanggan pelanggan) {
        daftarMeja[nomorMeja - 1].setPelanggan(pelanggan);
    }

    // menambah pesanan menu pada nomor meja
    // jika menu tidak ada dalam daftar maka tampilkan "Menu is null"
    public void tambahPesanan(int nomorMeja, Menu menu) {
        daftarMeja[nomorMeja - 1].setMenu(menu);
    }

    // Menghapus pelanggan
    public void hapusPelanggan(int nomorMeja) {
        daftarMeja[nomorMeja - 1].setPelanggan(null);
        daftarMeja[nomorMeja - 1].setMenu(null);
    }

    public int hitungHargaPesanan(int nomorMeja) {
        int totalHarga = 0;
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu.length > 0) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    totalHarga += menu[i].getHarga();
                }
            }
            return totalHarga;
        }
        return totalHarga;
    }

    public void tampilkanPesanan(int nomorMeja) {
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu[0] != null) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    System.out.println("Meja " + nomorMeja + " - " + pelanggan.getNama() + " memesan "
                            + menu[i].getNama() + " seharga " + menu[i].getHarga());
                }
            }
        } else {
            System.out.println("Meja " + nomorMeja + " tidak memiliki pesanan");
        }
    }

    public void tampilkanDaftarMenu() {
        System.out.println("Daftar Menu:");
        System.out.println("1. Nasi Goreng - Rp15.000");
        System.out.println("2. Mi Goreng - Rp15.000");
        System.out.println("3. Capcay - Rp20.000");
        System.out.println("4. Bihun Goreng - Rp17.000");
        System.out.println("5. Ayam Koloke - Rp25.000");
        System.out.println("6. Simpan");
        System.out.println();
    }

    public void tampilkanDaftarFitur() {
        System.out.println("1. Tampilkan daftar meja");
        System.out.println("2. Tambah pelanggan");
        System.out.println("3. Tambah pesanan");
        System.out.println("4. Hapus pelanggan");
        System.out.println("5. Hitung harga pesanan");
        System.out.println("6. Tampilkan pesanan di meja");
        System.out.println("0. Keluar");
    }

    public void jalankan() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;
        while (pilihan != 0) {
            tampilkanDaftarFitur();
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {
                case 1:
                    tampilkanDaftarMeja();
                    break;
                case 2:
                    // tampilkan pesan untuk input nomor meja dan nama pelanggan untuk digunakan
                    // pada method
                    // jangan lupa instansiasi Pelanggan dengan nama pelanggan sesuai input
                    int nomorMejaPelanggan;
                    System.out.print("Masukkan nama anda: ");
                    String namaPelanggan = scanner.nextLine();
                    while (true) {
                        System.out.print("Masukkan nomor meja: ");
                        nomorMejaPelanggan = scanner.nextInt();
                        scanner.nextLine();
                        if (daftarMeja[nomorMejaPelanggan - 1].isKosong()) break;
                        System.out.println("Meja yang anda pilih masih ada pelanggan.");
                        System.out.println("Silahkan coba lagi.");
                    }
                    Pelanggan pelangganBaru = new Pelanggan(namaPelanggan);
                    tambahPelanggan(nomorMejaPelanggan, pelangganBaru);
                    break; 
                case 3:
                    boolean stopLoop = false;
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaPesan = scanner.nextInt();
                    Boolean meja = daftarMeja[nomorMejaPesan - 1].isKosong();
                    scanner.nextLine();
                    if (!meja) {
                        tampilkanDaftarMenu();
                        while (!stopLoop) {
                            System.out.print("Masukkan nomor menu: ");
                            int nomorMenuPesan = scanner.nextInt();
                            scanner.nextLine();
                            switch (nomorMenuPesan) {
                                case 1:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[0]);
                                    break;
                                case 2:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[1]);
                                    break;
                                case 3:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[2]);
                                    break;
                                case 4:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[3]);
                                    break;
                                case 5:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[4]);
                                    break;
                                case 6:
                                    stopLoop = true;
                                    break;
                                default:
                                    System.out.println("Nomor menu tidak valid");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Meja tidak ada pelanggan");
                    }
                    break;
                case 4:
                    // untuk menghapus pelanggan pada meja tertentu
                    // tampilkan pesan untuk memasukkan nomor meja yang akan dihapus untuk digunakan
                    // pada method hapusPelanggan()
                    int nomorMejaTerisi;
                    while (true) {
                        System.out.print("Masukkan nomor meja: ");
                        nomorMejaTerisi = scanner.nextInt();
                        scanner.nextLine();
                        if (!daftarMeja[nomorMejaTerisi - 1].isKosong()) break;
                        System.out.println("Meja ini sudah kosong.");
                        System.out.println("Silahkan coba lagi.");
                    }
                    hapusPelanggan(nomorMejaTerisi);
                    break;
                case 5:
                    // Untuk melihat total harga pesanan pada meja tertentu
                    // tampilkan pesan untuk memasukkan nomor meja
                    // jangan lupa membedakan keluaran apabila pelanggan belum memesan apapun /
                    // total harga 0
                    int nomorMejaHarga;
                    System.out.print("Masukkan nomor meja: ");
                    nomorMejaHarga = scanner.nextInt();
                    scanner.nextLine();
                    if (!daftarMeja[nomorMejaHarga - 1].isKosong()){
                        if (hitungHargaPesanan(nomorMejaHarga) == 0){
                            System.out.println("Pelanggan di meja ini belum memesan apapun.");
                        } else{
                            System.out.println("Total harga yang di pesan pelanggan ini: " + hitungHargaPesanan(nomorMejaHarga));
                        }
                    } else{
                        System.out.println("Meja ini tidak ada pelanggan.");
                    }
                    break;
                case 6:
                    // untuk melihat pesanan pada meja tertentu
                    // tampilkan pesan untuk memasukkan nomor meja
                    int nomorMejaLihat;
                    System.out.print("Masukkan nomor meja: ");
                    nomorMejaLihat = scanner.nextInt();
                    scanner.nextLine();
                    if (!daftarMeja[nomorMejaLihat - 1].isKosong()){
                        tampilkanPesanan(nomorMejaLihat);
                    } else{
                        System.out.println("Meja ini tidak ada pelanggan.");
                    }
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi kasir restoran!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}