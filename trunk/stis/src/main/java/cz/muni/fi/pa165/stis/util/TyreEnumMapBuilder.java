package cz.muni.fi.pa165.stis.util;

import cz.muni.fi.pa165.stis.dto.OrderTO;
import cz.muni.fi.pa165.stis.entity.Order;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;

/**
 *
 * @author Dusan Svancara
 */
public class TyreEnumMapBuilder extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(Order.class, OrderTO.class).fields("tyres", "tyres", FieldsMappingOptions.customConverter(EnumMapConverter.class));
    }
}
