angular.module('BookShopWebApp').controller('ModalInstanceCtrl', function ($uibModalInstance, book) {
  var $ctrl = this;
  $ctrl.book = book;
 
  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.book);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});