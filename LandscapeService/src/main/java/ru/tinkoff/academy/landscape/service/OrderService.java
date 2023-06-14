package ru.tinkoff.academy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.tinkoff.academy.landscape.data.OrderStatus;
import ru.tinkoff.academy.landscape.data.SortDirection;
import ru.tinkoff.academy.landscape.dto.OrderDto;
import ru.tinkoff.academy.landscape.exception.EntityNotFoundException;
import ru.tinkoff.academy.landscape.mapper.OrderMapper;
import ru.tinkoff.academy.landscape.model.Order;
import ru.tinkoff.academy.landscape.model.User;
import ru.tinkoff.academy.landscape.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final UserService userService;

    public Order create(OrderDto orderDto) {
        Order order = mapper.mapToEntity(orderDto);
        User user = userService.get(orderDto.getUserId());
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        return repository.save(order);
    }

    public Order get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("order", String.valueOf(id)));
    }

    public Page<Order> getPage(int page, int size, SortDirection sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return switch (sortDirection) {
            case ASC -> repository.findAllByOrderByUser_LastNameAsc(pageRequest);
            case DESC -> repository.findAllByOrderByUser_LastNameDesc(pageRequest);
            case NONE -> repository.findAllByOrderById(pageRequest);
        };
    }

    public Order update(Long id, OrderDto orderDto) {
        Order order = get(id);
        order.setFieldId(orderDto.getFieldId());
        order.setWorkType(order.getWorkType());
        User user = userService.get(orderDto.getUserId());
        order.setUser(user);
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
