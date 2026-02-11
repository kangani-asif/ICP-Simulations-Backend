package com.dtt.simulations.controller;


import com.dtt.simulations.dto.BankApproveRejectDto;
import com.dtt.simulations.dto.BankRequestDto;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.BuissinessAccountIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BuissinessAccountController {

    private static Logger logger = LoggerFactory.getLogger(BuissinessAccountController.class);

    /** The Constant CLASS. */
    final static String CLASS = "BuissinessAccountController";

    @Autowired
    BuissinessAccountIface buissinessAccountIface;

    @GetMapping("/api/get/servicestatus")
    public ApiResponse getServiceStatus() {
       return AppUtil.createApiResponse(true,"Service is running ",false);
    }


    @PostMapping("/api/post/save/bankAccount")
    public ApiResponse saveBuissinessBankAccount(@RequestBody String model) {
        try {

            return buissinessAccountIface.saveBuissinessBankAccount(model);
        } catch (Exception e) {
            // TODO: handle exception
            
            return AppUtil.createApiResponse(false,"Something went wrong",null);
        }
    }

    @PostMapping("/api/post/approve-reject/buisiness-account/{passport}/{status}")
    public ApiResponse approveOrRejectBuisinessAccount(@PathVariable String passport,@PathVariable String status){
        return  buissinessAccountIface.approveOrRejectBuisinessAccount(passport,status);
    }

    @GetMapping("/api/get/business-account/{passportNumber}")
    public ApiResponse getBuisinessAccount(@PathVariable String passportNumber) {
        return buissinessAccountIface.getBuisinessAccountByPassport(passportNumber);
    }

    @GetMapping("/api/get/bank-account/{id}")
    public ApiResponse getBuisinessAccountbyId(@PathVariable int id) {
        return buissinessAccountIface.getBuisinessAccountById(id);
    }

    @GetMapping("/api/get/all/business-account")
    public ApiResponse getAllBuisinessAccount() {
        return buissinessAccountIface.getAllBuisinessAccount();
    }


//    @PostMapping("/api/post/save/bankAccount/web")
//    public ApiResponse saveBankAccountThroughWeb(@RequestBody String json){
//        return buissinessAccountIface.saveBankAccountThroughWeb(json);
//    }


    @PostMapping("/api/get/userProfile")
    public ApiResponse getUserProfile(@RequestBody BankRequestDto bankRequestDto) {

        logger.info(CLASS + "getUserProfile >> Inside /api/get/userProfile");

        return buissinessAccountIface.getUserProfile(bankRequestDto);

    }

    @PostMapping("/api/approve-reject/bank/account")
    public ApiResponse approveBank(@RequestBody BankApproveRejectDto bankApproveRejectDto) {
        return buissinessAccountIface.approveRejectBankAccount(bankApproveRejectDto);
    }

    @GetMapping("/api/get/business-account/view/{id}")
    public ApiResponse getViewBuisinessAccount(@PathVariable int id) {
        return buissinessAccountIface.viewforApproveReject(id);
    }

    @PostMapping("/api/post/save/web/bankAccount")
    public ApiResponse saveBuissinessBankAccountWeb(@RequestBody String json) {

            return buissinessAccountIface.saveBankAccountOpeningWeb(json);

    }

}
