package com.dtt.simulations.controller;

import com.dtt.simulations.dto.SimIssuanceDto;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.responseentity.AppUtil;
import com.dtt.simulations.service.Iface.TelecomSimIssuanceIface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TelecomSimIssuanceController {

    @Autowired
    TelecomSimIssuanceIface telecomSimIssuanceIface;
    @PostMapping("/api/post/save/sim/data")
    public ApiResponse saveSimData(@Valid @RequestBody SimIssuanceDto dto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            // Get first error message (or loop for all)
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return AppUtil.createApiResponse(false, errorMessage, null);
        }

        return telecomSimIssuanceIface.saveSimData(dto);
    }

}
