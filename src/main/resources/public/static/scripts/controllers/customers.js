/*globals define */
define('controllers/customers', function(require) {
  var Backbone = require('backbone');
  var Customers = require('models/customers');
  var CustomersView = require('views/customers');
  return Backbone.Model.extend({

    didLoad : function(controller) {
      return (function(resource, response) {
        if( typeof window.app !== 'undefined' && window.app.undelegateEvents ) {
          window.app.undelegateEvents();
        }
        var view = new CustomersView({
          controller : controller,
          customers : resource
        });
        window.app = view;
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
