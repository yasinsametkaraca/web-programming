package org.webp.project.entities.concretes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="topics")
@Entity
public class Topic {

    @Id
    @Column(name="topic_id")
    private int topicId;

    @Column(name="topic_name")
    private String topicName;

    @OneToMany(mappedBy="topic")
    private List<Comment> comments;
}


