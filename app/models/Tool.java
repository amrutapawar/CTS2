package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;



@Entity
@Table(name="tools")
public class Tool extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String toolname;

    @Constraints.Required
    public String description;


    @Constraints.Required
    public String owner;

    @Constraints.Required
    public String category;

    @OneToMany
    public List<Comments> commentbody;


    //public static Finder<Long, Tool> find = new Finder<Long,Tool>(Tool.class);
    public static Finder<Long, Tool> find = new Finder<Long,Tool>(Tool.class);


}