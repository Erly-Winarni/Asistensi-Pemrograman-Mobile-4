# 📱 Repositori Pengumpulan Tugas Praktikum Pemrograman Mobile (Java)

Repositori ini digunakan untuk **manajemen dan pengumpulan tugas praktikum** mahasiswa mata kuliah Pemrograman Mobile berbasis Java (Android Studio) selama satu semester.

> Setiap praktikan mengumpulkan tugas melalui sistem **Fork & Pull Request** di GitHub.

---

## 👨‍🏫 Informasi Asisten

|                              |                        |
| ---------------------------- | ---------------------- |
| **Asisten Praktikum**  | Mahendra Kirana M B    |
| **Instansi**           | Universitas Hasanuddin |
| **Jumlah Praktikan**   | 11 Orang               |
| **Jumlah Praktikum**   | 7 Pertemuan            |
| **Bahasa Pemrograman** | Java (Android)         |
| **IDE**                | Android Studio         |

---

## 📋 Daftar Praktikan

| No | NIM | Nama Lengkap | Angkatan |
|:---:|---|---|:---:|
| 1 | H071221027 | Ahmad Alim Arzaq | 2022 |
| 2 | H071241009 | Mahesa Putri Lukman | 2024 |
| 3 | H071241018 | Muhammad Alif Sakti | 2024 |
| 4 | H071241027 | Moch Ichwanul Muslimin Mayang | 2024 |
| 5 | H071241036 | A. Alya Musaenab Asmin | 2024 |
| 6 | H071241055 | Muhammad Hairi | 2024 |
| 7 | H071241065 | Akmal | 2024 |
| 8 | H071241074 | Erly Winarni | 2024 |
| 9 | H071241081 | Ahmad Hidayat | 2024 |
| 10 | H071241083 | As'syiekril Fikryan Sarda | 2024 |
| 11 | H071241093 | Moch. Syech Yusuf. M | 2024 |

---

## 📁 Struktur Folder Repositori

Setiap tugas praktikum memiliki folder tersendiri. Mahasiswa **wajib** membuat sub-folder dengan format identitas di dalam folder tugas yang sedang berjalan.

```text
/
├── .gitignore                          ← Pencegah file sampah Android Studio
├── README.md                           ← Panduan ini
│
├── Tugas-Praktikum_01/
│   ├── H071231001_Budi_Santoso/        ← Folder identitas mahasiswa
│   │   ├── app/                        ← Source code Android
│   │   ├── gradle/
│   │   ├── build.gradle
│   │   ├── settings.gradle
│   │   └── README.md                   (Opsional: penjelasan tugas)
│   └── H071231002_Siti_Aminah/
│       └── ...
│
├── Tugas-Praktikum_02/
│   └── NIM_Nama_Lengkap/
│
├── ...
│
└── Tugas-Praktikum_07/
    └── NIM_Nama_Lengkap/
```

### 📌 Format Penamaan Folder Identitas

```
NIM_Nama_Lengkap
```

| Komponen        | Contoh                                |
| --------------- | ------------------------------------- |
| NIM             | `H071231001`                        |
| Pemisah         | `_` (underscore)                    |
| Nama Lengkap    | `Budi_Santoso`                      |
| **Hasil** | **`H071231001_Budi_Santoso`** |

> ⚠️ **Gunakan underscore (`_`), BUKAN spasi.** Folder dengan spasi akan menyebabkan masalah di Git.

---

## 🚀 Panduan Pengumpulan Tugas (Fork & Pull Request)

Pengumpulan tugas menggunakan sistem **Fork & Pull Request**. Setiap praktikan bekerja di repositori hasil fork masing-masing, lalu mengirimkan tugas melalui Pull Request ke repositori utama ini.

### Langkah 1 — Fork Repositori (Hanya Sekali di Awal Semester)

1. Buka halaman repositori ini di GitHub:
   ```
   https://github.com/Kyyneko/Asistensi-Pemrograman-Mobile-4
   ```
2. Klik tombol **`Fork`** di pojok kanan atas halaman
3. Pastikan opsi **"Copy the `main` branch only"** tercentang
4. Klik **`Create fork`**
5. Repositori akan ter-copy ke akun GitHub Anda:
   ```
   https://github.com/USERNAME-ANDA/Asistensi-Pemrograman-Mobile-4
   ```

---

### Langkah 2 — Clone Fork ke Komputer Lokal (Hanya Sekali)

Clone **repositori fork Anda** (bukan repositori utama):

```bash
git clone https://github.com/USERNAME-ANDA/Asistensi-Pemrograman-Mobile-4.git
cd Asistensi-Pemrograman-Mobile-4
```

Lalu tambahkan repositori utama sebagai `upstream`:

```bash
git remote add upstream https://github.com/Kyyneko/Asistensi-Pemrograman-Mobile-4.git
```

Verifikasi remote sudah benar:

```bash
git remote -v
```

