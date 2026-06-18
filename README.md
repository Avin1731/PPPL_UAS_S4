# 🍃 SIPELITA - Sistem Informasi Penilaian Nirwasita Tantra
> Proyek Akhir Praktikum Pengujian Perangkat Lunak - Kelompok PAD 2
> Target Web: [https://area-fe-pad.vercel.app/](https://area-fe-pad.vercel.app/)

---

## 🖥️ 1. Penjelasan SUT (System Under Test)
**SIPELITA** adalah Sistem Informasi Manajemen Data Lingkungan Hidup berbasis web yang dikembangkan untuk digitalisasi pelaporan data lingkungan hidup daerah (Provinsi & Kabupaten/Kota) ke Pusat (Pusdatin).

### 🎯 Tujuan Utama:
* Mempermudah pengiriman dokumen fisik (**SLHD**, **IKLH**, **Tabel Data Utama**) menjadi pelaporan digital yang terstruktur, cepat, dan transparan.

### 👥 Target Pengguna & Peran:
* **DLH Kabupaten/Kota & Provinsi:** Mengunduh template, mengunggah dokumen pelaporan, dan melihat hasil penilaian.
* **Pusdatin:** Menerima dokumen, memantau progres pengiriman daerah, dan melakukan penilaian penghargaan Nirwasita Tantra.
* **Administrator:** Mengelola akun user (Pusdatin & DLH) serta mengatur deadline pengiriman data.

---

## 💻 2. Spesifikasi Teknis SUT (Application Stack)
Berikut adalah spesifikasi lingkungan pengembangan dan teknologi yang digunakan oleh aplikasi SIPELITA:

### ⚙️ A. Lingkungan Pengembangan Lokal (Laragon & Host)
* **PHP CLI Runtime:** `PHP 8.3.16` (Visual C++ 2019 x64)
* **Node.js Runtime:** `v22.20.0`
* **Package Manager (FE):** `pnpm 11.5.1` (lockfile format `'9.0'`)
* **Composer (BE):** `Composer version 2.8.4`
* **Database Engine:** `MySQL 8.0` (melalui instance Laragon / port `3306`)

### 🎨 B. Spesifikasi Frontend (`AreaFE-PAD`)
* **Framework Utama:** `Next.js 16.1.6` (dengan `React 19.2.0` dan `React DOM 19.2.0`)
* **Type Definitions:** `@types/node: ^20` dan `@types/react: ^19`
* **Styling Engine:** `Tailwind CSS v4.x` (dengan `@tailwindcss/postcss ^4`)
* **HTTP Client:** `Axios ^1.13.2`
* **State / Query Management:** `@tanstack/react-query ^5.90.21`
* **Target Docker Runtime:** `Node.js 20.x-alpine` (menggunakan `pnpm@9`).
* **Optimasi:** Konfigurasi `output: 'standalone'` pada `next.config.ts` untuk server minimalis hemat memori VPS (kapasitas RAM 1 GB).

### ⚙️ C. Spesifikasi Backend (`AreaBE-PAD`)
* **Framework Utama:** `Laravel v12.56.0` (menggunakan `"laravel/framework": "^12.0"`)
* **PHP Compatibility:** `PHP ^8.3`
* **API Authentication:** `Laravel Sanctum ^4.2`
* **Excel Import/Export:** `phpoffice/phpspreadsheet ^5.2` dan `spatie/simple-excel ^3.8`
* **Ekstensi PHP Wajib (Docker):** `pdo_mysql`, `mbstring`, `zip`, `gd`, `exif`, `pcntl`, `bcmath`.

---

## 🧪 3. Penjelasan Test Suite (Pengujian Otomatis)
Pengujian otomatis ini dirancang dengan pendekatan **Behavior-Driven Development (BDD)** dan pola arsitektur **Page Object Model (POM)** untuk menjamin pemeliharaan kode (*maintainability*) yang baik.

* **Teknologi Utama:** Java 17, Maven, Selenium WebDriver, Cucumber BDD, JUnit 5.
* **Mode Browser:** *Visual (Headful Mode)* untuk demonstrasi langsung di layar.
* **Wait Strategy:** Jeda 3 detik (`Thread.sleep(3000)`) di setiap akhir skenario sebelum browser ditutup otomatis.
* **Automated Reporting:** Menggunakan **Masterthought Cucumber Reporting** untuk menghasilkan visual dashboard analitik premium di `target/cucumber-html-reports/cucumber-html-reports/overview-features.html`.

---

## 👥 4. Kontributor Proyek
Berikut adalah daftar anggota tim pengembang aplikasi SIPELITA:

<table align="center">
  <tr>
    <td align="center" width="200px">
      <a href="https://github.com/safiradrmd">
        <img src="https://github.com/safiradrmd.png" width="100px;" alt="Safira Dwita Ramadhani"/><br />
        <sub><b>Safira Dwita Ramadhani</b></sub>
      </a><br />
      NIM: 24/541969/SV/24981<br />
      <b>Project Manager (PM)</b>
    </td>
    <td align="center" width="200px">
      <a href="https://github.com/titoalla17">
        <img src="https://github.com/titoalla17.png" width="100px;" alt="Tito Alla Khairi"/><br />
        <sub><b>Tito Alla Khairi</b></sub>
      </a><br />
      NIM: 24/544463/SV/25424<br />
      <b>UI/UX Designer</b>
    </td>
    <td align="center" width="200px">
      <a href="https://github.com/Avin1731">
        <img src="https://github.com/Avin1731.png" width="100px;" alt="Hilarius Christiano Avin Paliling"/><br />
        <sub><b>Hilarius Christiano Avin</b></sub>
      </a><br />
      NIM: 24/542159/SV/25009<br />
      <b>Frontend Developer (FE)</b>
    </td>
    <td align="center" width="200px">
      <a href="https://github.com/Nazirii">
        <img src="https://github.com/Nazirii.png" width="100px;" alt="Muhammad Adib Naziri"/><br />
        <sub><b>Muhammad Adib Naziri</b></sub>
      </a><br />
      NIM: 24/540019/SV/24747<br />
      <b>Backend Developer (BE)</b>
    </td>
  </tr>
</table>

---

## 🛠️ 5. Pembagian Tugas Pengujian (Test Automation Roles)
*(Silakan edit tabel di bawah ini setelah berdiskusi dengan anggota kelompok)*

| Nama Anggota | NIM | Peran / Tugas Pengujian | Fitur yang Dikerjakan |
| :--- | :---: | :--- | :--- |
| **Safira Dwita Ramadhani** | `24/541969/SV/24981` | - | - |
| **Tito Alla Khairi** | `24/544463/SV/25424` | - | - |
| **Hilarius Christiano Avin** | `24/542159/SV/25009` | - | - |
| **Muhammad Adib Naziri** | `24/540019/SV/24747` | - | - |

---

## 📁 6. Struktur Repository
```text
.
├── LAPORAN_DAN_PRESENTASI.md   # Panduan presentasi & laporan bug tertulis
├── pom.xml                     # Konfigurasi Maven & Plugin Masterthought
├── README.md                   # Dokumentasi repositori utama
└── src
    ├── main
    │   └── resources
    │       └── features        # Berkas skenario Cucumber Gherkin (7 Fitur)
    │           ├── DeadlinePage.feature
    │           ├── HapusPusdatin.feature
    │           ├── HasilPenilaianModal.feature
    │           ├── PengisianIKLH.feature
    │           ├── TambahPusdatin.feature
    │           ├── VerifikasiIKLH.feature
    │           └── login.feature
    └── test
        └── java
            ├── pages           # Kelas Page Object Model (POM Selector & Action)
            │   ├── DeadlinePage.java
            │   ├── HapusPusdatinPage.java
            │   ├── HasilPenilaianPage.java
            │   ├── LoginPage.java
            │   ├── PengisianIKLHPage.java
            │   └── VerifikasiIKLHPage.java
            ├── runner          # JUnit Runner Config
            │   └── TestRunner.java
            └── steps           # Logika Step Definitions Selenium
                ├── DeadlineSteps.java
                ├── HapusPusdatinSteps.java
                ├── HasilPenilaianSteps.java
                ├── LoginSteps.java
                ├── PengisianIKLHSteps.java
                ├── TambahPusdatinSteps.java
                └── VerifikasiIKLHSteps.java
```
