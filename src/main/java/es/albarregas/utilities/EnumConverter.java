/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.utilities;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author Sergio
 */
public class EnumConverter implements Converter {

    @Override
    public Object convert(Class type, Object o) {
        String enumValName = (String) o;
        Enum[] enumConstants = (Enum[]) type.getEnumConstants();

        for (Enum enumConstant : enumConstants) {
            if (enumConstant.name().equals(enumValName)) {
                return (Object) enumConstant;
            }
        }

        throw new ConversionException(String.format("Failed to convert %s value to %s class", enumValName, type.toString()));
    
        }
}
    

