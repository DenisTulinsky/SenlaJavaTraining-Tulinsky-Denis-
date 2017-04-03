'use strict';
angular.module('BookShopWebApp').controller('bookCtrl', function($scope, $http, $rootScope,$uibModal,$document, bookService) {

    $scope.addToOrder = function(book){
  		if(!$rootScope.basket){
  			$rootScope.basket = [];
  			$rootScope.basketId = [];
  		}
   		 $rootScope.basket.push(book);
  		 $rootScope.basketId.push(book.id);
       console.log($scope.basket);
  	}

  	$scope.deleteBook = function(id,index){
  		 bookService.deleteBook(id,function(response) {
          $scope.books.splice(index, 1)
       });
    }

    $scope.getBooks = function(sortParam,showUnwanted,id){
      bookService.getBooks(sortParam,showUnwanted,id,function(response) {
      $scope.books = response.data;
      });
    }

  	$scope.existInBasket = function(bookId){ 
      if($rootScope.basketId){
        if($rootScope.basketId.indexOf(bookId) !== -1){
          return true;
          }else{
            return false;
    		}
      }
        return false;
  	}

    $scope.buttonText = function(bookId){ 
      if($rootScope.basketId){
       if($rootScope.basketId.indexOf(bookId) !== -1){
              return "Added";
        }else{
          return "Add to Basket";
        }
      }
      return "Add to Basket";
    }


      $scope.animationsEnabled = true;
    $scope.open = function (book, size) {
      var modalBook ={};
      if(book){
      angular.copy(book,modalBook);
      }
      var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'partials/modalBook.html',
      controller: 'ModalInstanceCtrlBook',
      controllerAs: '$ctrl',
      size: size,
      resolve: {
        book: function () {
          return modalBook;
         }
      }
      });

      $scope.message = {};
      modalInstance.result.then(function (book) {
      $scope.modalBook = book;
      if(book.id){
        bookService.putBook(book, function(response) {
       $scope.message = response.data;
       alert( "message: " + $scope.message);
        $scope.refresh();
        });
        }else{
        bookService.postBook(modalBook, function(response) {
        $scope.message = response.data;
          alert( "message: " + $scope.message);
        });
        }
        }, function () {
         var modalBook ={};
           });
    };

    $scope.getBooks("title");
});