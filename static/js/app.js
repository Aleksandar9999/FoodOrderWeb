const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'login', component: Login},
		{ path: '/registration', name: 'registration', component: Registration},
		
	  ]
});

var app = new Vue({
	router,
	el: '#main'
});