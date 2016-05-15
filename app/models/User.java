package models;


import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Table(name="users")
@Entity
public class User extends Model {

    @Id
    public Long id;

    @Constraints.Required
    @Column(unique=true)
    public String username;

    //@Constraints.Required
    public String password_hash;

    @Constraints.Required
    public String firstname;

    @Constraints.Required
    public String lastname;

   // @Constraints.Required;
    public String address;

    @Constraints.Required
    public String email;

    public String phone;

    @OneToMany
    public List<Comments> commentbody;

        // Finder object for easier querying
    public static Finder<Long, User> find = new Finder<Long,User>(User.class);

    // NOT FOR PRODUCTION - must ensure this is a valid user first. I have not done that.

    public boolean authenticate(User user, String password)
    {
        if(user!=null){
            return BCrypt.checkpw(password, user.password_hash);
        }
        else {
            return false;
        }
    }

    public String getId(){
        return this.id.toString();

    }

    public String getName(){
       // System.out.println(this.username.toString());
        return this.username.toString();
    }


    public static User createUser(String username, String password,String firstname, String lastname, String email,String phone, String address){


        // requirements for username and password
        if((password==null || username==null) && password.length()<8)
        {
           // flash("error","fields cannot be empty");
            return null;
        }


        // create a password hash
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        // create a new user instance in the database
        // assign the username and passwordHash to the newly created user
        User user = new User();

        user.username = username;
        user.password_hash = passwordHash;
        user.firstname = firstname;
        user.lastname = lastname;
        user.email = email;
        user.phone = phone;
        user.address = address;

        return user;
    }
}