Vue.component("restaurant-orders", {
	data: function () {
		return {
			orders: {},
			buttonDisplay: true

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
			<tr v-for="(request, index) in orders" >
				<td>
					<select v-model="request.order.orderStatus"  style="height: 30px;" name="porderStatus" @change="buttonDisplay=true">
						<option value="Processing">Processing</option>
						<option value="InPreparation">In Preparation</option>
						<option value="WaitingDeliverer">Waiting Deliverer</option>
						<option value="Transport">Transport</option>
						<option value="Delivered">Delivered</option>
						<option value="Canceled">Canceled</option>
					</select>
				</td>
				<td>
					<p>{{request.order.timestamp.date.day}}.{{request.order.timestamp.date.month}}.{{request.order.timestamp.date.year}}
					{{request.order.timestamp.time.hour}}:{{request.order.timestamp.time.minute}}</p> 
				</td>
				<td>
					<p>{{request.order.price}}</p>
				</td>
				<td> 
					<button :name='index' @click='submitData(request.order)'>Submit</button> 
					<p v-if="request.deliverer">Deliverer: {{request.delivererId}}</p>
					<button v-if="request.deliverer && request.status == 'Pending'" @click='submitDeliverer(request)'>Submit deliverer</button> 
				</td>
			</tr>
		</table>
		
</div>
  
`
	,
	mounted() {
		axios
			.get('/rest/restaurants/' + this.$route.params.id + '/orders')
			.then(response => {
				this.orders = response.data;
				console.log(this.orders)
			}).catch(function (error) {
				alert(error.response.data, "Greska")
			})
	},
	methods: {
		submitData(order) {
			axios.put('/rest/orders/' + order.id, order).then(response => {
				alert("Uspijesno");
			})
		},
		submitDeliverer(request){
			request.status='Confirmed';
			axios.put('/rest/deliver-request/'+request.id,request).then(response=>{alert("Succes")})
		}
	}
});
