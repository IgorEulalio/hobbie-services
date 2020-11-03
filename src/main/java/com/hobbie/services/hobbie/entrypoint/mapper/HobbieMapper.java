package com.hobbie.services.hobbie.entrypoint.mapper;

import com.hobbie.services.hobbie.dataprovider.repository.entity.Hobbie;
import com.hobbie.services.hobbie.entrypoint.dto.HobbieDTO;
import org.springframework.stereotype.Component;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HobbieMapper {

    public Hobbie toModel(HobbieDTO dto) throws InvocationTargetException, IllegalAccessException {

        Hobbie hobbie = new Hobbie();

        BeanUtils.copyProperties(hobbie, dto);
        return hobbie;
    }

    public HobbieDTO toDto(Hobbie model) throws InvocationTargetException, IllegalAccessException {

        var dto = new HobbieDTO();

        BeanUtils.copyProperties(dto, model);
        return dto;
    }

    public List<Hobbie> toModels(List<HobbieDTO> dtos) {

        List<Hobbie> hobbies = new ArrayList<>();

        dtos.forEach(dto -> {
            Hobbie hobbie = new Hobbie();
            try {
                BeanUtils.copyProperties(hobbie, dto);
                hobbies.add(hobbie);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        return hobbies;
    }
}
