### Refleksi 1 

Dalam mengembangkan fitur baru dengan Spring Boot, saya belajar banyak tentang bagaimana membangun arsitektur yang rapi dengan memisahkan tugas di controller, service, dan repository. 
Saya jadi lebih paham pentingnya menjaga controller tetap ringan, menempatkan logika bisnis di service, dan mengelola data dengan baik di repository. 
Selain itu, saya juga mulai lebih terbiasa menulis kode yang lebih bersih dan mudah dipahami. 
Masih ada beberapa hal yang bisa diperbaiki, terutama dalam membuat kode lebih efisien dan mudah dikelola ke depannya.

==============================================================================================================================================================================================

### Refleksi 3 

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

### Sebelum Menerapkan SOLID (Tanpa SOLID)
SRP tidak diterapkan → CarRepository menangani semua operasi (CRUD) dalam satu kelas.
OCP tidak diterapkan → Jika ingin menambah penyimpanan database lain, kita harus mengubah CarRepository.
ISP tidak diterapkan → Semua metode repositori ada dalam satu interface, meskipun tidak semua implementasi membutuhkannya.
DIP tidak diterapkan → CarQueryServiceImpl dan CarModificationServiceImpl langsung bergantung pada InMemoryCarRepository, bukan abstraksi.

##### Pada kode : 
public class CarRepository {  
    private List<Car> carData = new ArrayList<>();  

    public Car create(Car car) { carData.add(car); return car; }  
    public Iterable<Car> findAll() { return carData; }  
    public Car findById(String id) { /* mencari berdasarkan id */ }  
    public Car update(String id, Car updatedCar) { /* update data */ }  
    public void deleteById(String id) { /* hapus data */ }  
}

### Setelah Menerapkan SOLID
#### - SRP & ISP: Memisahkan Interface Berdasarkan Tanggung Jawab
public interface CarQueryService {
    List<Car> findAll();
    Car findById(String id);
}

public interface CarModificationService {
    Car create(Car car);
    void update(String id, Car car);
    void deleteById(String id);
}
#### - OCP & DIP: Gunakan Abstraksi untuk Repositori (Yang kemudian dipecah berdasarkan CRUD dan query class)
public interface CarRepositoryInterface {
    Car create(Car car);
    Iterable<Car> findAll();
    Car findById(String id);
    Car update(String id, Car updatedCar);
    void deleteById(String id);
}
#### - Implementasi yang Sesuai dengan Abstraksi
public class InMemoryCarRepository implements CarRepositoryInterface {
    private List<Car> carData = new ArrayList<>();
    // Implementasi CRUD sesuai kontrak interface
}
#### - Dependency Inversion: Service Bergantung pada Interface, Bukan Implementasi Langsung
@Service
public class CarQueryServiceImpl implements CarQueryService {
    private final CarRepositoryInterface carRepository;
    
    @Autowired
    public CarQueryServiceImpl(CarRepositoryInterface carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() { return new ArrayList<>(carRepository.findAll()); }
    public Car findById(String carId) { return carRepository.findById(carId); }
}

==============================================================================================================================================================================================

### Refleksi 4

1. Dalam proses Test-Driven Development (TDD) yang aku lakukan, aku menyadari bahwa pendekatan ini sangat membantu dalam memahami ekspektasi terhadap kode sebelum implementasi dimulai. Dengan menulis uji terlebih dahulu, aku bisa lebih jelas menentukan bagaimana seharusnya `OrderServiceImpl` bekerja, sehingga menghindari kesalahan desain atau implementasi yang tidak perlu. Selain itu, dengan adanya tahap *RED* (uji gagal terlebih dahulu), aku bisa lebih cepat menemukan potensi masalah sebelum kode benar-benar diimplementasikan. Ketika akhirnya aku menulis kode untuk membuat uji berhasil (*GREEN*), aku merasa lebih percaya diri bahwa fitur yang dibuat memang sudah sesuai dengan kebutuhan.  

Meskipun proses ini cukup efektif, aku merasa masih ada beberapa hal yang perlu diperbaiki di lain waktu. Misalnya, aku bisa menambahkan lebih banyak variasi pengujian, terutama untuk menangani skenario tak terduga seperti input yang tidak valid, duplikasi data, atau kesalahan yang harus ditangani dengan pengecualian. Selain itu, aku bisa menggunakan teknik seperti *Parameterized Tests* agar pengujian lebih ringkas dan menghindari kode uji yang berulang. Aku juga menyadari pentingnya menguji kinerja kode, misalnya dengan melihat bagaimana `OrderServiceImpl` menangani jumlah pesanan yang besar.  

2. Untuk evaluasi pengujian yang sudah aku buat berdasarkan prinsip F.I.R.S.T., sebagian besar sudah terpenuhi. Pengujian berjalan dengan cepat karena hanya menggunakan struktur data sederhana tanpa bergantung pada sistem eksternal seperti database. Setiap uji juga bersifat mandiri, artinya hasil dari satu pengujian tidak memengaruhi pengujian lainnya. Selain itu, pengujian yang aku buat bisa dijalankan berulang kali dengan hasil yang konsisten, dan setiap uji memiliki *assertion* yang jelas untuk menentukan keberhasilannya tanpa analisis tambahan. Namun, aku merasa masih perlu meningkatkan aspek *Timely*, yaitu memastikan bahwa semua uji benar-benar ditulis sebelum implementasi fitur dimulai, terutama untuk kasus-kasus ekstrem yang mungkin terjadi.  



