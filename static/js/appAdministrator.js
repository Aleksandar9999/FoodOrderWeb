const Users= { template: '<users></users>' }
const routerAdmin = new VueRouter({
	  routes: [
		{ path: '/', name: 'users', component: Users},
	  ]
});

var appAdmin = new Vue({
	routerAdmin,
	el: '#adminMain'
});