package ru.moysklad.onlinestore.mapper;

import org.mapstruct.*;
import ru.moysklad.onlinestore.dto.ProductDto;
import ru.moysklad.onlinestore.entity.Product;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public abstract ProductDto map(Product product);
    public abstract Product map(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}
