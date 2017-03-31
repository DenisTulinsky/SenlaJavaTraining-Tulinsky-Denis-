'use strict';
angular.module('BookShopWebApp').controller('preorderCtrl', function($scope, $http, preorderService) {
 
  	$scope.getPreorders = function(sortParam){
  		preorderService.getPreorders(sortParam, function(response) {
    	$scope.preorders = response.data;
    });
  	}
  	 
   $scope.getPreorders("title");
});