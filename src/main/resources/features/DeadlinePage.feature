Feature: Manajemen Deadline SIPELITA

  Background: User sudah login sebagai Admin Pusdatin
    Given User sudah login dan berada di halaman "Pengaturan Deadline"

  # 🟢 POSITIVE CASE
  Scenario: Sukses mengatur deadline baru dengan data valid
    When User mengisi tanggal "15-07-2026", waktu "23:59", dan catatan "Test Deadline Q3"
    And User mengklik tombol Simpan Deadline
    Then Sistem harus menampilkan pesan sukses "Deadline berhasil disimpan"

  # 🔴 NEGATIVE CASE
  Scenario: Gagal mengatur deadline karena tanggal dikosongkan
    When User mengosongkan bagian tanggal namun mengisi waktu "12:00" dan catatan "Tanpa Tanggal"
    And User mengklik tombol Simpan Deadline
    Then Sistem harus menolak dan menampilkan pesan error "Tanggal deadline wajib diisi"