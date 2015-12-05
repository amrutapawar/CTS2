package models;

/**
 * Created by Amruta Pawar on 11/11/2015.
 */
        import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tools")
public class Tool extends Model {

    @Id
    public Long id;

  //  @Constraints.Required
    public String name;

  //  @Constraints.Required
    public String description;

//    @Constraints.Required
    public String owner;

  //  @Constraints.Required
    public String category;

 //   @OneToMany
    public String comment;

    @OneToMany
    public String toollist;

    public static Finder<Long, Tool> find = new Finder<Long,Tool>(Tool.class);

   // @Blob
   // public blob image;

   /* public static Finder<Long, Tool> find = new Finder<Long,Tool>(Tool.class);

    public Tool(String title, File image)
    {
        this.title = title;
        this.data = new byte[(int)image.length()];
        InputStream instream = null;
        try {

            instream = new BufferedInputStream(new FileInputStream(image));
            instream.read(this.data); //file's data is taken in buffer and data variable reads it

        }
        catch(IOException e){
            e.printStackTrace();    }
        finally {
            if(instream != null){
                try {
                    instream.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }

            }
    }
      this.save();


       public String brand;

    public int modelno;

    public String conditionoftool;

    public int year;

    public int qty;

    }*/



}
