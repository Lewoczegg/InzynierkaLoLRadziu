package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ContactInfoType implements UserType<ContactInfo> {

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<ContactInfo> returnedClass() {
        return ContactInfo.class;
    }

    @Override
    public boolean equals(ContactInfo x, ContactInfo y) {
        if (x == y) return true;
        if (x == null || y == null) return false;
        return x.getEmail().equals(y.getEmail()) && 
               x.getPhoneNumber().equals(y.getPhoneNumber());
    }

    @Override
    public int hashCode(ContactInfo x) {
        return x.hashCode();
    }

    @Override
    public ContactInfo nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) 
            throws SQLException {
        String value = rs.getString(position);
        if (value == null) return null;
        
        // Parse PostgreSQL composite type format: "(email,phoneNumber)"
        String content = value.substring(1, value.length() - 1); // Remove parentheses
        String[] parts = content.split(",", 2);
        
        return ContactInfo.builder()
                .email(parts[0])
                .phoneNumber(parts.length > 1 ? parts[1] : null)
                .build();
    }

    @Override
    public void nullSafeSet(PreparedStatement st, ContactInfo value, int index, SharedSessionContractImplementor session) 
            throws SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            PGobject pgObject = new PGobject();
            pgObject.setType("contact_info");
            pgObject.setValue("(" + value.getEmail() + "," + value.getPhoneNumber() + ")");
            st.setObject(index, pgObject);
        }
    }

    @Override
    public ContactInfo deepCopy(ContactInfo value) {
        if (value == null) return null;
        return ContactInfo.builder()
                .email(value.getEmail())
                .phoneNumber(value.getPhoneNumber())
                .build();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(ContactInfo value) {
        return deepCopy(value);
    }

    @Override
    public ContactInfo assemble(Serializable cached, Object owner) {
        return deepCopy((ContactInfo) cached);
    }

    @Override
    public ContactInfo replace(ContactInfo detached, ContactInfo managed, Object owner) {
        return deepCopy(detached);
    }
}
