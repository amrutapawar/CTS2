package models;
import com.avaje.ebean.Model;
import org.joda.time.DateTime;
import play.data.validation.Constraints;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Amruta Pawar on 3/13/2016.
 */

@Table(name="comments")
@Entity
public class Comments extends Model{

    @Id
    public Long cid;

    @Constraints.Required
    @ManyToOne
    public User user;

    @Constraints.Required
    @ManyToOne
    public Tool tool;

    @Constraints.Required

    public String commentbody;

    @Constraints.Required
    public DateTime dt;



   // public static Finder<Long, Comments> findcomment = new Finder<Long,Comments>(Comments.class);
   public static Finder<Long, Comments> find = new Finder<Long,Comments>(Comments.class);


}
