/*globals define Date */
define('views/interaction', function(require) {
  var $ = require('jquery'), _ = require('underscore'), Backbone = require('backbone');

  return Backbone.View.extend({
    tagName : 'tr',

    render : function() {
      var date = new Date(parseInt(this.model.date, 10));
      $(this.el).html('<td>' + (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear() + ' ' + (date.getHours() < 13 ? date.getHours() : date.getHours() - 12) + ':' + date.getMinutes() + ' ' + (date.getHours() < 13 ? "AM" : "PM") + '</td>' + '<td>' + this.model.email + '</td>' + '<td>' + this.model.interactionType + '</td>');
      return this;
    }
  });
});
