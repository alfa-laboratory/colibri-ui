package ru.colibri.ui.core.settings;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class AppSettings {
    private final String packageName;
    private final String startPageId;
    private final boolean activityUse;
    private final Map<String, String> userProfile;
}
