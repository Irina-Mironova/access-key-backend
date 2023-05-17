package com.example.accesskeybackend.template.controller;

import com.example.accesskeybackend.template.service.IPv6Service;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/web/checkIpv6Support")
@AllArgsConstructor
public class IPv6Controller {

    private final IPv6Service iPv6Service;

    @PostMapping
    public Boolean checkSiteByIP(
            @Parameter(description = "domain or URI", required = true)
            @RequestBody String site) {
        return iPv6Service.checkSiteByIP(site);

    }
}
