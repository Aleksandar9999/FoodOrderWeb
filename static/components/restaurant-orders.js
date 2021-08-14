Vue.component("restaurant-orders", {
	data: function () {
		return {
            orders:{},
			buttonDisplay:true
			
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
					<select v-model="p.orderStatus"  style="height: 30px;" name="porderStatus" @change="buttonDisplay=true">
						<option value="Processing">Processing</option>
						<option value="InPreparation">In Preparation</option>
						<option value="WaitingDeliverer">Waiting Deliverer</option>
						<option value="Transport">Transport</option>
						<option value="Delivered">Delivered</option>
						<option value="Canceled">Canceled</option>
					</select>
				</td>
				<td>
					<p>{{p.timestamp.date.day}}.{{p.timestamp.date.month}}.{{p.timestamp.date.year}}
					{{p.timestamp.time.hour}}:{{p.timestamp.time.minute}}</p> 
				</td>
				<td>
					<p>{{p.price}}</p>
				</td>
				<td> <button v-if='buttonDisplay' :name='index' @click='submitData(p)'>Potvrdi</button> </td>
			</tr>
		</table>
		
</div>
  
`
	,
	mounted() {
		axios
			.get('/rest/restaurants/'+this.$route.params.id+'/orders')
			.then(response => (this.orders = response.data)).catch(function (error) {
			  alert(error.response.data, "Greska")
		  })
	},
	methods: {
		ss(s){
			console.log(s.target.name)
		},
		submitData(order){
			axios.put('/rest/orders/'+order.id,order).then(response=>{
				alert("Uspijesno");
			})

		}
	}
});
