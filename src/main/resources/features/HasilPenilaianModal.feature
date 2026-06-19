@naziri
Feature: Modal Alert Hasil Penilaian oleh DLH

  Scenario: Sukses menutup modal alert Belum Dimulai
    Given User masuk sebagai DLH dan berada di halaman Hasil Penilaian
    When Modal alert "BELUM DIMULAI" muncul di layar
    And User mengklik tombol Mengerti untuk menutup alert
    Then Modal alert harus tertutup dari tampilan layar
