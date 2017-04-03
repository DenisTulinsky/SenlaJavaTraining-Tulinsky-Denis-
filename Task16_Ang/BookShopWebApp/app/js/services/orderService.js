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
 
     function  createOrder(books){
     	   var price = 0;
     	   var d = new Date();
           d.setDate(d.getDate()+5);
           var date = d.toISOString().split("T")[0] ;
        angular.forEach(books, function(value, key) {
        price += value.price;
        });
          var order = {books:books, status: "ACTIVE", price : price, executionDate : date};
		  return order;
	}
	return {
		getOrders : function (sortParam,id,callback){
			return 	getOrders(sortParam,id,callback);
		},		
		postOrder : function (order,callback){
			return 	postOrder(order,callback);
		},
		putOrder: function (order,callback){
			return 	putOrder(order,callback);
		},
		deleteOrder : function (id,callback){
			return 	deleteOrder(id,callback);
		},
		createOrder : function (books){
			return 	createOrder(books);
		}
	}
});