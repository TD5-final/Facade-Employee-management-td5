package com.example.prog4.service;

import com.example.prog4.repository.PhoneCnapsRepository;
import com.example.prog4.repository.PhoneRepository;
import com.example.prog4.repository.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PhoneService {
    private PhoneRepository repository;

    public List<Phone> getAll(){
        return repository.findAll();
    }

    public Phone saveOne(Phone position){
        return repository.save(position);
    }
}
