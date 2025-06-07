package com.example.controsha.controller;

import com.example.controsha.entity.Quest;
import com.example.controsha.entity.ShopOrder;
import com.example.controsha.entity.ShopProduct;
import com.example.controsha.repository.QuestRepository;
import com.example.controsha.repository.ShopOrderRepository;
import com.example.controsha.repository.ShopProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DashboardController {
    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private ShopOrderRepository shopOrderRepository;
    @Autowired
    private ShopProductRepository shopProductRepository;

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard(@RequestParam Integer userId) {
        Map<String, Object> result = new HashMap<>();
        System.out.println("userId = " + userId);
        // Квесты пользователя
        List<Quest> quests = questRepository.findByPersonIdContains(userId.toString());
        System.out.println("quests.size = " + quests.size());
        result.put("quests", quests);
        // Продукты пользователя
        List<ShopOrder> orders = shopOrderRepository.findByUserId(userId);
        System.out.println("orders.size = " + orders.size());
        List<Long> productIds = orders.stream().map(ShopOrder::getProductId).collect(Collectors.toList());
        List<ShopProduct> products = productIds.isEmpty() ? Collections.emptyList() : shopProductRepository.findAllById(productIds);
        result.put("products", products);
        return result;
    }
} 