angular.
	module('teamDetail').
	component('teamDetail', {
		templateUrl: 'app/team-detail/team-detail.template.html',
		controller: function TeamDetailController($scope, $rootScope, $routeParams, $http, $translate, DTOptionsBuilder, DTColumnBuilder, $window) {

			var self = this;
			
			self.dtInstance = {};

			if($routeParams.teamId != null){

				self.dtOptions = $rootScope.newDTOptionsBuilder($scope, $rootScope.config.urlServices + 'services/team/' + $routeParams.teamId + '/pokemons');
				
				self.dtColumns = [
				    DTColumnBuilder.newColumn('name', $translate('pokemon.name')).withClass('all'),
				    
				    DTColumnBuilder.newColumn(null, $translate('common.actions')).renderWith(function(data, type, full, meta) {
				    	var ret = '<div class="text-nowrap">';
				    	ret += '<a ng-href="#!/pokemons/' + data.pkdx_id + '"><button class="btn btn-primary" type="button"><i class="fa fa-pencil"></i></button></a>&nbsp;';
	                	ret += '<a href="javascript:;" ng-click="deleteTeam(' + data.pkdx_id + ');"><button class="btn btn-danger" type="button"><i class="fa fa-times"></i></button></a>&nbsp;';
	                	ret += '</div>';
	
	                	return ret;
	                })           
	            ];
				
				document.getElementById('div_pokemon').hidden = false;
			};

			$scope.refresh = function() {
		        self.dtInstance.rerender();
			};

			$scope.save = function() {
				$http.post('services/team/save', $scope.team).then(function(response) {
					$rootScope.successMessage();
				}, function(response) {
					$rootScope.errorMessage(response);
				});
			};

			$scope.deleteTeam = function(id) {
				if(confirm($translate.instant('common.deleteConfirm'))) {
					$http.post('services/team/delete/' + id).then(function(response) {
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
