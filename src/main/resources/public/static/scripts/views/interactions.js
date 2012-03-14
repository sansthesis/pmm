/*globals define window */
define('views/interactions', function(require) {
  var $ = require('jquery'), _ = require('underscore'), Backbone = require('backbone'), Campaigns = require('models/campaigns'), Routes = require('controllers/routes'), Rest = require('controllers/rest'), Campaign = require('models/campaign'), InteractionView = require('views/interaction');

  return Backbone.View.extend({
    controller : {},
    campaign : {},

    events : {
      'click .column' : 'columnClick',
      'change select#campaigns' : 'campaignSelectionDidChange'
    },

    el : $('body'),

    appendInteractions : function(self, interactions, sortColumn, sortDirection) {
      interactions = interactions && interactions.get ? interactions : new Backbone.Model();
      $('tbody').find('tr').remove().end();
      _.each(_.sortBy(interactions.get('interactions'), function(interaction) {
        return interaction[sortColumn];
      }), function(interaction) {
        var view = new InteractionView({
          model : interaction
        });
        if( sortDirection === 'desc' ) {
          $("tbody", self.el).prepend(view.render().el);          
        } else if( sortDirection === 'asc' ) {
          $("tbody", self.el).append(view.render().el);
        }
      });
    },

    campaignDidLoad : function(self) {
      return (function(resource, response) {
        self.campaign = resource;
      });
    },

    campaignsDidLoad : function(self, campaigns) {
      $('select#campaigns').find('option').remove().end();
      return (function(resource, response) {
        self.campaigns = (typeof campaigns === 'undefined') ? resource : campaigns;
        $('select#campaigns').append('<option value="' + Routes.generateLinkToCampaigns(self.campaigns) + '">--Select a campaign--</option>');
        _.each(_.sortBy(_.filter(self.campaigns.get('links'), function(link) {
          return link.rel === 'campaign';
        }), function(link) {
          return link.title.toLowerCase();
        }), function(link) {
          $('select#campaigns').append('<option value="' + Routes.generateLinkToCampaign(link) + '"' + (window.location.href.indexOf(link.href) > -1 ? 'selected="selected"' : '') + '>' + link.title + '</option>');
        });
      });
    },

    campaignDidLoad : function(self, campaign) {
      return (function(resource, response) {
        self.campaign = (typeof campaign === 'undefined') ? resource : campaign;
        if (typeof self.campaign !== 'undefined' && typeof self.campaigns === 'undefined') {
          var campaignsLink = Rest.findLink(self.campaign, 'campaigns');
          self.campaigns = new Campaigns;
          self.campaigns.fetch({
            url : campaignsLink.href,
            success : self.campaignsDidLoad(self)
          });
        } else if (typeof self.campaigns !== 'undefined') {
          this.campaignsDidLoad(self, self.campaigns)();
        }
      });
    },

    campaignSelectionDidChange : function(evt) {
      var selectedOption = _.filter(evt.currentTarget.childNodes, (function(option) {
        return option.selected;
      }))[0];
      var targetUri = selectedOption.value;
      window.location.href = targetUri;
    },

    initialize : function() {
      var controller = this.controller = this.options.controller;
      var campaign = this.campaign = controller.get('campaign');
      var campaigns = this.campaigns = controller.get('campaigns');
      var interactions = this.interactions = controller.get('interactions');
      
      $('tbody').find('tr').remove().end();

      if (typeof interactions !== 'undefined' && typeof campaign === 'undefined') {
        var campaignLink = Rest.findLink(interactions, 'campaign');
        campaign = new Campaign;
        campaign.fetch({
          url : campaignLink.href,
          success : this.campaignDidLoad(this)
        });
        this.appendInteractions(this, interactions, 'date', 'desc');
      } else if (typeof campaign !== 'undefined') {
        this.campaignDidLoad(this, campaign)();
      } else if (typeof campaigns !== 'undefined') {
        this.campaignsDidLoad(this, campaigns)();
      }
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
      this.appendInteractions(this, this.interactions, sortColumn, sortDirection);
    }
  });
});
