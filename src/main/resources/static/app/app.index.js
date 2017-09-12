angular.
module('pokemonApp').
controller('indexCtrl', function ($scope, $rootScope, $http, $translate, $compile, DTOptionsBuilder) {

	var _successTitle;
	var _successMessage;
	var _infoTitle;
	var _infoMessage; 
	var _errorTitle;
	  
	$scope.init = function() {
		$scope.now = new Date();
		$scope.initMessages();
		$scope.initSecurityConfig();
	}
	  
	$scope.initMessages = function() {
		$translate('title.successMessage').then(function(text) {_successTitle = text;});
		$translate('message.success').then(function(text) {_successMessage = text;});
		$translate('title.infoMessage').then(function(text) {_infoTitle = text;});
		$translate('message.loadData').then(function(text) {_infoMessage = text;});
		$translate('title.errorMessage').then(function(text) {_errorTitle = text;});
	};

	$scope.initSecurityConfig = function() {
		$http.get('services/security/config').then(function(response) {
			$rootScope.config = response.data;
		});
	};
	    
	$rootScope.successMessage = function(response) {

		var title = _successTitle;
		var message = _successMessage;
		
		if(response != null && response.data != null && response.data != '') {
			if(angular.isArray(response.data)) {
				message += "<br/>";
				for(var i = 0; i < response.data.length; i++) {
					if(response.data[i] != null && response.data[i] != '') {
						message += "<br/>" + response.data[i];
					}
				}
			} else {
				message += "<br/><br/>" + response.data;
			}
		}
		
		return new PNotify({
            title: title,
            text: message,
            type: 'success',
            animate: {
               animate: true,
               in_class: 'bounceIn',
               out_class: 'bounceOut'
            }
        });
	}
	
	$rootScope.loadingMessage = function(callback) {

		var hide = true;
		if(callback) {
			hide = false;
		} 
		
		var title = _infoTitle;
		var message = _infoMessage;
		
		var notify = new PNotify({
            title: title,
            text: message,
            type: 'info',
            hide: hide,
            animate: {
               animate: true,
               in_class: 'bounceIn',
               out_class: 'bounceOut'
            }
		});
		
		if(callback) {
			callback(notify);
		}
	}	
/*	
	$rootScope.errorMessage = function(response) {

		console.debug(response);
		
		var title = _errorTitle;
		var messageTranslated;

		$translate(response.status == -1 ? "message.errorMessageApiUnavailable" : "message.errorMessage", {
			'path': response.data != null ? response.data.path : null,
			'error':  response.data != null ? response.data.error : null,
			'exception':  response.data != null ? response.data.exception : null,
			'message':  response.data != null ? response.data.message : null
		}).then(function(text) {
			messageTranslated = text;

			if(response.data != null && response.data != '') {
				if(angular.isArray(response.data)) {
					messageTranslated += "<br/>";
					for(var i = 0; i < response.data.length; i++) {
						if(response.data[i] != null && response.data[i] != '') {
							messageTranslated += "<br/>" + response.data[i];
						}
					}
				} else {
					messageTranslated += "<br/><br/>" + response.data;
				}
			}
			
			return new PNotify({
	            title: title,
	            text: messageTranslated,
	            type: 'error',
	            hide: false,
	            animate: {
                   animate: true,
                   in_class: 'bounceIn',
                   out_class: 'bounceOut'
	            }
	        });
		});
	}
*/	
	$rootScope.errorMessage = function(response) {

		var title = _errorTitle;
		var message = $translate.instant('message.errorMessage'); 

		return new PNotify({
            title: title,
            text: message,
            type: 'error',
            hide: false,
            animate: {
               animate: true,
               in_class: 'bounceIn',
               out_class: 'bounceOut'
            }
        });
	};
	
	$rootScope.newDTOptionsBuilder = function(controllerScope, url) {

		return DTOptionsBuilder.newOptions()
			.withOption('processing', true)
			.withOption('responsive', true)
            .withOption('ajax', {
            	method: 'GET',
            	url: url,
            	headers: {
                    'Content-type': 'application/json'
            	}           	
            })
			.withOption('createdRow', function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
	           	$compile(nRow)(controllerScope);
	         })
	         .withOption('lengthMenu', [20]);        
	};
	
	$rootScope.doPost = function(url, param, successCallback, warningCallback, errorCallback, hideSuccess) {
		
		$rootScope.loadingMessage(function(notify) {

			$http.post(url, param).then(function(response) {
				notify.remove();
				
				if(!hideSuccess) {
					$rootScope.successMessage();
				}
				
				if(successCallback) {
					successCallback(response);
				}
			}, function(response) {
				notify.remove();
				
				if(response.data.exception.indexOf('DataIntegrityViolationException') >= 0
						&& (response.data.path.indexOf('delete') >= 0 || response.data.path.indexOf('save') >= 0)) {
					
					if(response.data.path.indexOf('delete') >= 0) {
						$rootScope.warningMessage('message.deleteConstraint');
					} else {
						$rootScope.warningMessage('message.requiredFields');
					}
					
					if(warningCallback) {
						warningCallback();
					}
				} else if(response.data.exception != null && response.data.message.indexOf('MessageException') >= 0) {
					var message = response.data.message.substring(response.data.message.indexOf(":")+2);
					$rootScope.warningMessage(message);
					
					if(warningCallback) {
						warningCallback();
					}
				} else {
					$rootScope.errorMessage(response);
					
					if(errorCallback) {
						errorCallback();
					}
				}
			});	
		});
	};

});