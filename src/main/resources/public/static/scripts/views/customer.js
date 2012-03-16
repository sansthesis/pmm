/*globals define */
define('views/customer', function(require) {
  var $ = require('jquery'), _ = require('underscore'), Backbone = require('backbone');

  return Backbone.View.extend({
    tagName : 'tr',
    link : '',
    
    format : function(text) {
      return (typeof text === 'undefined') ? '' : text;
    },

    render : function() {
      var model = this.model = this.options.model;
      var link = this.link = this.options.link;
      if( typeof(model) !== 'undefined' ) {
        return this.renderModel(model);
      } else if( typeof(link) !== 'undefined' ){
        return this.renderLink(link);
      }
    },
    
    renderLink : function(link) {
      $(this.el).html(
          '<td>' + '' + '</td>' +
          '<td>' + '' + '</td>' +
          '<td>' + link.title + '</td>' +
          '<td>' + '' + '</td>'
          );
      return this;
    },
    
    renderModel : function(model) {
      $(this.el).html(
          '<td>' + this.format(model.get('firstname')) + '</td>' +
          '<td>' + this.format(model.get('lastname')) + '</td>' +
          '<td>' + this.format(model.get('email')) + '</td>' +
          '<td>' + this.format(model.get('zipcode')) + '</td>'
          );
      return this;
    }
  });
});
