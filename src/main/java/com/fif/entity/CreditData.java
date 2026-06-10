package com.fif.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "credit_data")
public class CreditData implements Serializable, Cloneable {
    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",
            parameters = @Parameter(name = "prefix", value = "prod"),
            strategy = "com.fif.util.MyGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "nama_lengkap", length = 150) // Perbaikan: Gunakan 'name', dan batasi panjang karakter jika perlu
    private String namaLengkap;

    @Column(name = "nik", length = 16, unique = true) // Bisa ditambah constraint unique agar NIK tidak duplikat
    private String nik;

    @Column(name = "no_telp", length = 20)
    private String noTelp;

    @Column(name = "rentan_gaji", length = 50)
    private String rentangGaji;

    @Column(name = "tujuan_pengajuan") // Jika tidak diberi name, hibernate otomatis pakai nama variabel, tapi lebih baik diset manual
    private String tujuanPengajuan;

    @Column(name = "plafon_pengajuan")
    private String plafonPengajuan;

    // --- Jangan lupa buat Default Constructor, Getter, dan Setter manualnya ---
    public CreditData() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    public String getNik() { return nik; }
    public void setNik(String nik) { this.nik = nik; }
    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }
    public String getRentangGaji() { return rentangGaji; }
    public void setRentangGaji(String rentangGaji) { this.rentangGaji = rentangGaji; }
    public String getTujuanPengajuan() { return tujuanPengajuan; }
    public void setTujuanPengajuan(String tujuanPengajuan) { this.tujuanPengajuan = tujuanPengajuan; }
    public String getPlafonPengajuan() { return plafonPengajuan; }
    public void setPlafonPengajuan(String plafonPengajuan) { this.plafonPengajuan = plafonPengajuan; }
}
