'use strict';

var app = angular.module('BookShopWebApp', [
  'ngRoute'
]);

/**
 * Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/home.html"})
    // Pages
    .when("/preorders", {templateUrl: "partials/preorders.html", controller: "preordersCtrl"})
    .when("/csv", {templateUrl: "partials/CSV.html", controller: "csvCtrl"})
    .when("/orders", {templateUrl: "partials/orders.html", controller: "ordersCtrl"})
    .when("/books", {templateUrl: "partials/books.html", controller: "booksCtrl"})
      
    
}]);



