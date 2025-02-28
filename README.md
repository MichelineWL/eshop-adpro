#### Refleksi 1 

Dalam mengembangkan fitur baru dengan Spring Boot, saya belajar banyak tentang bagaimana membangun arsitektur yang rapi dengan memisahkan tugas di controller, service, dan repository. 
Saya jadi lebih paham pentingnya menjaga controller tetap ringan, menempatkan logika bisnis di service, dan mengelola data dengan baik di repository. 
Selain itu, saya juga mulai lebih terbiasa menulis kode yang lebih bersih dan mudah dipahami. 
Masih ada beberapa hal yang bisa diperbaiki, terutama dalam membuat kode lebih efisien dan mudah dikelola ke depannya.


#### Refleksi 3 

#### Prinsip yang Diterapkan
Pada proyek ini, prinsip SOLID telah diterapkan untuk memastikan kode lebih terstruktur dan mudah dikelola. Prinsip yang digunakan adalah:

1. **Single Responsibility Principle (SRP)**
   - Setiap kelas memiliki satu tanggung jawab utama agar lebih mudah dipahami dan diubah tanpa mempengaruhi bagian lain.
   - **Perubahan yang dilakukan:** Memisahkan fungsi yang berbeda dalam kelas `CarRepository` menjadi beberapa interface terpisah agar setiap kelas hanya memiliki satu tanggung jawab. Memisahkan `CarService` terlebih dahulu untukk membedakan method mana yang masuk ke CRUD, dan method mana yang hanya merupakan query. 

2. **Open/Closed Principle (OCP)**
   - Kelas dibuat agar mudah diperluas tanpa harus mengubah kode yang sudah ada.
   - **Perubahan yang dilakukan:** Menggunakan interface `CarRepositoryInterface` agar implementasi repositori bisa diperluas tanpa mengubah kode yang sudah ada.

3. **Liskov Substitution Principle (LSP)**
   - Subkelas dapat menggantikan kelas induk tanpa menyebabkan kesalahan.
   - **Perubahan yang dilakukan:** Memastikan bahwa setiap implementasi `CarRepositoryInterface` dapat digunakan tanpa mengubah perilaku program.

4. **Interface Segregation Principle (ISP)**
   - Antarmuka dipecah menjadi bagian yang lebih spesifik agar tidak ada metode yang tidak diperlukan dalam implementasi.
   - **Perubahan yang dilakukan:** Memecah `CarRepositoryInterface` menjadi beberapa antarmuka kecil seperti `CarModificationService` dan `CarQueryService` agar setiap kelas hanya mengimplementasikan metode yang diperlukan.

5. **Dependency Inversion Principle (DIP)**
   - Dependensi diarahkan pada abstraksi agar lebih fleksibel dan tidak bergantung langsung pada implementasi konkret.
   - **Perubahan yang dilakukan:** Menggunakan `CarRepositoryInterface` pada `CarQueryServiceImpl` dan `CarModificationServiceImpl` sehingga kode bergantung pada abstraksi, bukan implementasi konkret.

#### Keuntungan Menerapkan SOLID
1. **Kode lebih mudah dipelihara** - Perubahan dapat dilakukan pada satu bagian tanpa mempengaruhi seluruh sistem.
2. **Meningkatkan skalabilitas** - Proyek dapat dengan mudah dikembangkan karena kode tersusun dengan baik.
3. **Mencegah duplikasi kode** - Memisahkan tanggung jawab membantu menghindari kode yang berulang.
4. **Mudah diuji** - Dengan memisahkan logika ke dalam komponen kecil, pengujian menjadi lebih mudah dan efektif.

#### Kekurangan Jika Tidak Menerapkan SOLID
1. **Kode sulit dipahami** - Jika satu kelas memiliki banyak tanggung jawab, akan sulit untuk memahami fungsinya.
2. **Perubahan sulit dilakukan** - Mengubah satu bagian kode bisa mempengaruhi banyak bagian lain, meningkatkan risiko bug.
3. **Sulit untuk mengembangkan proyek** - Kode yang tidak terstruktur akan menyulitkan pengembang untuk menambahkan fitur baru tanpa merusak sistem yang sudah ada.

Dengan menerapkan prinsip SOLID, proyek ini menjadi lebih efisien, fleksibel, dan lebih mudah dikelola di masa depan.


