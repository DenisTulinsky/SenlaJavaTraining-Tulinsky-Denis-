'use strict';

var app = angular.module('BookShopWebApp', ['ui.bootstrap', 'ngRoute']);

/**
 * Routes
 */
 app.config(['$locationProvider', function($locationProvider) {
  $locationProvider.hashPrefix('');
}]);

app.config(['$routeProvider', function ($routeProvider) {
	  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/home.html"})
    .when("/home", {templateUrl: "partials/home.html"})
    // Pages
    .when("/preorders", {templateUrl: "partials/preorders.html", controller: "preorderCtrl"})
    .when("/csv", {templateUrl: "partials/CSV.html", controller: "csvCtrl"})
    .when("/orders", {templateUrl: "partials/orders.html", controller: "orderCtrl"})
    .when("/books", {templateUrl: "partials/books.html", controller: "bookCtrl"})
     .when("/basket", {templateUrl: "partials/basket.html", controller: "basketCtrl"})
    
}]);



