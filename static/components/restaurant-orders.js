Vue.component("restaurant-orders", {
	data: function () {
		return {
            orders:null
		}
	},
	template: ` 
	<div id="itemslist">
		<h3 id="title">Narudzbe</h3>
		<table id="userList" border="0" CELLSPACING=0>
			<tr id="headertable">
				<th >Status</th>
				<th >Vrijeme kreiranja</th>
				<th>Cena</th>
			</tr>
			<tr v-for="(p, index) in orders" >
				<td>
					<p>{{p.orderStatus}}</p>
				</td>
				<td>
					<p>{{p.timestamp.date.day}}.{{p.timestamp.date.month}}.{{p.timestamp.date.year}}
					{{p.timestamp.time.hour}}:{{p.timestamp.time.minute}}</p> 
				</td>
				<td>
					<p>{{p.price}}</p>
				</td>
			</tr>
		</table>
</div>
  
`
	,
	mounted() {
		axios
			.get('/rest/restaurants/'+this.$route.params.id+'/orders')
			.then(response => (this.orders = response.data))
	},
	methods: {
	
	}
});
