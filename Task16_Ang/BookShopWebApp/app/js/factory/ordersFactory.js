angular.module('BookShopWebApp').factory('ordersFactory', function($http){
	
	function getOrders(callback){
		
		return $http.get("http://localhost:8080/Servlets/OrdersServlet?criterion=customer").success(callback);
	}

	return {
		getOrders : function (callback){

			return 	getOrders(callback);
		}
	}

});