angular.module('BookShopWebApp').controller('ModalInstanceCtrlBook', function ($uibModalInstance,$scope, book) {
  var $ctrl = this;
  $ctrl.book = book;
  $scope.modalHeader = (angular.equals({}, book)) ? "Add Book:" : "Edit this Book:"

  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.book);
  };

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});