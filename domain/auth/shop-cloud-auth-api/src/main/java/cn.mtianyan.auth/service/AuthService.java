package cn.mtianyan.auth.service;

import cn.mtianyan.auth.service.pojo.Account;
import cn.mtianyan.auth.service.pojo.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 半仙.
 */
@FeignClient("foodie-auth-service")
@RequestMapping("auth-service")
public interface AuthService {

    @PostMapping("token")
    public AuthResponse tokenize(@RequestParam("userId") String userId);

    @PostMapping("verify")
    public AuthResponse verify(@RequestBody Account account);

    @PostMapping("refresh")
    public AuthResponse refresh(@RequestParam("refresh") String refresh);

    @DeleteMapping("delete")
    public AuthResponse delete(@RequestBody Account account);

}
