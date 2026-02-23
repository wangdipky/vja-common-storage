package vn.com.vja.service;

import vn.com.vja.dto.AuthDto;
import vn.com.vja.dto.AuthResultDto;

/**
* IAuthService
* @author QuangDK.
*/
public interface IAuthService {

    AuthResultDto login(AuthDto authDto);
}