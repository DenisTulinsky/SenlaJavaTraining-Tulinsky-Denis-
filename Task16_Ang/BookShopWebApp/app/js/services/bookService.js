angular.module('BookShopWebApp').factory('bookService', function($http){
	
function getBooks(sortParam,showUnwanted,id,callback){
		    return $http({
			method: "GET",		
            url: "http://localhost:8080/Servlets/BooksServlet", 
            params: {criterion: sortParam, showUnwanted: showUnwanted, id: id}}).then(callback);
	    }
	
function  postBook(book,callback){
		    return $http.post('http://localhost:8080/Servlets/BooksServlet', book).then(callback);
		}
	
function putBook(book,callback){
   return $http.put('http://localhost:8080/Servlets/BooksServlet', book).then(callback);
    	}

function  deleteBook(id,callback){
		  return $http.delete('http://localhost:8080/Servlets/BooksServlet', {params: {id: id}}).then(callback);
		}

    return {
		getBooks : function (sortParam,showUnwanted,id,callback){
			return 	getBooks(sortParam,showUnwanted,id,callback);
		},
		postBook : function (book,callback){
			return 	postBook(book,callback);
		},
		putBook : function (book,callback){
			return 	putBook(book,callback);
		},
		deleteBook : function (id,callback){
			return 	deleteBook(id,callback);
		}
	}
});

