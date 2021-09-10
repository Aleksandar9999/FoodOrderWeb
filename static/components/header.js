Vue.component("custom-header", {
	data: function () {
		return {
			userRole: '',
			restaurantId:'',
		}
	},
	template: ` 
    <div id="header">
			<ul>
				<li v-if="userRole == 'Administrator'"><a href="/index.html#/users">Korisnici</a></li>
				<li v-if="userRole == 'Administrator'"><a href="/index.html#/users/suspicious">Sumnjivi korisnici</a></li>
				<li v-if="userRole == 'Administrator'"><a href="/index.html#/registration/managers">Registruj menadzera</a></li>
				<li v-if="userRole == 'Administrator'"><a href="/index.html#/registration/deliverers">Registruj dostavljaca</a></li>
				<li><a href="/index.html#/restaurants">Restorani</a></li>
				<li v-if="!userRole "><a href="/index.html">Prijavite se</a></li>
				<li v-if="userRole"><a href="/index.html#/profile">Profil</a></li>
				<li v-if="userRole == 'Buyer'"><a href="/index.html#/cart">Korpa</a></li>
				<li v-if="userRole == 'Buyer' || userRole == 'Deliverer'"><a href="/index.html#/orders">Narudzbe</a></li>
				<li v-if="userRole == 'Manager'"><a :href="'/index.html#/restaurants/'+restaurantId+'/settings'">Moj restoran</a></li>
				<li v-if="userRole == 'Manager'"><a :href="'/index.html#/restaurants/'+restaurantId+'/orders'">Narudzbe</a></li>
				<li v-if="userRole == 'Manager'"><a :href="'/index.html#/restaurants/'+restaurantId+'/buyers'">Kupci</a></li>
				
			</ul>
	</div>
`
	,
	mounted() {
		axios.get('/rest/users/me').then(response => {
			this.userRole = response.data?.userRole;
			this.restaurantId=response.data?.restaurantId
		});
	},
});