package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.LaptopAttributeDTO;
import com.example.springjpa.model.Attribute;
import com.example.springjpa.model.Laptop;
import com.example.springjpa.model.LaptopAttribute;
import com.example.springjpa.model.LaptopAttributeId;
import com.example.springjpa.repository.AttributeRep;
import com.example.springjpa.repository.LapTopRep;
import com.example.springjpa.repository.LaptopAttributeRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopAttributeService {
    @Autowired
    AttributeRep attributeRep;
    @Autowired
    LaptopAttributeRep laptopAttributeRep;
    @Autowired
    LapTopRep lapTopRep;
    public LaptopAttributeDTO toModelLaptopAttributeDTO(LaptopAttribute laptopAttribute) {
            LaptopAttributeDTO attributeDTO = new LaptopAttributeDTO();
            attributeDTO.setChip(laptopAttribute.getAttribute().getChip());
            attributeDTO.setRam(laptopAttribute.getAttribute().getRam());
            attributeDTO.setSSD(laptopAttribute.getAttribute().getSSD());
            attributeDTO.setVGA(laptopAttribute.getAttribute().getVGA());
            attributeDTO.setNameLaptop(laptopAttribute.getLaptop().getNameLaptop());
            attributeDTO.setIdLaptop(laptopAttribute.getLaptop().getId());
           return  attributeDTO;
    }
    public LaptopAttributeDTO addNewLaptop(LaptopAttributeDTO laptopAttributeDTO){
      Optional<Laptop> laptop = lapTopRep.findById(laptopAttributeDTO.getIdLaptop());
         if (!laptop.isPresent())
         {
             // creat a laptop instert database
             Laptop laptop1 = new Laptop();
             laptop1.setNameLaptop(laptopAttributeDTO.getNameLaptop());
             lapTopRep.save(laptop1);
             // b2
             Attribute attribute = new Attribute();
             attribute.setChip(laptopAttributeDTO.getChip());
             attribute.setVGA(laptopAttributeDTO.getVGA());
             attribute.setSSD(laptopAttributeDTO.getSSD());
             attribute.setRam(laptopAttributeDTO.getRam());
             attributeRep.save(attribute);
             // thêm lớp chiều tượng id
             LaptopAttributeId laptopAttributeId = new LaptopAttributeId();
             laptopAttributeId.setLaptopid(laptop1.getId());
             laptopAttributeId.setAttributeid(attribute.getId());
             // add tg
             LaptopAttribute laptopAttribute = new LaptopAttribute();
             laptopAttribute.setLaptopAttributeId(laptopAttributeId);
             laptopAttribute.setLaptop(laptop1);
             laptopAttribute.setAttribute(attribute);
             laptopAttribute.setDescription(laptopAttributeDTO.getDescription());
             laptopAttributeRep.save(laptopAttribute);
             return  laptopAttributeDTO;
         }else {
             return null;
         }

    }
    public List<LaptopAttributeDTO> laptopAttributeDTOS (){
       List<LaptopAttributeDTO>laptopAttributeDTOS = laptopAttributeRep.findAll()
               .stream().map(this::toModelLaptopAttributeDTO).collect(Collectors.toList());
       return laptopAttributeDTOS;
    }
    public LaptopAttributeDTO update(  Integer id, LaptopAttributeDTO laptopAttributeDTO){

           try {
               Optional<Laptop> laptop = lapTopRep.findById(id);
               if (laptop.isPresent()){
                   Laptop laptop1   = laptop.get();
                   laptop1.setNameLaptop(laptopAttributeDTO.getNameLaptop());
                   lapTopRep.save(laptop1);
               }
               Optional<Attribute>attribute = attributeRep.findById(laptopAttributeDTO.getIdLaptop());
               if (attribute.isPresent()){
                   Attribute attribute1 = attribute.get();
                   attribute1.setRam(laptopAttributeDTO.getRam());
                   attribute1.setVGA(laptopAttributeDTO.getVGA());
                   attribute1.setChip(laptopAttributeDTO.getChip());
                   attribute1.setSSD(laptopAttributeDTO.getSSD());
                   attributeRep.save(attribute1);
               }

               return laptopAttributeDTO;
           } catch (RuntimeException e) {
               throw  new notification("Bug data");
           }


    }

}
