angular.module('BookShopWebApp').service('ordersService', function(ordersFactory){

	this.getOrders = function(callback) { 

		return ordersFactory.getOrders(callback); 
	}
			
});