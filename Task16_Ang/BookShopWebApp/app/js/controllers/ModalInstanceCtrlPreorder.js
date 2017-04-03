angular.module('BookShopWebApp').controller('ModalInstanceCtrlPreorder', function ($uibModalInstance,$scope, preorder) {
  var $ctrl = this;
  $ctrl.preorder = preorder;
  
  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.preorder);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});