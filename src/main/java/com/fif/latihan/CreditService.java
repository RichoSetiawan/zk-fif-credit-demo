package com.fif.latihan;

import java.util.List;

public interface CreditService {
    List<Credit> findAll();

    List<Credit> search(String keyword);

    void add(Credit credit);

    void update(Credit credit);
}