Output yang diharapkan:

```
origin    https://github.com/USERNAME-ANDA/Asistensi-Pemrograman-Mobile-4.git (fetch)
origin    https://github.com/USERNAME-ANDA/Asistensi-Pemrograman-Mobile-4.git (push)
upstream  https://github.com/Kyyneko/Asistensi-Pemrograman-Mobile-4.git (fetch)
upstream  https://github.com/Kyyneko/Asistensi-Pemrograman-Mobile-4.git (push)
```

---

### Langkah 3 — Sinkronisasi Sebelum Mengerjakan Tugas Baru

**Setiap kali akan mengerjakan tugas baru**, tarik update terbaru dari repositori utama agar mendapatkan folder tugas praktikum terbaru:

```bash
git fetch upstream
git merge upstream/main
git push origin main
```

> 💡 Langkah ini memastikan fork Anda selalu up-to-date dengan struktur folder terbaru.

---

### Langkah 4 — Kerjakan & Simpan Tugas

1. Buat folder identitas Anda di dalam folder tugas yang sesuai:
   ```
   Tugas-Praktikum_01/H071231001_Budi_Santoso/
   ```
2. Copy/paste file project Android Studio Anda ke dalam folder tersebut
3. **Pastikan folder `build/`, `.gradle/`, `.idea/` TIDAK ikut ter-copy** (lihat bagian [File yang Dilarang](#️-file-yang-dilarang-push))

---

### Langkah 5 — Commit & Push ke Fork

```bash
git add .
git commit -m "Submit Tugas Praktikum 01 - H071231001 - Budi Santoso"
git push origin main
```

#### Format Commit Message

```
Submit Tugas Praktikum XX - NIM - Nama Lengkap
```

| Contoh                                                    |
| --------------------------------------------------------- |
| `Submit Tugas Praktikum 01 - H071231001 - Budi Santoso` |
| `Submit Tugas Praktikum 05 - H071231002 - Siti Aminah`  |

---

### Langkah 6 — Buat Pull Request (PR)

1. Buka repositori **fork Anda** di GitHub
2. Klik **`Contribute`** → **`Open Pull Request`**
3. Pastikan arahnya benar:
   ```
   base: Kyyneko/Asistensi-Pemrograman-Mobile-4 (main)  ←  head: USERNAME-ANDA/Asistensi-Pemrograman-Mobile-4 (main)
   ```
4. Isi judul PR sesuai format:
   ```
   Submit Tugas Praktikum XX - NIM - Nama Lengkap
   ```
5. Klik **`Create Pull Request`**

> ⏳ **Pull Request akan di-review oleh asisten praktikum.** PR yang memenuhi syarat akan di-merge setelah deadline pengumpulan berakhir.

---

## 🔄 Ringkasan Alur Pengumpulan

```
┌──────────────────────────────────────────────────────────┐
│                    SETUP AWAL (SEKALI)                    │
│                                                          │
│   Fork repo  →  Clone fork  →  Tambah upstream          │
└──────────────────────────┬───────────────────────────────┘
                           │
                           ▼
┌──────────────────────────────────────────────────────────┐
│              SETIAP PENGUMPULAN TUGAS                     │
│                                                          │
│   Sync upstream  →  Kerjakan tugas  →  Commit & Push     │
│                                      →  Buat PR          │
└──────────────────────────────────────────────────────────┘
```

---

## ⛔ File yang Dilarang Push

Folder project Android Studio sangat besar (bisa **>100MB**) karena file hasil kompilasi.
**DILARANG KERAS** melakukan push pada file/folder berikut:

| ❌ File / Folder     | Keterangan                                 | Ukuran Rata-rata |
| -------------------- | ------------------------------------------ | ---------------- |
| `.gradle/`         | Cache Gradle                               | ~50-200 MB       |
| `.idea/`           | Konfigurasi IDE                            | ~5-10 MB         |
| `build/`           | Folder hasil kompilasi                     | ~50-300 MB       |
| `local.properties` | Path SDK lokal (berisi path komputer Anda) | <1 KB            |
| `*.apk`            | File aplikasi hasil build                  | ~5-50 MB         |
| `*.aab`            | File bundle hasil build                    | ~5-50 MB         |
| `captures/`        | Screenshot/video profiling                 | Bervariasi       |

### Cara Memastikan File Tidak Ikut Ter-push

File `.gitignore` di repositori ini **sudah dikonfigurasi** untuk mengabaikan file-file di atas. Namun, pastikan:

1. **Jangan copy folder `build/`, `.gradle/`, `.idea/`** ke dalam folder tugas Anda
2. Jika sudah terlanjur ter-push, jalankan:
   ```bash
   git rm -r --cached Tugas-Praktikum_XX/NIM_Nama/.gradle
   git rm -r --cached Tugas-Praktikum_XX/NIM_Nama/build
   git rm -r --cached Tugas-Praktikum_XX/NIM_Nama/.idea
   git commit -m "Remove build artifacts"
   git push origin main
   ```

> 🚨 **Jika ukuran tugas Anda melebihi 5MB**, PR Anda akan **ditolak** dan diminta melakukan clean up terlebih dahulu.

---

## 📜 Kode Etik Praktikum

### 1. 🛡️ Integritas Akademik

- **Dilarang menyalin (copy-paste) kode** dari folder praktikan lain.
- **Dilarang mengakses repositori fork milik praktikan lain** untuk melihat atau menyalin kode mereka. Ini termasuk pelanggaran integritas akademik.
- Setiap tugas akan diperiksa kemiripannya. **Plagiarisme akan dikenakan sanksi.**

### 2. 🔒 Keamanan Repositori

- **Dilarang mengubah file di luar folder identitas Anda**, termasuk `README.md`, `.gitignore`, dan folder praktikan lain.
- Segala perubahan tercatat di **History Git** dan dapat dilacak.
- PR yang mengandung perubahan di luar folder identitas Anda akan **otomatis ditolak**.

### 3. ⏰ Ketentuan Deadline

- Waktu pengumpulan dihitung berdasarkan **timestamp Pull Request** Anda di GitHub.
- PR yang dibuat **setelah deadline** akan dianggap **terlambat**.
- **PR tidak akan di-merge selama periode pengumpulan masih berlangsung**, untuk menjaga kerahasiaan tugas antar praktikan.

### 4. 📂 Ketentuan Format

- Folder identitas **wajib** mengikuti format: `NIM_Nama_Lengkap`
- Commit message **wajib** mengikuti format yang ditentukan
- Project harus bisa di-build dan dijalankan di Android Studio

---

## ❓ FAQ (Pertanyaan yang Sering Diajukan)

<details>
<summary><strong>Q: Saya belum pernah pakai Git/GitHub, harus mulai dari mana?</strong></summary>

1. Buat akun di [github.com](https://github.com)
2. Install [Git](https://git-scm.com/downloads) di komputer Anda
3. Konfigurasi Git:
   ```bash
   git config --global user.name "Nama Lengkap Anda"
   git config --global user.email "email@anda.com"
   ```
4. Ikuti langkah-langkah di bagian [Panduan Pengumpulan](#-panduan-pengumpulan-tugas-fork--pull-request)

</details>

<details>
<summary><strong>Q: Bagaimana jika saya salah menaruh tugas di folder yang salah?</strong></summary>

Pindahkan folder ke lokasi yang benar, lalu commit ulang:

```bash
git mv Tugas-Praktikum_01/NIM_Nama Tugas-Praktikum_02/NIM_Nama
git commit -m "Fix: Pindah tugas ke folder yang benar"
git push origin main
```

</details>

<details>
<summary><strong>Q: Pull Request saya conflict, bagaimana cara mengatasinya?</strong></summary>

Sinkronisasi ulang fork Anda:

```bash
git fetch upstream
git merge upstream/main
```

Jika ada conflict, selesaikan secara manual, lalu:

```bash
git add .
git commit -m "Resolve merge conflict"
git push origin main
```

PR akan otomatis ter-update.

</details>

<details>
<summary><strong>Q: Apakah saya bisa mengumpulkan ulang (revisi) tugas?</strong></summary>

Ya, selama deadline belum lewat. Cukup push ulang ke fork Anda dan PR akan otomatis ter-update dengan perubahan terbaru.

</details>

<details>
<summary><strong>Q: Kenapa PR saya ditolak?</strong></summary>

Kemungkinan penyebab:

- Folder `build/`, `.gradle/`, `.idea/` ikut ter-push (ukuran terlalu besar)
- Format folder identitas salah
- Commit message tidak sesuai format
- Ada perubahan di file/folder di luar folder identitas Anda

</details>

---

## 📋 Checklist Sebelum Submit Pull Request

Gunakan checklist ini sebelum membuat PR:

- [ ] ✅ Folder identitas sesuai format `NIM_Nama_Lengkap`
- [ ] ✅ Project disimpan di folder `Tugas-Praktikum_XX` yang benar
- [ ] ✅ Folder `build/`, `.gradle/`, `.idea/` **tidak** ikut ter-push
- [ ] ✅ File `local.properties` **tidak** ikut ter-push
- [ ] ✅ Tidak ada file `.apk` atau `.aab` yang ikut ter-push
- [ ] ✅ Ukuran total tugas **di bawah 5MB**
- [ ] ✅ Commit message sesuai format: `Submit Tugas Praktikum XX - NIM - Nama Lengkap`
- [ ] ✅ PR mengarah ke branch `main` repositori utama
- [ ] ✅ Tidak ada perubahan di file/folder milik praktikan lain

---

> **Selamat Berpraktikum!** 🎉
>
> Jaga kerapian kode dan kebersihan repositori.
> Jika ada pertanyaan, hubungi asisten praktikum.
