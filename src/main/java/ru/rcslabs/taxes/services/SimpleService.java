package ru.rcslabs.taxes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rcslabs.taxes.dto.RequestDto;
import ru.rcslabs.taxes.repositories.TaxesRepo;
import java.util.List;

@Service("simpleService")
public class SimpleService {

    @Autowired
    TaxesRepo taxesRepo;

    public List getAll() {
        return taxesRepo.getAll();
    }

    public List getColumnA() {
        return taxesRepo.getColumnA();
    }

    public List getColumnB(String value) {
        return taxesRepo.getColumnB(value);
    }

    public List getColumnC() {
        return taxesRepo.getColumnC();
    }

    public List getColumnD(String value) {
        return taxesRepo.getColumnD(value);
    }

    public List getColumnY() {
        return taxesRepo.getColumnY();
    }

    public List getAllByParameters(RequestDto requestDto) {
        if (requestDto.getA() == null &&
                requestDto.getB() == null &&
                requestDto.getC() == null &&
                requestDto.getD() == null &&
                requestDto.getY() == null) {
            return taxesRepo.getAll();
        }
        return taxesRepo.getAllByParameters(requestDto);
    }
}
