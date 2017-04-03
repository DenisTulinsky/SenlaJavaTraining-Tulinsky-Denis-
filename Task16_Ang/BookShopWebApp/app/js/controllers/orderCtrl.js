'use strict';
angular.module('BookShopWebApp').controller('orderCtrl', function($scope, $http,$uibModal,$document,orderService) {

$scope.startDate = {};
$scope.endDate = {};

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
$scope.open = function (order, view, size) {
  var modalOrder ={};
  $scope.view = view;
  angular.copy(order,modalOrder);
  
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
      orderService.putOrder(order, function(response) {
     $scope.getOrders($scope.sortParam);
     });
    }, function () {
       var modalOrder ={};
       });
};

$scope.getOrders("customer");
});