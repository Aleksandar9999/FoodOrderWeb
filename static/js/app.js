const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const Users= { template: '<users></users>' }
const Restaurants= { template: '<restaurants></restaurants>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'login', component: Login},
		{ path: '/registration', name: 'registration', component: Registration},
		{ path: '/users', name: 'users', component: Users},
		{ path: '/restaurants', name: 'restautants', component: Restaurants},
		{ path: '/profile', name: 'profile', component: { template: '<profile></profile>' }},

	  ]
});

var app = new Vue({
	router,
	el: '#main'
});