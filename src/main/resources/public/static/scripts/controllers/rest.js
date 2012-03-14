/*globals define */
define('controllers/rest', function(require) {
  var Backbone = require('backbone');

  var Rest = Backbone.Model.extend({
    
    isResource: function(input) {
      return (input.get && typeof input.get('links') !== 'undefined');
    },

    findLinks : function(resource, rel) {
      return resource.get('links').filter(function(link) {
        return link.rel === rel;
      });
    },

    findLink : function(resource, rel) {
      return this.findLinks(resource, rel)[0];
    }
  });

  var obj = new Rest;
  return obj;
});
