package Strukdat;

import java.util.Scanner;



public class PemesananTiket {

    private static Scanner scanner = new Scanner(System.in);

    private static Node<Pemesanan> head;
    private static Node<Pemesanan> tail;
    private static int nomorUrut = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("**Pilihan Menu**");
            System.out.println("1. Tambah Pemesanan");
            System.out.println("2. Tampilkan Antrian");
            System.out.println("3. Hapus Pemesanan");
            System.out.println("4. Keluar");
            System.out.print  (" Pilih Menu : ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahPemesanan();
                    break;
                case 2:
                    tampilkanAntrian();
                    break;
                case 3:
                    hapusPemesanan();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahPemesanan() {
        System.out.print("Masukkan nama pemesan: ");
        String namaPemesan = scanner.nextLine();

        System.out.print("Masukkan jumlah tiket: ");
        int jumlahTiket = scanner.nextInt();

        String nomorPemesanan = String.format("%03d", nomorUrut);
        Pemesanan pemesanan = new Pemesanan(nomorPemesanan, namaPemesan, jumlahTiket);
        enqueue(pemesanan);

        System.out.println("Pemesanan berhasil ditambahkan!");
        System.out.println("Nomor Pemesanan: " + nomorPemesanan);

        nomorUrut++;
    }

    private static void tampilkanAntrian() {
        if (isEmpty()) {
            System.out.println("Antrian kosong!");
            return;
        }

        int nomor = 1;
        Node<Pemesanan> current = head;
        while (current != null) {
            System.out.println("**Pemesanan " + nomor + "**");
            System.out.println("Nomor Pemesanan: " + current.data.getNomorPemesanan());
            System.out.println("Nama Pemesan: " + current.data.getNamaPemesan());
            System.out.println("Jumlah Tiket: " + current.data.getJumlahTiket());
            System.out.println();

            nomor++;
            current = current.next;
        }
    }

    private static void hapusPemesanan() {
        if (isEmpty()) {
            System.out.println("Antrian kosong!");
            return;
        }

        dequeue();
        System.out.println("Pemesanan pertama telah dihapus!");
    }

    private static void enqueue(Pemesanan pemesanan) {
        Node<Pemesanan> newNode = new Node<>(pemesanan);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    private static Pemesanan dequeue() {
        if (isEmpty()) {
            return null;
        }

        Pemesanan data = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        return data;
    }

    private static boolean isEmpty() {
        return head == null;
    }
}

class Pemesanan {

    private String nomorPemesanan;
    private String namaPemesan;
    private int jumlahTiket;

    public Pemesanan(String nomorPemesanan, String namaPemesan, int jumlahTiket) {
        this.nomorPemesanan = nomorPemesanan;
        this.namaPemesan = namaPemesan;
        this.jumlahTiket = jumlahTiket;
    }

    public String getNomorPemesanan() {
        return nomorPemesanan;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }
}

class Node<T> {

    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}

