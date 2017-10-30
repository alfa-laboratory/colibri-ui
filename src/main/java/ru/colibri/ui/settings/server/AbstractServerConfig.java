package ru.colibri.ui.settings.server;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractServerConfig {

    private String port;
    private String host;

}
