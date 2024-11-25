package ru.moysklad.onlinestore.mapper;

import org.mapstruct.*;
import ru.moysklad.onlinestore.dto.ProductDto;
import ru.moysklad.onlinestore.entity.Product;
import ru.moysklad.onlinestore.util.ProductMapperHelper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapperHelper.class})
public abstract class ProductMapper {
    public abstract ProductDto map(Product product);

    @Mapping(target = "id", ignore = true)
    public abstract Product map(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateProductFromDto(ProductDto productDto, @MappingTarget Product product);

    @IterableMapping(elementTargetType = ProductDto.class)
    public abstract List<ProductDto> map(List<Product> products);
}
