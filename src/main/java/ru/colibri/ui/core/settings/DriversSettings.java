package ru.colibri.ui.core.settings;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class DriversSettings {
    private String appiumRemoteUrl;
    private String deviceName;
    private String filePath;
    private int findingTimeOutInSeconds;
    private int newCommandTimeoutInSeconds;
    private int implicitlyWaitInSeconds;
    private String storyTimeoutsInSeconds;
    private List<String> stepsPackages;
    private String storyPath;
    private String storyToInclude;
    private String storyToExclude;
    private String pagesPath;
    private String UDID;
    private int wdaLocalPort;
    private int systemPort;
}
