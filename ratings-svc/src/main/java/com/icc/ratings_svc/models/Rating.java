package src.main.java.com.icc.ratings_svc.models;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private short score;

    @Column(nullable = false)
    private String comment;

    /* @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store; no se que tiene que ir aca  */

    public Rating(short score, String comment) {
        this.score = score;
        this.comment = comment;
    }

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
