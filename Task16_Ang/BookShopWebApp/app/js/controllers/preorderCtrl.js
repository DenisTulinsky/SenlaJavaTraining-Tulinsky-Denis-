'use strict';
angular.module('BookShopWebApp').controller('preorderCtrl', function($scope, $http,$uibModal,$document, preorderService) {
 
  	
    $scope.addPreorder = function(book){
        var preorder = preorderService.createPreorder(book);
        preorderService.postPreorder(preorder, function(response) {
          window.alert(response.status);
         });
    }

    $scope.getPreorders = function(sortParam){
  		preorderService.getPreorders(sortParam, function(response) {
    	$scope.preorders = response.data;
    });
      $scope.sortParam = sortParam;
  	}
  	 
     $scope.deletePreorder = function(id, index){ 
  		 preorderService.deletePreorder(id,function(response) {
  		 	$scope.preorders.splice(index, 1)
        window.alert(response.status);
    });
    }

      $scope.animationsEnabled = true;
    $scope.open = function (preorder, size) {
      var modalPreorder ={};
      angular.copy(preorder,modalPreorder);
      
        var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'partials/modalPreorder.html',
      controller: 'ModalInstanceCtrlPreorder',
      controllerAs: '$ctrl',
      size: size,
      resolve: {
        preorder: function () {
          return modalPreorder;
        }
      }
      });

      modalInstance.result.then(function (preorder) {
      $scope.modalPreorder = preorder;
      preorderService.putPreorder(preorder, function(response) {
        window.alert(response.status);
      $scope.getPreorders($scope.sortParam);
      });
     }, function () {
       var modalPreorder ={};
        });
    };

   $scope.getPreorders("title");
});