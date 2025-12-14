package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FullNameType implements UserType<FullName> {

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<FullName> returnedClass() {
        return FullName.class;
    }

    @Override
    public boolean equals(FullName x, FullName y) {
        if (x == y) return true;
        if (x == null || y == null) return false;
        return x.getFirstName().equals(y.getFirstName()) && 
               x.getSurname().equals(y.getSurname());
    }

    @Override
    public int hashCode(FullName x) {
        return x.hashCode();
    }

    @Override
    public FullName nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) 
            throws SQLException {
        String value = rs.getString(position);
        if (value == null) return null;
        
        // Parse PostgreSQL composite type format: "(firstName,surname)"
        String content = value.substring(1, value.length() - 1); // Remove parentheses
        String[] parts = content.split(",", 2);
        
        return FullName.builder()
                .firstName(parts[0])
                .surname(parts.length > 1 ? parts[1] : null)
                .build();
    }

    @Override
    public void nullSafeSet(PreparedStatement st, FullName value, int index, SharedSessionContractImplementor session) 
            throws SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            PGobject pgObject = new PGobject();
            pgObject.setType("full_name");
            pgObject.setValue("(" + value.getFirstName() + "," + value.getSurname() + ")");
            st.setObject(index, pgObject);
        }
    }

    @Override
    public FullName deepCopy(FullName value) {
        if (value == null) return null;
        return FullName.builder()
                .firstName(value.getFirstName())
                .surname(value.getSurname())
                .build();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(FullName value) {
        return deepCopy(value);
    }

    @Override
    public FullName assemble(Serializable cached, Object owner) {
        return deepCopy((FullName) cached);
    }

    @Override
    public FullName replace(FullName detached, FullName managed, Object owner) {
        return deepCopy(detached);
    }
}
