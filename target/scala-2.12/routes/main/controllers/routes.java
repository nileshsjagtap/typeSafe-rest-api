
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/niles/IdeaProjects/HeadyTypesafeProject/conf/routes
// @DATE:Tue Oct 13 16:52:14 IST 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseEntityController EntityController = new controllers.ReverseEntityController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseEntityController EntityController = new controllers.javascript.ReverseEntityController(RoutesPrefix.byNamePrefix());
  }

}
