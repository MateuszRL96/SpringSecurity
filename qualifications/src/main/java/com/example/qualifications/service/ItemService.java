package com.example.qualifications.service;


import com.example.qualifications.entity.QualificationItems;
import com.example.qualifications.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public QualificationItems save(QualificationItems items){
        return itemRepository.saveAndFlush(items);
    }

    public List<QualificationItems> getByOrder(QualificationItems items){
        return itemRepository.findOrderItemsByOrder(items);
    }
}

