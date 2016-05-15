package controllers;

import models.Comments;
import models.Tool;
import models.User;
import org.joda.time.DateTime;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

import static play.data.Form.form;

public class Search extends Controller{

    public Result search(){

        DynamicForm form = form().bindFromRequest();
        String search = form.data().get("search");
        String cat = form.data().get("categories");

      //  if(search.trim().length() == 0 && cat.equals("")){
          //  List<ToolCategory> toolcat = ToolCategory.find.all();
            List<Tool> tool = Tool.find.where().eq("category",cat).findList();
         //   return redirect(routes.Website.tools());
            return ok(views.html.cts.tools.render(tool));


    //    }
     //   return ok();
    }

    public Result addcomment(Long id){
        /*Tool tool = Tool.find.where().idEq(id).findUnique();
        User user = User.find.where().eq("user_id",Long.parseLong(session("user_id"))).findUnique();
        DynamicForm form = form().bindFromRequest();
        String com = */



        DynamicForm userForm = form().bindFromRequest();

        Tool gettoolname = Tool.find.where().eq("id",id).findUnique();
        User getuname = User.find.where().eq("id", Long.parseLong(session("id"))).findUnique();
        //System.out.println(session("id"));
        String combody = userForm.data().get("commentbody");
        Comments com = new Comments();
        com.tool = gettoolname;
        com.user = getuname;
        com.commentbody =  combody;
        com.dt = new DateTime();
        com.save();

        return redirect(routes.Tools.buy(id));

    }


    public Result showcomment(Long id){
        Tool tool = Tool.find.where().eq("tool_id",id).findUnique();
        List<Comments> comments = Comments.find.where().eq("tool",tool).findList();
        System.out.println("comments is " + comments.toString());
        return ok(views.html.cts.buy.render(tool,comments));


    }
}
