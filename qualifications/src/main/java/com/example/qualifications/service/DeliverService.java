package com.example.qualifications.service;



import com.example.qualifications.entity.DeliverDTO;
import com.example.qualifications.repo.DeliverRepository;
import com.example.qualifications.translators.DeliverToDeliverDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliverService {

    private DeliverRepository deliverRepository;
    private DeliverToDeliverDTO deliverToDeliverDTO;

    public List<DeliverDTO> getAllDeliver() {
        return deliverRepository.findAll().stream().map(deliverToDeliverDTO::deliverDTO).collect(Collectors.toList());
    }
}

