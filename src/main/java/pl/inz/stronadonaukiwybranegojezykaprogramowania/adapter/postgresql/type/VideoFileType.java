package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.postgresql.type;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class VideoFileType implements UserType<VideoFile> {

    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<VideoFile> returnedClass() {
        return VideoFile.class;
    }

    @Override
    public boolean equals(VideoFile x, VideoFile y) {
        if (x == y) return true;
        if (x == null || y == null) return false;
        return x.getFileName().equals(y.getFileName()) && 
               x.getFilePath().equals(y.getFilePath()) &&
               x.getFileSize().equals(y.getFileSize());
    }

    @Override
    public int hashCode(VideoFile x) {
        return x.hashCode();
    }

    @Override
    public VideoFile nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) 
            throws SQLException {
        String value = rs.getString(position);
        if (value == null) return null;
        
        // Parse PostgreSQL composite type format: "(fileName,filePath,fileSize)"
        String content = value.substring(1, value.length() - 1); // Remove parentheses
        String[] parts = content.split(",", 3);
        
        return VideoFile.builder()
                .fileName(parts[0])
                .filePath(parts.length > 1 ? parts[1] : null)
                .fileSize(parts.length > 2 && parts[2] != null ? Long.parseLong(parts[2]) : null)
                .build();
    }

    @Override
    public void nullSafeSet(PreparedStatement st, VideoFile value, int index, SharedSessionContractImplementor session) 
            throws SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
            PGobject pgObject = new PGobject();
            pgObject.setType("video_file");
            pgObject.setValue("(" + value.getFileName() + "," + value.getFilePath() + "," + value.getFileSize() + ")");
            st.setObject(index, pgObject);
        }
    }

    @Override
    public VideoFile deepCopy(VideoFile value) {
        if (value == null) return null;
        return VideoFile.builder()
                .fileName(value.getFileName())
                .filePath(value.getFilePath())
                .fileSize(value.getFileSize())
                .build();
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(VideoFile value) {
        return deepCopy(value);
    }

    @Override
    public VideoFile assemble(Serializable cached, Object owner) {
        return deepCopy((VideoFile) cached);
    }

    @Override
    public VideoFile replace(VideoFile detached, VideoFile managed, Object owner) {
        return deepCopy(detached);
    }
}
