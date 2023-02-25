package ru.tinkoff.academy.handyman;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuildInfo {
    private String artifact;
    private String name;
    private String group;
    private String version;
}