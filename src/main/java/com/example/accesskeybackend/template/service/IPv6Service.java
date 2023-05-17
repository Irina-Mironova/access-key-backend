package com.example.accesskeybackend.template.service;

import com.example.accesskeybackend.exception.IllegalArgumentException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.*;

@Service
@AllArgsConstructor
public class IPv6Service {

    public boolean checkSiteByIP(String site) {

        if (!isValidSite(site)) {
            throw new IllegalArgumentException(String.format("Wrong format of domain %s", site));
        }

        String domain = getDomain(site);

        try {
            InetAddress[] addresses = InetAddress.getAllByName(domain);

            for (InetAddress address : addresses) {
                if (address instanceof Inet6Address) {
                    return true;
                }
            }
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(String.format("Wrong format of domain %s", site));
        }


        return false;
    }

    private boolean isValidSite(String site) {
        String patternUrl = "^((https?|ftp)://)?([a-z0-9-]+\\.)+[a-z]{2,}(\\.?[a-z]{2,})?(/.*)?$";
        String patternDomain = "^([a-z0-9-]+\\.)+[a-z]{2,}(\\.?[a-z]{2,})?$";

        return site.matches(patternUrl) || site.matches(patternDomain);
    }

    private String getDomain(String site) {
        try {
            URL url = new URL(site);
            return url.getHost();
        } catch (MalformedURLException ex) {
            return site;
        }
    }

}
