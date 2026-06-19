@safira
Feature: Tambah Akun Pusdatin

  Scenario: Sukses menambah akun Pusdatin baru dengan data valid
    Given User sudah login dan berada di halaman tambah akun pusdatin
    When User mengisi nama "Admin Pusdatin Baru", email "pusdatin_baru@test.com", dan nomor HP "08123456789"
    And User memasukkan password "password123" dan konfirmasi password "password123"
    And User mengklik tombol Simpan Akun
    Then Sistem berhasil menyimpan akun dan kembali ke daftar pusdatin