package com.fif.latihan;

import com.fif.entity.CreditData;
import com.fif.services.impl.DatabaseCreditDataRepository;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

// REVISI 1: Wajib tambahkan Spring Variable Resolver agar bisa melakukan @WireVariable ke Repository
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyPage {

    @Wire("#myPage")
    private Window myPage;

    // REVISI 2: Migrasikan objek dari 'Credit' lama ke Entity Database 'CreditData'
    private CreditData credit;

    // REVISI 3: Suntikkan langsung Spring Data JPA Repository untuk akses update ke database
    @WireVariable
    private DatabaseCreditDataRepository databaseCreditDataRepository;

    @Init
    public void init(@ExecutionArgParam("credit") CreditData credit) {
        // Menerima data dari halaman SearchViewModel utama
        this.credit = credit;
    }

    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        System.out.println("-------------- MY PAGE ------------");
        if (myPage != null) {
            System.out.println("Window ID: " + myPage.getId());
        }
    }

    @Command
    public void update() {
        try {
            System.out.println("------------ UPDATE INFORMATION TO DATABASE -----------");
            System.out.println("ID   : " + credit.getId());
            System.out.println("NAMA : " + credit.getNamaLengkap());
            System.out.println("NIK  : " + credit.getNik());

            // REVISI 4: Simpan perubahan langsung ke database asli
            // Spring Data JPA secara cerdas akan mengeksekusi query SQL UPDATE karena ID credit ini sudah ada di database
            databaseCreditDataRepository.save(credit);

            // Mengambil instance komponen window utama (parent)
            Window parentWindow = (Window) myPage.getParent();

            // REVISI 5: Kirim event kembali ke parent agar halaman utama tahu proses update telah selesai
            Events.postEvent(new Event("onChildWindowClosed", parentWindow, credit));

            // Tutup jendela modal pop-up ini
            myPage.detach();

        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Gagal memperbarui data ke database: " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    @Destroy
    public void destroy() {
        System.out.println("---------- DESTROY ----------------");
        // Pembersihan resource jika dibutuhkan
    }

    // REVISI 6: Getter & Setter disesuaikan ke tipe entity 'CreditData'
    public CreditData getCredit() {
        return credit;
    }

    public void setCredit(CreditData credit) {
        this.credit = credit;
    }
}