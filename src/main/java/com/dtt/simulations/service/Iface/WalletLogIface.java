package com.dtt.simulations.service.Iface;

import com.dtt.simulations.dto.WalletTranscationDto;
import com.dtt.simulations.responseentity.ApiResponse;

public interface WalletLogIface {
    ApiResponse savewalletLog(WalletTranscationDto walletTranscationDto);

}
