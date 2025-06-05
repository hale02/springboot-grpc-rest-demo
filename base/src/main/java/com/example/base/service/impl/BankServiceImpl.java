package com.example.base.service.impl;

import com.example.base.service.BankService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BankServiceImpl implements BankService {
    @Override
    public String layTiGiaHienTai(LocalDate dateNow, String tiGia) {
        if (LocalDate.now().equals(dateNow)) {
            return tiGia;
        }
        return "0";
    }
}
