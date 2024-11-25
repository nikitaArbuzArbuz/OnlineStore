package ru.moysklad.onlinestore.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.moysklad.onlinestore.dto.ProductSaleDto;
import ru.moysklad.onlinestore.entity.ProductSale;
import ru.moysklad.onlinestore.util.ProductMapperHelper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapperHelper.class})

public abstract class ProductSaleMapper {
    @Mapping(target = "productId", source = "product.id")
    public abstract ProductSaleDto map(ProductSale productSupply);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "productId", qualifiedByName = "mapProductFromId")
    public abstract ProductSale map(ProductSaleDto productSaleDto);

    @IterableMapping(elementTargetType = ProductSaleDto.class)
    public abstract List<ProductSaleDto> mapSales(List<ProductSale> productSales);

    @Mapping(target = "id", ignore = true)
    public abstract void updateProductSaleFromDto(ProductSaleDto productSaleDto, @MappingTarget ProductSale productSale);
}
