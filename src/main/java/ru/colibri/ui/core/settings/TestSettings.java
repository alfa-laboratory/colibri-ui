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
public class TestSettings {
    private List<String> flagsMetaFilters;
}
