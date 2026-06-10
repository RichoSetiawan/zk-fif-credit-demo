package com.fif.latihan;

import com.fif.entity.CreditData;
import com.fif.services.impl.DatabaseCreditDataRepository;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// MENGAKTIFKAN RESOLVER: Agar ZK dapat menginjeksi komponen Spring Bean Repository
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SearchViewModel {

    @Wire("#searchWindow")
    private Window parentWindow;

    private String keyword;

    // Menggunakan entity CreditData untuk data binding ke layar tabel
    private ListModelList<CreditData> creditList = new ListModelList<>();
    private CreditData selectedCredit;

    // SUNTIK DATA: Menghubungkan langsung ke layer repositori database real-time
    @WireVariable
    private DatabaseCreditDataRepository databaseCreditDataRepository;

    @Init
    public void init() {
        // Load data tabel awal secara langsung dari database terpusat
        search();
    }

    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);

        // Event Listener: Mendengar sinyal penutupan halaman modal edit (mypage.zul)
        parentWindow.addEventListener("onChildWindowClosed", new EventListener<Event>() {
            @Override
            public void onEvent(Event event) {
                System.out.println("------------------ REFRESH THE PARENT -------------");

                // Refresh data grid agar langsung mencerminkan data ter-update di database
                search();
            }
        });
    }

    @Command
    @NotifyChange({"creditList", "selectedCredit"})
    public void search() {
        List<CreditData> searchResult;

        if (keyword == null || keyword.trim().isEmpty()) {
            // Jika pencarian kosong, ambil semua baris record di tabel DB
            searchResult = databaseCreditDataRepository.findAll();
        } else {
            // Gunakan pencarian filter teroptimasi database (Menggunakan query custom Spring JPA)
            searchResult = databaseCreditDataRepository.findByNamaLengkapContainingIgnoreCaseOrNikContaining(keyword, keyword);
        }

        refreshDataTabel(searchResult);
        selectedCredit = null; // Reset item aktif pasca pencarian
    }

    @Command
    @NotifyChange({"creditList", "selectedCredit"})
    public void delete() {
        if (selectedCredit == null) {
            Messagebox.show("Silakan pilih baris data pada tabel terlebih dahulu sebelum menghapus!", "Peringatan", Messagebox.OK, Messagebox.EXCLAMATION);
            return;
        }

        Messagebox.show("Apakah Anda yakin ingin menghapus data pengajuan milik: " + selectedCredit.getNamaLengkap() + "?",
                "Konfirmasi Hapus", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, event -> {
                    if (Messagebox.ON_YES.equals(event.getName())) {

                        // PROSES DELETE: Menembakkan query DELETE FROM credit_data WHERE id = ?
                        databaseCreditDataRepository.delete(selectedCredit);

                        System.out.println("DATA DI DATABASE BERHASIL DIHAPUS");

                        // Tarik ulang data struktural terbaru ke layar monitor
                        search();
                    }
                });
    }

    @Command
    public void update(@BindingParam("selectedCredit") CreditData creditToUpdate) {
        if (creditToUpdate == null) return;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("credit", creditToUpdate);

        // Membuka modal pop-up pembaruan data
        Window window = (Window) Executions.createComponents("mypage.zul", parentWindow, parameters);
        window.doModal();
    }

    private void refreshDataTabel(List<CreditData> targetList) {
        this.creditList.clear();
        if (targetList != null) {
            this.creditList.addAll(targetList);
        }
    }

    // --- GETTER & SETTER BERBASIS ENTITY DATABASE ---
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ListModelList<CreditData> getCreditList() {
        return creditList;
    }

    public CreditData getSelectedCredit() {
        return selectedCredit;
    }

    public void setSelectedCredit(CreditData selectedCredit) {
        this.selectedCredit = selectedCredit;
    }
}