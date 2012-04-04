/*globals define */
define(['backbone', 'models/customers', 'views/customers', 'underscore', 'controllers/rest', 'models/customer'], function(Backbone, Customers, CustomersView, _, Rest, Customer) {
  return Backbone.Model.extend({

    didLoad : function( resource, response) {
      if (typeof window.app !== 'undefined' && window.app.undelegateEvents) {
        window.app.undelegateEvents();
      }
      var customerLinks = Rest.findLinks(resource, 'item');
      
      // Create view object to render.
      var view = new CustomersView({
        customersResource : resource,
        customerLinks : customerLinks,
        customers : []
      });
      
      // Lazily load data that we don't have yet.
      _.each(customerLinks, function(link) {
        var customer = new Customer;
        customer.fetch({
          url : link.href,
          success : _.bind(view.customerDidLoad, view)
        });
        return customer;
      });
      window.app = view;
    },

    load : function(url) {
      var resource = new Customers;
      resource.fetch({
        url : url,
        success : _.bind(this.didLoad, this)
      });
      return resource;
    }
  });
});
