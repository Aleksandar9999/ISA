package com.isa.FishingBooker.mapper;

import org.springframework.stereotype.Component;

import com.isa.FishingBooker.dto.RevisionDTO;
import com.isa.FishingBooker.model.Revision;
@Component
public class RevisionMapper<E extends Revision> extends CustomModelMapperAbstract<E, RevisionDTO> {

	@Override
	public E convertToEntity(RevisionDTO dto, Class<? extends E> retClass) {
		E entity = modelMapper.map(dto, retClass);
		entity.setRelatedId(dto.getRelatedId());
		return entity;
	}

	@Override
	public RevisionDTO convertToDto(E entity) {
		RevisionDTO dto = modelMapper.map(entity, RevisionDTO.class);
		dto.setRelatedId(entity.getRelatedId());
		dto.setClassName(entity.getClass().toString());
		return dto;
	}

	@Override
	public E convertToEntity(RevisionDTO dto) {
		return this.convertToEntity(dto, (Class<? extends E>) Revision.class);
	}
}
