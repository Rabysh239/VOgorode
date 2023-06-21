package ru.tinkoff.academy.filler.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RancherStatistic {
    private String login;
    private Double minArea;
    private Double maxArea;
    private Double averageArea;
}
