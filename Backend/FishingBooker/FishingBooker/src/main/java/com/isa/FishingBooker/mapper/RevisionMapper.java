package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.RevisionDTO;
import com.isa.FishingBooker.model.revision.Revision;

@Component
public class RevisionMapper extends CustomModelMapperAbstract<Revision,RevisionDTO> {

    @Override
    public RevisionDTO convertToDto(Revision entity) {
        RevisionDTO dto = modelMapper.map(entity, RevisionDTO.class);
        dto.setRelatedId(entity.getRelatedId());
        dto.setClassName(entity.getClass().toString());
        try{
            dto.setCreatorInfo(entity.getCreator().getName()+" " + entity.getCreator().getSurname());
            dto.setOwnerEmail(entity.getOwnerEmail());
            return dto;
        }catch (Exception ex){
            return dto;
        }
    }
}
