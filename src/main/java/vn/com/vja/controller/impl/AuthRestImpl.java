package vn.com.vja.controller.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.vja.common.AbstractRest;
import vn.com.vja.controller.IAuthRest;
import vn.com.vja.dto.AuthDto;
import vn.com.vja.dto.AuthResultDto;
import vn.com.vja.dto.BaseRes;
import vn.com.vja.req.AuthReq;
import vn.com.vja.service.IAuthService;

/**
* AuthRestImpl
* @author QuangDK.
*/
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestImpl extends AbstractRest implements IAuthRest {

    private final IAuthService authService;

    @PostMapping("/login")
    @Override
    public BaseRes login(AuthReq req
            , BindingResult br
            , HttpServletRequest request, HttpServletResponse response) {
        try {

            AuthDto reqDto = mapper.convertValue(req, AuthDto.class);
            AuthResultDto resDto = authService.login(reqDto);
            return handleSuccess.handleSuccess(resDto);
        } catch (Exception e) {
            return handleError.handleError(e);
        }
    }
}