package controllers;

import models.*;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;


public class UserAuth extends Security.Authenticator {

    // When return is null, Authentication failed
    @Override
    public String getUsername(final Http.Context ctx) {
        String userIdStr = ctx.session().get("id");
        if(userIdStr == null) return null;

        User user = User.find.byId(Long.parseLong(userIdStr));
        return (user != null ? user.getId() : null);

    }

    @Override
    public Result onUnauthorized(final Http.Context ctx) {
        ctx.flash().put("error",
                "Nice try, but you need to log in first!");
        return redirect(routes.Users.index());
    }
}