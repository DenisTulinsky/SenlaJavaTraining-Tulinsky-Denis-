'use strict';
angular.module('BookShopWebApp').controller('ordersCtrl', function($scope, $http,ordersService) {

   ordersService.getOrders(function(response) {
    	$scope.orders = response;
    });
 

    });