angular.module('BookShopWebApp').factory('orderService', function($http){
	
	function getOrders(sortParam,id,callback){
		    return $http({
			method: "GET",		
            url: "http://localhost:8080/Servlets/OrdersServlet", 
            params: {criterion: sortParam, id: id}}).then(callback);
	}

    function  deleteOrder(id,callback){
		  return $http.delete('http://localhost:8080/Servlets/OrdersServlet', {params: {id: id}}).then(callback);
	}

    function  postOrder(order,callback){
		    return $http.post('http://localhost:8080/Servlets/OrdersServlet', order).then(callback);
	}
	
    function putOrder(order,callback){
  	return $http.put('http://localhost:8080/Servlets/OrdersServlet', order).then(callback);
  	}

	return {
		getOrders : function (sortParam,id,callback){
			return 	getOrders(sortParam,id,callback);
		},		
		postOrder : function (order,callback){
			return 	postOrder(book,callback);
		},
		putOrder: function (order,callback){
			return 	putOrder(order,callback);
		},
		deleteOrder : function (id,callback){
			return 	deleteOrder(id,callback);
		}
	}
});