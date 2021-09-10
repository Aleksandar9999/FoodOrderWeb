const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'login', component: { template: '<login></login>' }},
		{ path: '/registration/:role', name: 'registration', component: { template: '<registration></registration>' }},
		{ path: '/users', name: 'users', component: { template: '<users></users>' }},
		{ path: '/restaurants', name: 'restautants', component: { template: '<restaurants></restaurants>' }},
		{ path: '/profile/:id', name: 'profile', component: { template: '<profile></profile>' }},
		{ path: '/profile', name: 'profile', component: { template: '<profile></profile>' }},
		{ path: '/restaurants/:id/settings', name: 'restaurants', component: { template: '<restaurant-settings></restaurant-settings>' }},
		{ path: '/restaurants/:id/orders', name: 'restaurants-orders', component: { template: '<restaurant-orders></restaurant-orders>' }},
		{ path: '/restaurants/:id/buyers', name: 'restaurants-buyers', component: { template: '<restaurant-buyers></restaurant-buyers>' }},
		{ path: '/restaurants/:id/manager', name: 'registration-manager', component: { template: '<registration-manager></registration-manager>' }},
		{ path: '/restaurants/:id', name: 'restaurant', component: { template: '<restaurant></restaurant>' }},
		{ path: '/orders', name: 'restaurant', component: { template: '<orders></orders>' }},
		{ path: '/cart', name: 'cart', component: { template: '<cart></cart>' }},
		{ path: '/users/suspicious', name: 'suspicious-users', component: { template: '<suspicious-users></suspicious-users>' }},
		
	  ]
});

var app = new Vue({
	router,
	el: '#main'
});