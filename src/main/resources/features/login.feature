Feature: Menguji Halaman Login SIPELITA Local

  Scenario: Login sukses sebagai Admin (Equivalence Partitioning Valid)
    Given User membuka halaman login SIPELITA local di "https://area-fe-pad.vercel.app/login"
    When User mengetik email "admin@test.com" dan password "password"
    And User mengklik tombol "Login"
    Then User harus melihat halaman Dashboard Admin utama

  Scenario: Login gagal karena password salah (Equivalence Partitioning Invalid)
    Given User membuka halaman login SIPELITA local di "https://area-fe-pad.vercel.app/login"
    When User mengetik email "admin@test.com" dan password "salah123"
    And User mengklik tombol "Login"
    Then User harus melihat pesan error "Kredensial tidak cocok"