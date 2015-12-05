package controllers;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import models.Tool;
import java.util.List;

public class Website extends Controller {

    public Result index() {
        List<Tool> tools = Tool.find.all();
        return ok(views.html.cts.index.render(tools));
    }



    public Result create(){
    Tool tool = Form.form(Tool.class).bindFromRequest().get();
    tool.save();
    flash ("success", "Saved new tool" + tool.name);
    return redirect(routes.Website.tools());
    }

    public Result register() {

        User user = Form.form(User.class).bindFromRequest().get();

        user.save();
        flash("success", "Your account has been created successfully" + user.name);

        return ok(views.html.cts.register.render());
    }

    public Result gtools(){

        List<Tool> tool = Tool.find.all();
    //    Tool tool1 = Tool.find.byId(id);
        flash("hello");
       return ok(views.html.cts.tools.render(tool));
      //  return ok(views.html.cts.buy.render(tool));
        //   return ok("buy page");
    }

    public Result tools(){

      //  List<Tool> tool = Tool.find.all();
        return redirect(routes.Website.tools());
      //  return ok(views.html.cts.tools.render(tool));
        //   return ok("buy page");
    }


    public Result buy(Long id){

        Tool tool = Tool.find.byId(id);
            return ok(views.html.cts.buy.render(tool));
     //   return ok("buy page");
    }

    public Result payeeinfo(){

        return ok(views.html.cts.payee_info.render());
    }

    public Result confirmation() {
        return ok(views.html.cts.confirmation.render());
    }


    public Result show(Long id) {
        //Query the database for a Tool with this id
        Tool tool = Tool.find.byId(id);

        //If the tool doesn't exist, then respond with a 404.
        if (tool == null)
            return notFound("Not Found\n");
        else
            return ok(views.html.cts.buy.render(tool));
        //return ok("show page");
    }


  /* public Result signup()  {

        User user = Form.form(User.class).bindFromRequest().get();

        user.save();
        flash ("success", "Your account has been created successfully" + user.name);

        return ok(views.html.cts.signup());
    }


    //public Result login(Long id) {
    //  User user = User.find.byId(id);

    //if (user == null)
    //  return notFound("Not Found\n");
    // else
    //return ok(views.html.cts.index());
    //}*/



  /*  #GET     /users                      controllers.Website.register()

            #POST     /create                     controllers.Website.create()
            #GET    /tools1/:id                   controllers.Website.tools(id:Long)
            #GET    /buy/:id                        controllers.Website.buy(id:Long)

            #POST    /tools                      controllers.Website.payeeinfo()
            #POST    /tools/confirmation         controllers.Website.confirmation()
            # POST     /tools/:id                  controllers.Website.show(id:Long)*/

}

/*public class UploadImageForm{
        public FilePart image;

        public String validate(){
            MultipartFormData data = request().body().asMultipartFormData();
            image= data.getFile("image");

            if(image==null){

                return "File is missing";


            }
            return null;
        }

    }

    public Result uploadImage(){
        Form<UploadImageForm> form = form(UploadImageForm.class).bindFromRequest();

        if(form.hasErrors()) {
            return badRequest(index.render(form, Tool.find.all()));//"bad request"));
        }
        else {
            new Tool(
                    form.get().image.getFilename(),
                    form.get().image.getFile());

            flash("success","file uploaded");
            return redirect(routes.Website.index());


        }


    public Result getImage(Long id){
        Tool image = Tool.find.byId(id);
        if(image!=null){
            //return ok(views.html.cts.tools.render(tool));
            return ok(image.data).as("image");
        }
        else{
           flash("error","Picture not available");
        //   return redirect(routes.Website.tools(image));
            return ok("tools page error");
        }

    }

    }*/
