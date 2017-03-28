angular.module('BookShopWebApp').factory('bookFactory', function($http){
	
	function getBooks(callback){
		
		return $http.get("http://localhost:8080/Servlets/BooksServlet?criterion=title").success(callback);
	}

	return {
		getBooks : function (callback){

			return 	getBooks(callback);
		}
	}

});