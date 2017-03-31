angular.module('BookShopWebApp').factory('preorderService', function($http){
	
	function getPreorders(sortParam,callback){
		    return $http({
			method: "GET",		
            url: "http://localhost:8080/Servlets/PreordersServlet", 
            params: {criterion: sortParam}}).then(callback);
	}
	
  function  postPreorder(preorder,callback){
		    return $http.post('http://localhost:8080/Servlets/PreordersServlet', preorder).then(callback);
		}

	function  deletePreorder(id,callback){
		  return $http.delete('http://localhost:8080/Servlets/BooksServlet', {params: {id: id}}).then(callback);
		}

  	return {
		getPreorders : function (sortParam,callback){
			return 	getPreorders(sortParam,callback);
		},
		postPreorder : function (preorder,callback){
			return 	postPreorder(preorder,callback);
		},
		deletePreorder : function (id,callback){
			return 	deletePreorder(id,callback);
		}
	}

});