package controllers;

import models.Tool;
import models.User;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.data.Form.form;

public class Users extends Controller {

    public Result welcome() {

        return ok(views.html.cts.welcome.render());
    }

    public Result index() {
        List<Tool> tools = Tool.find.all();
        return ok(views.html.cts.index.render(tools));
    }



    //post
    public Result register() {

        //Tool tool = Form.form(Tool.class).bindFromRequest().get();
      /*  User user1 = Form.form(User.class).bindFromRequest().get();
        String username = user1.username;
        String password = user1.password_hash;
        String firstname = user1.firstname;
        String lastname  = user1.lastname;
        String email = user1.email;
        String phone = user1.phone;
        String address = user1.address;
        System.out.println(username);*/

        DynamicForm userForm = form().bindFromRequest();
        String username = userForm.data().get("username");
        String password = userForm.data().get("password");
        String firstname = userForm.data().get("firstname");
        String lastname = userForm.data().get("lastname");
        String email = userForm.data().get("email");
        String phone = userForm.data().get("phone");
        String address = userForm.data().get("address");

        if(username.trim().length()==0 || password.trim().length() == 0 || firstname.trim().length() == 0 || lastname.trim().length() == 0 || email.trim().length()== 0)
        {
            flash("error", "Required fields cannot be empty");
            return redirect(routes.Users.registerUI());


        }


        User user = User.createUser(username, password, firstname, lastname, email, phone, address);

        if (user == null) {
            flash("error", "Invalid user");
            return redirect(routes.Users.registerUI());
        }
            /*user.username = username;
            user.password_hash = password;
        user.firstname = firstname;
        user.lastname = lastname;
        user.email = email;
        user.phone = phone;
        user.address = address;*/
        else{
            user.save();
            flash("success", "Welcome new user " + user.username);
            session("id", user.getId());
            session(user.getId(),user.getName());

            return redirect(routes.Tools.tools());}
       // return redirect(routes.Users.index());
    }


    //get
    public Result registerUI()  {

        return ok(views.html.cts.register.render());
    }

    //get
    public Result loginUI() {

        return ok(views.html.cts.login.render());
    }


    //post
    public Result login(){


        DynamicForm userForm = form().bindFromRequest();
        String username = userForm.data().get("username");
        String password = userForm.data().get("password");

        User user = User.find.where().eq("username", username).findUnique();

        if (user != null ) {
            if(user.authenticate(user,password)) {
                session("id", user.getId());
                session(user.getId(), user.getName());
                flash("success", "Welcome " + user.username);
            }
            else
            {
                flash("error", "Invalid login. Please try with correct username/password");
                return redirect(routes.Users.loginUI());
            }
        } else {
            flash("error", "Invalid login. Please try with correct username/password");
            return redirect(routes.Users.loginUI());
        }



       // return redirect(routes.Users.index());
        return redirect(routes.Tools.tools());
    }


    public Result logout() {
        session().remove("id");
        return redirect(routes.Users.welcome());
    }




}