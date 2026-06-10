package com.fif.services.impl;

import com.fif.entity.CreditData;
import java.util.*;
import com.fif.latihan.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseCreditDataRepository extends JpaRepository<CreditData, String> {
    List<CreditData> findAll();
    List<CreditData> findByNamaLengkapContainingIgnoreCaseOrNikContaining(String nama, String nik);

    void delete(String nama);
}
