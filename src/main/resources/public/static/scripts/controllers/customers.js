/*globals define */
define('controllers/customers', function(require) {
  var Backbone = require('backbone');
  var Customers = require('models/customers');
  return Backbone.Model.extend({

    didLoad : function(controller) {
      return (function(resource, response) {
        return resource;
      });
    },

    load : function(url) {
      var resource = new Customers;
      resource.fetch({
        url : url,
        success : this.didLoad(this)
      });
      return resource;
    }
  });
});
