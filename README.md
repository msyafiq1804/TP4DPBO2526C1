# JANJI 
Saya Muhammad Syafiq A dengan NIM 2500254 mengerjakan TP 3 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin
# Struktur File
│   .gitignore
│   PeopleData.iml
│
├───.idea
│       .gitignore
│       misc.xml
│       modules.xml
│       uiDesigner.xml
│       vcs.xml
│       workspace.xml
│
├───out
│   └───production
│       └───PeopleData
│           │   PeopleMenu$1.class
│           │   PeopleMenu$2.class
│           │   PeopleMenu$3.class
│           │   PeopleMenu$4.class
│           │   PeopleMenu.class
│           │   Person.class
│           │
│           └───com
│               └───intellij
│                   └───uiDesigner
│                       └───core
│                               AbstractLayout.class
│                               DimensionInfo.class
│                               GridConstraints.class
│                               GridLayoutManager.class
│                               HorizontalInfo.class
│                               LayoutState.class
│                               Spacer.class
│                               SupportCode$TextWithMnemonic.class
│                               SupportCode.class
│                               Util.class
│                               VerticalInfo.class
│
└───src
        PeopleMenu.form
        PeopleMenu.java
        Person.java

# FUNGSI PROGRAM & PENJELASAN ALUR PROGRAM

## Tujuan dibuatnya program ini untuk mengolah data dari pasien yang terdaftar di rumah sakit atau klinik, meliputi identitas dasar hingga status perawatannya.

### 1. Struktur Class
Program ini menerapkan konsep enkapsulasi pada class modelnya. Seluruh variabel dideklarasikan sebagai private untuk melindungi data, sehingga proses membaca atau mengubah nilai harus dilakukan melalui method getter dan setter.
Atribut yang tersimpan meliputi:
- id (String)
- nama (String)
- tahunLahir (int)
- kategori (String)
- statusRawat (String)

### 2. Penjelasan Alur Program
Pengguna disediakan form input untuk mendaftarkan pasien baru, di mana mereka bisa mengetikkan ID, Nama, dan Tahun Lahir. Selain itu, terdapat menu dropdown untuk menentukan Kategori (BPJS, Umum, atau Asuransi) dan radio button untuk Status Rawat (Rawat Inap atau Rawat Jalan). Seluruh rekaman data akan langsung ditampilkan pada tabel di bagian bawah aplikasi yang bisa di-scroll.

Untuk melakukan proses edit (Ubah) atau hapus (Delete), pengguna wajib mengklik salah satu baris data di dalam tabel terlebih dahulu. Setelah diklik, data tersebut akan ditarik kembali ke form isian, tombol "Add" beralih fungsi menjadi "Update", dan tombol "Delete" yang sebelumnya bersembunyi akan muncul. Sebagai langkah pencegahan, ketika tombol "Delete" ditekan, sistem akan memunculkan kotak dialog konfirmasi (Yes/No) sebelum benar-benar menghapus data tersebut.

