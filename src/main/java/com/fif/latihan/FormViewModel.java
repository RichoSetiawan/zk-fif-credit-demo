package com.fif.latihan;

import com.fif.entity.CreditData;
import com.fif.services.impl.DatabaseCreditDataRepository;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.UUID;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class FormViewModel {

    private CreditData credit;

    @WireVariable
    private DatabaseCreditDataRepository databaseCreditDataRepository;

    @Init
    public void init() {
        this.credit = new CreditData();
    }

    @Command
    public void submit() {
        try {

            databaseCreditDataRepository.save(credit);

            System.out.println("DATA BERHASIL DITAMBAHKAN KE DATABASE");

            Messagebox.show("Data Pengajuan Berhasil Disimpan ke Database!", "Sukses",
                    Messagebox.OK, Messagebox.INFORMATION, event -> {

                        Executions.sendRedirect("credit.zul");
                    });

            this.credit = new CreditData();

        } catch (Exception e) {
            e.printStackTrace();
            Messagebox.show("Gagal menyimpan data ke database: " + e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }

    public CreditData getCredit() {
        return credit;
    }

    public void setCredit(CreditData credit) {
        this.credit = credit;
    }
}

//public class FormViewModel {
//
//    private Credit credit = new Credit();
//
//    private CreditService creditService =
//            new CreditServiceImpl();
//
//    public Credit getCredit() {
//        return credit;
//    }
//
//    public void setCredit(Credit credit) {
//        this.credit = credit;
//    }
//
//    @Command
//    public void submit() {
//        // Generate ID unik untuk data baru
//        credit.setId(UUID.randomUUID().toString());
//
//        // Masuk ke dalam static list di CreditServiceImpl
//        creditService.add(credit);
//
//        System.out.println("DATA BERHASIL DITAMBAHKAN");
//
//        // Munculkan alert box sukses di browser user
//        org.zkoss.zul.Messagebox.show("Data Pengajuan Berhasil Disimpan!", "Sukses",
//                org.zkoss.zul.Messagebox.OK, org.zkoss.zul.Messagebox.INFORMATION, event -> {
//                    // Setelah klik OK, arahkan ke halaman monitoring/search credit
//                    org.zkoss.zk.ui.Executions.sendRedirect("credit.zul");
//                });
//
//        // Reset Form object agar bersih kembali
//        credit = new Credit();
//    }
//
////    @Command
////    public void submit() {
////
////        credit.setId(UUID.randomUUID().toString());
////
////        creditService.add(credit);
////
////        System.out.println("DATA BERHASIL DITAMBAHKAN");
////
////        credit = new Credit();
////    }
//}
