package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserLevelType implements UserType<UserLevel> {

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<UserLevel> returnedClass() {
        return UserLevel.class;
    }

    @Override
    public boolean equals(UserLevel x, UserLevel y) {
        if (x == y) return true;
        if (x == null || y == null) return false;
        return x.getLevel().equals(y.getLevel()) && 
               x.getTitle().equals(y.getTitle());
    }

    @Override
    public int hashCode(UserLevel x) {
        return x.hashCode();
    }

    @Override
    public UserLevel nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) 
            throws SQLException {
        String value = rs.getString(position);
        if (value == null) return null;
        
        // Parse PostgreSQL composite type format: "(level,title)"
        String content = value.substring(1, value.length() - 1); // Remove parentheses
        String[] parts = content.split(",", 2);
        
        return UserLevel.builder()
                .level(parts[0] != null ? Integer.parseInt(parts[0]) : null)
                .title(parts.length > 1 && parts[1] != null ? Title.valueOf(parts[1]) : null)
                .build();
    }

    @Override
    public void nullSafeSet(PreparedStatement st, UserLevel value, int index, SharedSessionContractImplementor session) 
            throws SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            PGobject pgObject = new PGobject();
            pgObject.setType("user_level");
            pgObject.setValue("(" + value.getLevel() + "," + value.getTitle().name() + ")");
            st.setObject(index, pgObject);
        }
    }

    @Override
    public UserLevel deepCopy(UserLevel value) {
        if (value == null) return null;
        return UserLevel.builder()
                .level(value.getLevel())
                .title(value.getTitle())
                .build();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(UserLevel value) {
        return deepCopy(value);
    }

    @Override
    public UserLevel assemble(Serializable cached, Object owner) {
        return deepCopy((UserLevel) cached);
    }

    @Override
    public UserLevel replace(UserLevel detached, UserLevel managed, Object owner) {
        return deepCopy(detached);
    }
}
