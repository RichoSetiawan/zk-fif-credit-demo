package com.fif.latihan;

public class Credit {
    private String id;
    private String namaLengkap;
    private String nik;
    private String noTelp;
    private String rentangGaji;
    private String tujuanPengajuan;
    private String plafonPengajuan;

    // Constructor
    public Credit(String id, String namaLengkap, String nik, String noTelp, String rentangGaji, String tujuanPengajuan, String plafonPengajuan){
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.nik = nik;
        this.noTelp = noTelp;
        this.rentangGaji = rentangGaji;
        this.tujuanPengajuan = tujuanPengajuan;
        this.plafonPengajuan = plafonPengajuan;
    }

    public Credit() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNoTelp() {
        return noTelp;
    }

    // PERBAIKAN 1: Nama parameter disesuaikan dan value di-assign dengan benar
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getRentangGaji() {
        return rentangGaji;
    }
    public void setRentangGaji(String rentangGaji) {
        this.rentangGaji = rentangGaji;
    }

    public String getTujuanPengajuan() {
        return tujuanPengajuan;
    }
    public void setTujuanPengajuan(String tujuanPengajuan) {
        this.tujuanPengajuan = tujuanPengajuan;
    }

    public String getPlafonPengajuan() {
        return plafonPengajuan;
    }

    // PERBAIKAN 2: Diubah dari getPlafonPengajuan menjadi setPlafonPengajuan (Void Setter)
    public void setPlafonPengajuan(String plafonPengajuan) {
        this.plafonPengajuan = plafonPengajuan;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Credit)) return false;
        Credit other = (Credit) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        return true;
    }
}