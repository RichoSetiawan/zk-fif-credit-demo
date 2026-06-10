package com.fif.latihan;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreditServiceImpl implements CreditService {
    private static List<Credit> creditList = new LinkedList<>();

    static {

        creditList.add(
                new Credit(
                        UUID.randomUUID().toString(),
                        "Mana",
                        "38349438792387",
                        "08281348382988",
                        "5 juta",
                        "Modal",
                        "20.000.000"));

        creditList.add(
                new Credit(
                        UUID.randomUUID().toString(),
                        "Bambang",
                        "38349438792387",
                        "08281348382988",
                        "5 juta",
                        "Modal",
                        "20.000.000"));

        creditList.add(
                new Credit(
                        UUID.randomUUID().toString(),
                        "Riku",
                        "38349438792387",
                        "08281348382988",
                        "5 juta",
                        "Modal",
                        "20.000.000"));
    }

    @Override
    public List<Credit> findAll() {
        return creditList;
    }

    @Override
    public List<Credit> search(String keyword) {

        return creditList.stream()
                .filter(cr ->
                        keyword == null
                                || keyword.isEmpty()
                                || cr.getNamaLengkap().toLowerCase().contains(keyword.toLowerCase())
                                || cr.getNik().contains(keyword))
                .collect(Collectors.toList());
    }

    @Override
    public void add(Credit credit) {
        creditList.add(credit);
    }

    @Override
    public void update(Credit credit) {

        for (int i = 0; i < creditList.size(); i++) {

            if (creditList.get(i).getId().equals(credit.getId())) {

                creditList.set(i, credit);
                break;
            }
        }
    }
}
