'use strict';
angular.module('BookShopWebApp').controller('booksCtrl', function($scope, $http,bookService) {

   bookService.getBooks(function(response) {
    	$scope.books = response;
    });
 

    $scope.basket = [];
 
  	$scope.addToOrder = function(book){
  		$scope.basket.push(book);
  		console.log($scope.basket);
  	}




});