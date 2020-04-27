package com.sensor.converter.type;

import com.sensor.model.type.UnitType;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UnitEnumType extends EnumType {

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
                            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        preparedStatement.setObject(index, value != null ? ((UnitType) value).getType() : null, Types.OTHER);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        String value = rs.getString(names[0]);
        return UnitType.typeValueOf(value);
    }
}
