@avin
Feature: Pengisian Nilai IKLH oleh DLH

  Scenario: Sukses menyimpan perubahan nilai IKLH
    Given User masuk sebagai DLH dan berada di halaman unggah nilai IKLH
    When User mengisi nilai Indeks Kualitas Air "95" dan Indeks Kualitas Udara "90"
    And User mengklik tombol Simpan Perubahan Nilai
    Then Sistem harus berhasil memproses penyimpanan data nilai IKLH
