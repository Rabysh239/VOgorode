package ru.tinkoff.academy.landscape.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.academy.landscape.data.SortDirection;
import ru.tinkoff.academy.landscape.dto.OrderDto;
import ru.tinkoff.academy.landscape.model.Order;
import ru.tinkoff.academy.landscape.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @PostMapping
    public Order create(@RequestBody OrderDto orderDto) {
        return service.create(orderDto);
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/page")
    public Page<Order> getPage(
            @RequestParam int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "NONE") SortDirection sortDirection) {
        return service.getPage(page, size, sortDirection);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return service.update(id, orderDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
