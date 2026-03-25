# Lab Mobile - Instagram Clone (Profile & Feed)

Proyek ini dibuat untuk tugas mata kuliah Lab Pemrograman Mobile. Fokus utamanya adalah implementasi UI/UX profil Instagram, navigasi antar activity, dan manipulasi data sederhana (Edit Profile).

## Fitur Utama
* **Halaman Profil:** Menampilkan informasi user (username, bio, jumlah postingan) dan grid feed.
* **Edit Profil:** Bisa mengubah nama, username, dan bio. Data dikirim balik ke halaman utama menggunakan `ActivityResultLauncher`.
* **Detail Postingan:** Klik pada gambar di feed untuk melihat caption lengkap dan detail foto.
* **Custom UI:** Menggunakan Material Design, ShapeableImageView (untuk foto profil bulat), dan ConstraintLayout.

## Cara Run
1. Clone repository ini.
2. Buka di Android Studio (disarankan versi terbaru/Ladybug).
3. Tunggu proses Gradle Sync selesai.
4. Run di Emulator atau HP Android (Min SDK 24).

## Tech Stack
* **Language:** Java
* **UI Framework:** XML (Material Design)
* **Components:** AppCompat, ConstraintLayout, Material Components.

## Struktur Folder Penting
* `MainActivity.java`: Halaman profil utama.
* `NextActivity.java`: Logika edit profil.
* `PostDetailActivity.java`: Detail setiap postingan.
* `res/layout/`: Semua desain XML aplikasi.
