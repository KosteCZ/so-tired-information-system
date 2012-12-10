package cz.muni.fi.pa165.stis.util;

import cz.muni.fi.pa165.stis.dto.TyrePosition;
import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import java.util.EnumMap;
import java.util.Map;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;

/**
 *
 * @author Dusan Svancara
 */
public class EnumMapConverter implements CustomConverter {

        @Override
        public Object convert(Object o, Object o1, Class<?> type, Class<?> type1) {
            if (o1 == null) {
                return null;
            }
            DozerBeanMapper m = new DozerBeanMapper();
            //
            Map<TyrePosition, Object> tyres = new EnumMap<>(TyrePosition.class);
            Map<TyrePosition, Object> old = (Map<TyrePosition, Object>) o1;
            for (Map.Entry<TyrePosition, Object> me : old.entrySet()) {
                if (me.getValue() instanceof TyreTO) {
                    tyres.put(me.getKey(), m.map(me.getValue(), Tyre.class));
                } else {
                    tyres.put(me.getKey(), m.map(me.getValue(), TyreTO.class));
                }
            }

            return tyres;
        }
    
    }
