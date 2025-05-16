package com.pharmainventory.catalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.devtools.restart.Restarter;

@Configuration
@Profile("dev")
public class DevToolsConfig {
    public DevToolsConfig() {
        Restarter.getInstance().getInitialUrls()
            .removeIf(url -> url.toString().contains("/generated-sources/"));
    }
}
