/*globals define window */
define('controllers/router', function(require) {
  var Backbone = require('backbone'), RootController = require('controllers/root'), CustomersController = require('controllers/customers');
  var AppRouter = Backbone.Router.extend({
    routes : {
      '!/root?uri=*uri' : 'index',
      '!/customers?uri=*uri' : 'customers',
      '*actions' : 'defaultRoute'
    },

    index : function(uri) {
      var root = new RootController;
      root.load(uri);
    },

    customers : function(uri) {
      var customers = new CustomersController;
      customers.load(uri);
    },

    defaultRoute : function(actions) {
      console.error("Unknown url fragment: ", actions);
      window.location.href = '/index.html';
    },
  });
  // Initiate the router
  var app_router = new AppRouter;
  Backbone.history.start();
  return app_router;
});
