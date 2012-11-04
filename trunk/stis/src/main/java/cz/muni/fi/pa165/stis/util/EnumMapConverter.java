/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.stis.util;

import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.entity.TyrePosition;
import java.util.EnumMap;
import java.util.Map;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;

/**
 *
 * @author Dusan
 */
public class EnumMapConverter implements CustomConverter {

        @Override
        public Object convert(Object o, Object o1, Class<?> type, Class<?> type1) {
            DozerBeanMapper m = new DozerBeanMapper();
            //
            if (o == null) {
                Map<TyrePosition, TyreTO> tyres = new EnumMap<>(TyrePosition.class);
                Map<TyrePosition, Tyre> old = (Map<TyrePosition, Tyre>) o1;
                for (Map.Entry<TyrePosition, Tyre> me : old.entrySet()) {
                    tyres.put(me.getKey(), m.map(me.getValue(), TyreTO.class));
                }

                return tyres;
            } else if (o1 == null) {
                Map<TyrePosition, Tyre> tyres = new EnumMap<>(TyrePosition.class);
                Map<TyrePosition, TyreTO> old = (Map<TyrePosition, TyreTO>) o;
                for (Map.Entry<TyrePosition, TyreTO> me : old.entrySet()) {
                    tyres.put(me.getKey(), m.map(me.getValue(), Tyre.class));
                }

                return tyres;
            }
            return null;
        }
    
    }
