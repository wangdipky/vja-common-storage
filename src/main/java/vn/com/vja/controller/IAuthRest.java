package vn.com.vja.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import vn.com.vja.dto.BaseRes;
import vn.com.vja.req.AuthReq;

/**
* IAuthRest.
* @author QuangDK.
*/
@Tag(name = "Authenticate API")
public interface IAuthRest {

    @Operation(summary = "Verify user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server")
    })
    BaseRes login(@Parameter(name = "req", description = "Payload for request") @RequestBody AuthReq req
            , @Parameter(hidden = true) BindingResult br
            , @Parameter(hidden = true) HttpServletRequest request, @Parameter(hidden = true) HttpServletResponse response);
}