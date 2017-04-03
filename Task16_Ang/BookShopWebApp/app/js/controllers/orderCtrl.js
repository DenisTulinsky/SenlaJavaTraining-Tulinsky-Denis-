'use strict';
angular.module('BookShopWebApp').controller('orderCtrl', function($scope, $http,$uibModal,$document, $rootScope, orderService) {

$scope.startDate = $rootScope.startDate;
$scope.endDate = $scope.endDate;

$scope.addOrder = function(order){
        orderService.postOrder(order,function(response) {
   	 });
 }

$scope.getOrders = function(sortParam,startDate,endDate,id){
	 if(angular.equals({}, startDate)){
           $scope.hideExecuted = true;
  	        } else {
  	        	$scope.hideExecuted = false;
  	       }
  	orderService.getOrders(sortParam,id,function(response) {
    	$scope.orders = response.data;
    });
  		$scope.sortParam = sortParam;
}

$scope.deleteOrder = function(id,index){
  		 orderService.deleteOrder(id,function(response) {
          $scope.orders.splice(index, 1)
        });
}

   $scope.animationsEnabled = true;
$scope.open = function (books, order, view, size) {
  var modalOrder ={};
  $scope.view = view;
  if(order){
      angular.copy(order,modalOrder);
    }else{
     modalOrder = orderService.createOrder(books);
    }  
      var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
      ariaLabelledBy: 'modal-title',
      ariaDescribedBy: 'modal-body',
      templateUrl: 'partials/modalOrder.html',
      controller: 'ModalInstanceCtrlOrder',
      controllerAs: '$ctrl',
      size: size,
      view: view,
      resolve: {
        order: function () {
          return modalOrder;
        },
        view: function() {
                    return view;
                }
    }
    });

    modalInstance.result.then(function (order) {
     $scope.modalOrder = order;
 if(order.id){
         orderService.putOrder(order, function(response) {
          window.alert(response.status);
     $scope.getOrders($scope.sortParam);
     });
        }else{
       orderService.postOrder(order, function(response) {
      window.alert(response.status);
         
           });
        }

     
    }, function () {
       var modalOrder ={};
       });
};

$scope.getOrders("customer");
});