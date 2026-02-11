package com.dtt.simulations.service.Iface;


import com.dtt.simulations.model.MoneyTransfer;
import com.dtt.simulations.responseentity.ApiResponse;

public interface MoneyTransferIface {

    ApiResponse getAllMoneyTransfer();

    ApiResponse getMoneyTransferById(int id);

    ApiResponse saveMoneyTransfer(MoneyTransfer moneyTransfer);

}
