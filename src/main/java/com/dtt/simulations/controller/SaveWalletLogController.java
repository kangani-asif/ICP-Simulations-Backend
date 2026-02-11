package com.dtt.simulations.controller;

import com.dtt.simulations.dto.WalletTranscationDto;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.service.Iface.WalletLogIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SaveWalletLogController {
    @Autowired
    WalletLogIface savewalletLog;

    @PostMapping("/api/save/wallet-log")
    public ApiResponse saveWalletLog(@RequestBody WalletTranscationDto walletTranscationDto) {
        return savewalletLog.savewalletLog(walletTranscationDto);
    }

}
