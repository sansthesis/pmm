/*globals define window */
define(['jquery', 'underscore', 'backbone', 'views/customer', 'models/customer', 'controllers/rest'], function($, _, Backbone, CustomerView, Customer, Rest) {
//  var $ = require('jquery'), _ = require('underscore'), Backbone = require('backbone'), CustomerView = require('views/customer'), Customer = require('models/customer');

  return Backbone.View.extend({
    customersResource : {},
    customerViews : [],

    events : {
      'click .column' : 'columnClick'
    },

    el : $('body'),
    
    appendCustomer : function(root, ascending) {
      return (function(view) {
        if( ascending ) {
          root.append(view.render().el);
        } else {
          root.prepend(view.render().el);
        }
      });
    },
    
    createCustomerStub : function(customer) {
      var view = new CustomerView({link : customer});
      this.customerViews.push(view);
      $('tbody').append(view.render().el);
    },
    
    customerDidLoad: function(resource, response) {
      var uri = _.find(resource.get('links'), function(link) {
        return 'self' === link.rel;
      }).href;
      var view = _.find(this.customerViews, function(v) {
        return uri === v.options.link.href;
      });
      view.options.model = resource;
      view.render();
    },

    appendCustomers : function(self, customers, sortColumn, sortDirection) {
      customers = customers && customers.get ? customers : new Backbone.Model();
      var comparator = function(customer) {return customer.options.model ? customer.options.model.get(sortColumn) : '';};
      
      // Clean up DOM.
      $('tbody').find('tr').remove().end();
      
      // Render each view.
      _.each(
          _.sortBy(
              self.customerViews, comparator
              ), function(view) {
                if( sortDirection === 'asc' ) {
                  $('tbody').append(view.render().el);                  
                } else {
                  $('tbody').prepend(view.render().el);
                }
              }
          );
    },

    initialize : function() {
      var customersResource = this.customersResource = this.options.customersResource;
      var customerLinks = this.customerLinks = this.options.customerLinks;
      $('div#main').find('table').remove().end();
      $('div#main', self.el).append($('<table>').attr('id', 'customers'));
      $('table#customers', self.el).append($('<thead>').append($('<tr>')
          .append($('<th>').text('First Name').addClass('column firstname'))
          .append($('<th>').text('Last Name').addClass('column lastname'))
          .append($('<th>').text('Email').addClass('column email'))
          .append($('<th>').text('Zip Code').addClass('column zipcode'))));
      $('table#customers', self.el).append($('<tbody>')).end();
      
      // Find all customers to load.
      var filter = function(link) {return 'item' === link['rel'];};
      
      // Create stub for each customer.
      _.each(customerLinks, _.bind(this.createCustomerStub, this));

      this.appendCustomers(this, customersResource, 'email', 'asc');
    },

    columnClick : function(evt) {
      var classNames = $(evt.target).attr('class').split(' '), i, length = classNames.length, sortDirection = 'asc', sortColumn = '';

      for (i = 0; i < length; i++) {
        switch (classNames[i]) {
        case 'asc':
          sortDirection = 'desc';
          break;
        case 'desc':
          sortDirection = 'asc';
          break;
        case 'column':
          break;
        default:
          sortColumn = classNames[i];
          break;
        }
      }
      $('th', this.el).removeClass('asc');
      $('th', this.el).removeClass('desc');
      $(evt.target).addClass(sortDirection);
      this.appendCustomers(this, this.customersResource, sortColumn, sortDirection);
    }
  });
});
