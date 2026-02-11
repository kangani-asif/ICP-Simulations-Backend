package com.dtt.simulations.service.Iface;


import com.dtt.simulations.dto.BankApproveRejectDto;
import com.dtt.simulations.dto.BankRequestDto;
import com.dtt.simulations.dto.FileSigningDto;
import com.dtt.simulations.responseentity.ApiResponse;

public interface BuissinessAccountIface {

    ApiResponse saveBuissinessBankAccount(String businessAccountDto);


    ApiResponse approveOrRejectBuisinessAccount(String passport, String status);

    ApiResponse getBuisinessAccountByPassport(String passportNumber);

    ApiResponse getAllBuisinessAccount();

    ApiResponse getBuisinessAccountById(int id);
    ApiResponse getUserProfile(BankRequestDto bankRequestDto);

    ApiResponse approveRejectBankAccount(BankApproveRejectDto bankApproveRejectDto);

    ApiResponse viewforApproveReject(int id);

    ApiResponse saveBankAccountOpeningWeb(String bankAccountDto);

    ApiResponse fileSigning(FileSigningDto fileSigningDto);





//    ApiResponse saveBankAccountThroughWeb(String json);
}
