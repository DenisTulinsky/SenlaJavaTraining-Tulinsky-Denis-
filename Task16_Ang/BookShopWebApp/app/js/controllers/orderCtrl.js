'use strict';
angular.module('BookShopWebApp').controller('orderCtrl', function($scope, $http,orderService) {
  
 $scope.getOrders = function(sortParam,id){
  		orderService.getOrders(sortParam,id,function(response) {
    	$scope.orders = response.data;
    });
}
    });