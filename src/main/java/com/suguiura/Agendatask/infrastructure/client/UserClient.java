package com.suguiura.Agendatask.infrastructure.client;

import com.suguiura.Agendatask.business.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping("/users")
    UserDTO findUserByEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);
}
