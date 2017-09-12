angular.
	module('pokemonDetail').
	component('pokemonDetail', {
		templateUrl: 'app/pokemon-detail/pokemon-detail.template.html',
		controller: function PokemonDetailController($scope, $rootScope, $http, $translate, DTOptionsBuilder, DTColumnBuilder) {

			var self = this;
			
			self.dtInstance = {};

			self.dtOptions = $rootScope.newDTOptionsBuilder($scope, $rootScope.config.urlServices + 'services/pokemon/');
			
			self.dtColumns = [
			    DTColumnBuilder.newColumn('name', $translate('pokemon.name')).withClass('all'),
			    
			    DTColumnBuilder.newColumn(null, $translate('common.actions')).notSortable().renderWith(function(data, type, full, meta) {
			    	var ret = '<div class="text-nowrap">';
/*			    	ret += '<a href="javascript:;" ng-click="add(' + data.name + ');"><button class="btn btn-primary" type="button"><i class="fa fa-plus"></i></button></a>&nbsp;';
 */               	ret += '</div>';
 
                	return ret;
			    })           
            ];

			$scope.refresh = function() {
		        self.dtInstance.rerender();
			};
			
			$scope.add = function(data) {
				$scope.pokemon.name = data.name;
			};

			$scope.deletePokemon = function(id) {
				if(confirm($translate.instant('common.deleteConfirm'))) {
					$http.post('services/pokemon/delete/' + id).then(function(response) {
						$rootScope.successMessage();
						$scope.refresh();
					}, function(response) {
						if(response.data.exception.indexOf('DataIntegrityViolationException') >= 0) {
							$rootScope.warningMessage('common.deleteConstraint');
						} else {
							$rootScope.errorMessage(response);	
						}
					});
				}
			};
		}		  
	});
