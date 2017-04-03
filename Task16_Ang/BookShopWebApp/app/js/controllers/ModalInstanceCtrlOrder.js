angular.module('BookShopWebApp').controller('ModalInstanceCtrlOrder', function ($uibModalInstance,$scope, order, view) {
  var $ctrl = this;
  $ctrl.order = order;
  $scope.modalHeader = (angular.equals("view", view)) ? "View Order:" : "Edit this Order:"

  $ctrl.showNoInput = function (){
  	        if(angular.equals("view", view)){
           return true;
  	        } else {return false;
  	       }
  };

  $ctrl.deleteBook = function (index){
  	        $ctrl.order.books.splice(index, 1)
  };

  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.order);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});