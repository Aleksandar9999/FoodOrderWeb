const Login = { template: '<login></login>' }
const Registration = { template: '<registration></registration>' }
const Administrator= { template: '<administrator></administrator>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'login', component: Login},
		{ path: '/registration', name: 'registration', component: Registration},
		{ path: '/administrator', name: 'administrator', component: Administrator},//TODO: IZMJENA
	  ]
});

var app = new Vue({
	router,
	el: '#main'
});