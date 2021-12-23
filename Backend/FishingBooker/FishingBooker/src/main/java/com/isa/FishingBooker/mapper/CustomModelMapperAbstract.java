package com.isa.FishingBooker.mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.isa.FishingBooker.exceptions.UndefinedEntityClassException;

public abstract class CustomModelMapperAbstract<E, T> implements CustomModelMapper<E, T> {

	@Autowired
	protected ModelMapper modelMapper;

	private Class<E> entityClass;
	private Class<T> dtoClass;

	public CustomModelMapperAbstract() {
		this.resolveTypeClasses();
	}

	@Override
	public T convertToDto(E entity) {
		return this.modelMapper.map(entity,dtoClass);
	}

	@Override
	public E convertToEntity(T dto) {
		if(entityClass==null) throw new UndefinedEntityClassException();
		return this.modelMapper.map(dto, entityClass);
	}

	@Override
	public E convertToEntity(T dto, Class<? extends E> retClass) {
		if(retClass==null) throw new UndefinedEntityClassException();
		return this.modelMapper.map(dto, retClass);
	}

	@Override
	public List<T> convertToDtos(List<E> entities) {
		return entities.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<E> convertToEntities(List<T> dtos) {
		return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

	private void resolveTypeClasses() {
		this.entityClass=(Class<E>) this.resolveTypeClass(0);
		this.dtoClass=(Class<T>) this.resolveTypeClass(1);
	}
	
	private Class<?> resolveTypeClass(int index) {
		Type superClass = getClass().getGenericSuperclass();
		Type classType = ((ParameterizedType) superClass).getActualTypeArguments()[index];
		try {
			return Class.forName(classType.toString().split(" ")[1]);
		}catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
