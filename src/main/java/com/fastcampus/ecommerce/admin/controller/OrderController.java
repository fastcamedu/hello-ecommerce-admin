package com.fastcampus.ecommerce.admin.controller;

import com.fastcampus.ecommerce.admin.domain.order.OrderDetailView;
import com.fastcampus.ecommerce.admin.exception.NotFoundOrderException;
import com.fastcampus.ecommerce.admin.service.OrderService;
import com.fastcampus.ecommerce.admin.service.dto.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class OrderController {

    public static final String ATTRIBUTE_KEY_ORDERS = "orders";
    private final OrderService orderService;

    @GetMapping({"/orders/", "/orders"})
    public String index(Model model) {
        List<OrderDetailView> orderDetailViews = orderService.findAllOrderDetailView();
        model.addAttribute("orders", orderDetailViews);
        return "/orders/orders";
    }

    @GetMapping("/orders/order-detail")
    public String detail(@RequestParam Long orderId, Model model) {
        Optional<OrderDTO> optionalOrderDTO = orderService.findById(orderId);
        OrderDTO orderDTO = optionalOrderDTO.orElseThrow(() -> new NotFoundOrderException("Not found order info"));
        model.addAttribute("order", orderDTO);
        return "/orders/order-detail";
    }
}
