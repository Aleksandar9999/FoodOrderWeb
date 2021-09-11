Vue.component("cart", {
	data: function () {
		return {
			sc: null,
			total: 0,
			discount: 0
		}
	},
	template: `
	<div>
	<custom-header></custom-header> 
	<div>
		Proizvodi u korpi:
		<table border="1">
		<tr bgcolor="lightgrey">
			<th></th><th>Naziv</th><th>Jedinicna cena</th><th>Komada</th><th>Ukupna cena</th></tr>
			<tr v-for="i in sc.articles">
			<td>
					<img width="90px" height="90px"  :src="'../files/images/'+i.article.imageUrl">
					
			</td>
			<td> <p style="color: white;">{{i.article.name}}</p></td>
			<td> <p style="color: white;">{{i.article.price}}</p></td>
			<td> <input v-model="i.quantity" type="number"/> </td>
			<td> {{i.quantity * i.article.price}} </td>
			</tr>
		</table>
		<br /> 
		<button v-on:click="clearSc" >Obriši korpu</button>
        <button v-on:click="createOrder" >Kreiraj narudzbinu</button>
		
		<p style="color: white;">
		Ukupno: {{totalPriceCalculator}} dinara.
		</p>
	<p>
		<a href="#/">Proizvodi</a>
	</p>
	</div>
</div>		  
`
	,
	methods: {
		init: function () {
			this.sc = {};
			this.total = 0.0;
		},
		clearSc: function () {
				axios
					.post('/rest/cart/delete')
					.then(response => (this.init()))
		},
		createOrder() {
			console.log(this.sc)
			axios.post('/rest/orders', this.sc).then(response => {
				alert("Uspijesno kreirana narudzba");
				this.$router.push('/orders');
			})
		}
	},
	computed: {
		totalPriceCalculator: function () {
			let total = 0;
			this.sc.articles.forEach(element => {
				total += element.article.price * element.quantity;
			});
			total -= total * this.discount / 100;
			this.sc.price = total;
			return total;
		}
	},
	mounted() {
		axios
			.get('rest/cart')
			.then(response => {
				this.sc = response.data;
				console.log(this.sc)
			})
			.catch(error => (alert(error.response.data)));
		axios.get('/rest/users/me').then(response => {
			this.discount = response.data.buyerType.discount ? response.data.buyerType.discount : 0;
		})
	}
});