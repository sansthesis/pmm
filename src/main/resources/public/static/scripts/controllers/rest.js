/*globals define */
define(['backbone', 'underscore'], function(Backbone, _) {
  var Rest = Backbone.Model.extend({
    
    isResource: function(input) {
      return (input.get && typeof input.get('links') !== 'undefined');
    },

    findLinks : function(resource, rel) {
      var desiredRels = _.isArray(rel) ? rel : [rel];
      var rels = _.filter(resource.get('links'), function(link) { return (_.isEmpty(_.difference(desiredRels, link.rel.split(' ')))); });
      return rels;
    },

    findLink : function(resource, rel) {
      return _.first(this.findLinks(resource, rel));
    }
  });

  return new Rest;
});
