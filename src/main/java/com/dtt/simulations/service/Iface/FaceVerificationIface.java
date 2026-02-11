package com.dtt.simulations.service.Iface;


import com.dtt.simulations.dto.VerifyFaceDto;
import com.dtt.simulations.responseentity.ApiResponse;

public interface FaceVerificationIface {

    ApiResponse verifyFace(VerifyFaceDto verifyFaceDto);
}
