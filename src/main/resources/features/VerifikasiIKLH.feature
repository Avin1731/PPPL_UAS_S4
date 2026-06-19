@avin
Feature: Verifikasi IKLH oleh Pusdatin

  Scenario: Sukses melakukan verifikasi Terima pada data IKLH Kabupaten Aceh Barat
    Given User masuk sebagai Pusdatin dan berada di halaman Penerimaan SLHD Kab/Kota
    When User mengklik tab IKLH
    And User mengklik tombol Terima pada baris data "Kabupaten Aceh Barat"
    Then Sistem harus memperbarui status verifikasi data IKLH tersebut
