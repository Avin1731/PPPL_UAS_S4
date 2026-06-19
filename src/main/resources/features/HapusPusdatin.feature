@tito
Feature: Hapus Akun Pusdatin

  Scenario: Sukses menghapus akun Pusdatin yang terdaftar
    Given User sudah login dan berada di halaman kelola akun pusdatin
    When User mengklik tombol Hapus pada akun "pusdatin@test.com"
    Then Sistem berhasil memperbarui daftar akun pusdatin