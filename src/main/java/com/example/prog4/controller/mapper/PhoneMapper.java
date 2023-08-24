package com.example.prog4.controller.mapper;

import com.example.prog4.model.exception.BadRequestException;
import com.example.prog4.repository.employee.PhoneRepository;
import com.example.prog4.repository.employee.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class PhoneMapper {
    private static final String JOIN_ELEMENT = ",";
    private PhoneRepository phoneCnapsRepository;

    public Phone toDomain(com.example.prog4.model.employee.Phone fromView, String employeeId) {
        String valueFromView = createPhoneValue(fromView.getValue(), fromView.getCountryCode());

        if(fromView.getId() == null){
            boolean isPhoneAlreadyExist = phoneCnapsRepository.isPhoneAlreadyExist(valueFromView);
            if (isPhoneAlreadyExist){
                throw new BadRequestException("The phone " + fromView.getValue() + " already exist.");
            }
            return phoneCnapsRepository.save(Phone.builder().value(valueFromView).build());
        }
        else {
            Optional<Phone> existPhone = phoneCnapsRepository.findById(fromView.getId());
            if (existPhone.isPresent() && !existPhone.get().getEmployee().getId().equals(employeeId)){
                throw new BadRequestException("The phone " + fromView.getValue() + " already used by another employee.");
            }
            return phoneCnapsRepository.save(Phone.builder().id(fromView.getId()).value(valueFromView).build());
        }
    }

    public com.example.prog4.model.employee.Phone toView(Phone fromDomain) {
        return com.example.prog4.model.employee.Phone.builder()
                .id(fromDomain.getId())
                .countryCode(getViewCountryCode(fromDomain.getValue()))
                .value(getViewValue(fromDomain.getValue()))
                .build();
    }

    public String createPhoneValue(String value, String countryCode) {
        return countryCode + JOIN_ELEMENT + value;
    }

    public String getViewValue(String value) {
        return value.split(JOIN_ELEMENT)[1];
    }

    public String getViewCountryCode(String value) {
        return value.split(JOIN_ELEMENT)[0];
    }
}
