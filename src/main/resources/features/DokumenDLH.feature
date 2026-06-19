@tito
Feature: Manajemen Dokumen DLH

  Scenario: User DLH berhasil mengunduh template dokumen
    Given User masuk sebagai DLH dan berada di halaman Unduh Template Dokumen
    When user menekan tombol unduh template
    Then file template berhasil diunduh ke direktori lokal