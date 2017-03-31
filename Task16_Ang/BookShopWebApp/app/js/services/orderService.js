angular.module('BookShopWebApp').factory('orderService', function($http){
	
	function getOrders(sortParam,id,callback){
		    return $http({
			method: "GET",		
            url: "http://localhost:8080/Servlets/OrdersServlet", 
            params: {criterion: sortParam, id: id}}).then(callback);
	}

	return {
		getOrders : function (sortParam,id,callback){
			return 	getOrders(sortParam,id,callback);
		}
	}

});