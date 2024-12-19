package com.example.qualifications.service;


import com.example.koszyk.entity.KoszykItems;
import com.example.qualifications.entity.Qualification;
import com.example.qualifications.entity.QualificationItems;
import com.example.qualifications.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private ItemRepository itemRepository;

    public QualificationItems save(QualificationItems items){
        return itemRepository.saveAndFlush(items);
    }

    public List<QualificationItems> getByQualification(Qualification items){
        return itemRepository.findQualificationItemsByQualification(items);
    }
}

