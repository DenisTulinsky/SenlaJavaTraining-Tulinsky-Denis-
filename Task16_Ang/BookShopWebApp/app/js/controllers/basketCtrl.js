'use strict';
angular.module('BookShopWebApp').controller('basketCtrl', function($scope, $http, $rootScope) {

   
    $scope.contents = false;
	  $scope.books =  $rootScope.basket;
    $scope.ids = $rootScope.basketId;
	    
     $scope.deleteFromOrder = function(index,id){
     $scope.books.splice(index, 1);
     $scope.ids.splice($scope.ids.indexOf(id),1);
     $scope.checkContents();
    }
    
  $scope.checkContents = function(){
    if($scope.books && $scope.books.length !== 0 ){
       	$scope.contents = true;
        }else{
          $scope.contents = false;
  		}
    }
   $scope.checkContents();
});