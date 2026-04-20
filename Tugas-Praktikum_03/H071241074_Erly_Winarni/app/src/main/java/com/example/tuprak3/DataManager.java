package com.example.tuprak3;

import com.example.tuprak3.R;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;
    private final List<Book> books = new ArrayList<>();

    private DataManager() {
        loadDummyData();
    }

    public static DataManager getInstance() {
        if (instance == null) instance = new DataManager();
        return instance;
    }

    private void loadDummyData() {
        int placeholder = R.drawable.cover_placeholder;

        books.add(new Book(
                "Bumi", "Tere Liye", 2014,
                "Raib, gadis 15 tahun, memiliki kemampuan menghilang yang tidak diketahui siapapun. Petualangan dimulai ketika ia menemukan dunia paralel yang penuh misteri dan bahaya.",
                "Fantasi", 4.3f,
                "Worldbuilding yang kuat and alur yang seru. Tere Liye berhasil membangun dunia paralel yang memikat sejak halaman pertama.", R.drawable.bumi));

        books.add(new Book(
                "Bulan", "Tere Liye", 2015,
                "Petualangan membawa mereka ke Klan Bulan, tempat yang tampak damai namun menyimpan konflik tersembunyi. Seli mulai memahami kekuatan aslinya.",
                "Fantasi", 4.4f,
                "Sering dianggap lebih baik dari buku pertama karena emosi dan konflik mulai terasa dalam. Dunia yang dikunjungi terasa lebih hidup dan tidak hitam-putih. Banyak pembaca menyukai perkembangan karakter Seli di sini, karena ia jadi lebih dari sekadar “teman dengan kekuatan”.", R.drawable.bulan));

        books.add(new Book(
                "Matahari", "Tere Liye", 2016,
                "Fokus beralih ke Ali, yang mulai mengungkap rahasia besar tentang dunia paralel dan ancaman yang lebih luas.",
                "Fantasi", 4.4f,
                "Bagian ini memperluas cerita secara signifikan. Plot mulai kompleks dan penuh twist, terutama terkait rahasia dunia paralel. Pembaca menyukai bagaimana Ali berkembang sebagai karakter cerdas yang ternyata juga punya sisi rentan.", R.drawable.matahari));

        books.add(new Book(
                "Bintang", "Tere Liye", 2017,
                "Konflik antar dunia meningkat dan petualangan berubah menjadi perjuangan besar untuk menjaga keseimbangan dunia paralel.",
                "Fantasi", 4.4f,
                "Skala cerita terasa jauh lebih besar dan serius. Banyak yang menyebut ini titik di mana seri berubah dari petualangan remaja menjadi fantasi epik. Ketegangan meningkat, dan pilihan karakter mulai terasa berat.", R.drawable.bintang));

        books.add(new Book(
                "Ceros dan Batazoar", "Tere Liye", 2018,
                "Perjalanan membawa mereka menghadapi musuh kuat dengan rencana besar yang mengancam banyak dunia.",
                "Fantasi", 4.4f,
                "Salah satu buku paling disukai karena penuh aksi dan strategi. Antagonisnya terasa kuat dan menantang, membuat konflik lebih intens. Banyak pembaca merasa ini salah satu puncak keseruan seri.", R.drawable.ceros));

        books.add(new Book(
                "Komet", "Tere Liye", 2018,
                "Ancaman lama kembali dan dunia paralel mulai kehilangan keseimbangan.",
                "Fantasi", 4.3f,
                "Nuansanya lebih gelap dibanding sebelumnya. Cerita terasa lebih menegangkan karena konsekuensi yang dihadapi karakter semakin besar. Beberapa pembaca menyukai keseriusannya, meski ada yang merasa alurnya mulai padat.", R.drawable.komet));

        books.add(new Book(
                "Komet Minor", "Tere Liye", 2019,
                "Petualangan ke dunia baru membuka potongan penting dari konflik besar yang sedang berlangsung.",
                "Fantasi", 4.3f,
                "Lebih fokus pada eksplorasi dunia dan lore. Pembaca yang suka detail dunia paralel sangat menikmati bagian ini, meskipun alur utama terasa sedikit melambat.", R.drawable.komet_minor));

        books.add(new Book(
                "Selena", "Tere Liye", 2020,
                "Kisah masa lalu Selena, memperlihatkan perjalanan hidup dan awal mula konflik besar.",
                "Fantasi", 4.2f,
                "Lebih emosional dan personal dibanding buku lain. Banyak pembaca menghargai pendalaman karakter, walau terasa seperti jeda dari cerita utama.", R.drawable.selena));

        books.add(new Book(
                "Nebula", "Tere Liye", 2020,
                "Melanjutkan kisah Selena dan mengungkap lebih banyak rahasia dunia paralel.",
                "Fantasi", 4.3f,
                "Memberi banyak jawaban penting yang sebelumnya misterius. Cocok untuk pembaca yang ingin memahami keseluruhan cerita dengan lebih dalam.", R.drawable.nebula));

        books.add(new Book(
                "Lumpu", "Tere Liye", 2021,
                "Raib dan tim menghadapi dunia baru dengan aturan dan bahaya yang berbeda.",
                "Fantasi", 4.3f,
                "Menawarkan variasi setting yang segar. Cerita tetap menarik meski beberapa pembaca merasa ini lebih seperti petualangan tambahan.", R.drawable.lumpu));

        books.add(new Book(
                "Si Putih", "Tere Liye", 2021,
                "Cerita dari sudut pandang lain yang menunjukkan sisi berbeda dari petualangan utama.",
                "Fantasi", 4.2f,
                "Lebih ringan dan menyentuh. Cocok sebagai pelengkap, terutama bagi yang ingin melihat sisi emosional karakter.", R.drawable.siputih));

        books.add(new Book(
                "ILY", "Tere Liye", 2016,
                "Buku ILY melanjutkan petualangan Raib, Seli, dan Ali dalam menghadapi konflik baru yang lebih kompleks di dunia paralel. Mereka harus menghadapi ancaman besar yang berkaitan dengan kekuatan, persahabatan, dan pengorbanan. Di tengah situasi yang semakin berbahaya, hubungan antar tokoh juga berkembang, menghadirkan sisi emosional yang lebih dalam dibanding buku-buku sebelumnya.",
                "Fantasi", 4.6f,
                "ILY banyak disukai karena alurnya yang lebih matang dan emosional. Cerita tidak hanya fokus pada aksi dan dunia fantasi, tetapi juga memperdalam hubungan karakter. Plot twist-nya terasa kuat dan membuat pembaca penasaran, meskipun beberapa bagian dianggap cukup berat karena konflik yang lebih serius.", R.drawable.ily));

        books.add(new Book(
                "Bibi Gill", "Tere Liye", 2022,
                "Mengangkat kisah dan peran Bibi Gill dalam dunia paralel.",
                "Fantasi", 4.3f,
                "Menambah kedalaman karakter pendukung. Banyak pembaca merasa cerita ini memperkaya keseluruhan seri.", R.drawable.bibigil));

        books.add(new Book(
                "Sagaras", "Tere Liye", 2022,
                "Kisah tentang tokoh kuat yang berperan penting dalam konflik besar dunia paralel.",
                "Fantasi", 4.5f,
                "Salah satu buku dengan respons terbaik. Karakter dan konflik terasa kuat, serta memberikan dampak besar pada cerita keseluruhan.", R.drawable.sigaras));

        books.add(new Book(
                "Matahari Minor", "Tere Liye", 2022,
                "Cerita tambahan yang melengkapi detail dunia dan karakter.",
                "Fantasi", 4.2f,
                "Lebih cocok sebagai pelengkap untuk fans. Walau tidak sepenting cerita utama, tetap menarik untuk memperkaya pemahaman.", R.drawable.matahari_minor));
    }

    public List<Book> getAllBooks()       { return books; }

    public List<Book> getFavoriteBooks() {
        List<Book> favs = new ArrayList<>();
        for (Book b : books) if (b.isLiked()) favs.add(b);
        return favs;
    }

    public void addBook(Book book) {
        books.add(0, book);
    }
}
