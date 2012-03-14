/*globals define window */
define('controllers/routes', function(require) {
  var Backbone = require('backbone');
  var Rest = require('controllers/rest');

  var Routes = Backbone.Model.extend({
    findLink: function(input, rel) {
      var link = null;
      if( Rest.isResource(input) && Rest.findLink(input, rel) ) {
        link = Rest.findLink(input, rel);
      } else if (Rest.isResource(input) ) {
        link = Rest.findLink(input, 'self');
      } else {
        link = input;
      }
      return link;
    },
    
    generateLinkToCustomers : function(input) {
      var link = this.findLink(input, 'customers');
      return '/index.html/#!/customers?uri=' + link.href;
    }

  });

  var routes = new Routes;
  return routes;
});
