package ru.moysklad.onlinestore.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.moysklad.onlinestore.dto.ProductSupplyDto;
import ru.moysklad.onlinestore.entity.ProductSupply;
import ru.moysklad.onlinestore.util.ProductMapperHelper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapperHelper.class})

public abstract class ProductSupplyMapper {
    @Mapping(target = "productId", source = "product.id")
    public abstract ProductSupplyDto map(ProductSupply productSupply);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "productId", qualifiedByName = "mapProductFromId")
    public abstract ProductSupply map(ProductSupplyDto productSupplyDto);

    @Mapping(target = "id", ignore = true)
    public abstract void updateProductSupplyFromDto(ProductSupplyDto productSupplyDto, @MappingTarget ProductSupply productSupply);
}
