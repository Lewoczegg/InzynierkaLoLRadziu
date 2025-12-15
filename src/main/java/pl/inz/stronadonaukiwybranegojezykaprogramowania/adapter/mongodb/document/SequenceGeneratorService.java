package pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.mongodb.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Service
public class SequenceGeneratorService {
    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
            Query.query(Criteria.where("_id").is(seqName)),
            new Update().inc("seq",1),
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            DatabaseSequence.class
        );
        return counter != null ? counter.getSeq() : 1;
    }

    @Document(collection = "counters")
    public static class DatabaseSequence {
        @Id
        private String id;
        private long seq;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public long getSeq() { return seq; }
        public void setSeq(long seq) { this.seq = seq; }
    }
}
