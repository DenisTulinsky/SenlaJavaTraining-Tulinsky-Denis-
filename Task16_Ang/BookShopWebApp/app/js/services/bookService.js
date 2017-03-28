angular.module('BookShopWebApp').service('bookService', function(bookFactory){

	this.getBooks = function(callback) { 

		return bookFactory.getBooks(callback); 
	}
			
});