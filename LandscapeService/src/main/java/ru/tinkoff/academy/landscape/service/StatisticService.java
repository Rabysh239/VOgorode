package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.client.HandymanClient;
import ru.tinkoff.academy.landscape.client.RancherClient;
import ru.tinkoff.academy.landscape.data.HandymanPaymentSystemStatistic;
import ru.tinkoff.academy.landscape.data.PageDto;
import ru.tinkoff.academy.landscape.data.RancherStatistic;
import ru.tinkoff.academy.landscape.dto.RancherStatisticDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RancherClient rancherClient;
    private final HandymanClient handymanClient;

    public List<HandymanPaymentSystemStatistic> getHandymanPaymentSystemStatistics() {
        return handymanClient.getPaymentSystemStatistics();
    }

    public Page<RancherStatistic> getRancherStatistics(int page, int size) {
        PageDto<RancherStatisticDto> pageDto = rancherClient.getStatistics(page, size);
        List<RancherStatistic> statistics = pageDto.getContent().stream()
                .map(it -> {
                    RancherStatistic statistic = modelMapper.map(it, RancherStatistic.class);
                    String login = userService.get(it.getUserId()).getLogin();
                    statistic.setLogin(login);
                    return statistic;
                })
                .toList();
        return new PageImpl<>(statistics, pageDto.getPageable(), pageDto.getTotalElements());
    }
}
