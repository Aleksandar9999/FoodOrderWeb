const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'login', component: { template: '<login></login>' }},
		{ path: '/registration', name: 'registration', component: { template: '<registration></registration>' }},
		{ path: '/users', name: 'users', component: { template: '<users></users>' }},
		{ path: '/restaurants', name: 'restautants', component: { template: '<restaurants></restaurants>' }},
		{ path: '/profile', name: 'profile', component: { template: '<profile></profile>' }},
		{ path: '/restaurants/:id', name: 'restaurants', component: { template: '<restaurant></restaurant>' }},
		{ path: '/restaurants/:id/orders', name: 'restaurants-orders', component: { template: '<restaurant-orders></restaurant-orders>' }},
		{ path: '/restaurants/:id/buyers', name: 'restaurants-buyers', component: { template: '<restaurant-buyers></restaurant-buyers>' }},

	  ]
});

var app = new Vue({
	router,
	el: '#main'
});