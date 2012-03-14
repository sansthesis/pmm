/*globals define */
define('controllers/root', function(require) {
  var Backbone = require('backbone');
  var Root = require('models/root');
  var Routes = require('controllers/routes');
  return Backbone.Model.extend({
    rootDidLoad : function(root, response) {
      window.location.href = Routes.generateLinkToCustomers(root);
    },

    load : function(url) {
      var root = new Root;
      root.fetch({
        url : url,
        success : this.rootDidLoad
      });
      return root;
    }
  });
});
