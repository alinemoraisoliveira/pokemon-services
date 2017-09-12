angular.
  module('pokemonApp').
  config(['$locationProvider', '$routeProvider', '$translateProvider',
    function config($locationProvider, $routeProvider, $translateProvider) {
	  
      $locationProvider.hashPrefix('!');

      $routeProvider.
      	when('/', {
            template: '<home></home>'
        }).
        when('/teams', {
            template: '<team-list></team-list>'
        }).
        when('/teams/:teamId', {
          template: '<team-detail></team-detail>'
        }).
        when('/insertteams', {
            template: '<team-detail></team-detail>'
        }).
        when('/pokemons/:pokemonId', {
          template: '<pokemon-detail></pokemon-detail>'
        }).
        
        otherwise({redirectTo:'/'});
      
      $translateProvider.useSanitizeValueStrategy('escapeParameters');
      $translateProvider.translations('pt-br', translations_pt_br);
      $translateProvider.preferredLanguage('pt-br');
      
      PNotify.prototype.options.delay = 1500;
    }
  ]);