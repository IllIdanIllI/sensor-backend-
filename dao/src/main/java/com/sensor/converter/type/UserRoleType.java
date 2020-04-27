package com.sensor.converter.type;

import com.sensor.model.type.UserRole;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class UserRoleType extends EnumType {

    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
                            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        preparedStatement.setObject(index, value != null ? ((UserRole) value).name() : null, Types.OTHER);
    }
}
