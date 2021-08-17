Vue.component("orders", {
	data: function () {
		    return {
                orders:null,
				userRole:'',
				currentFilterStatus:''
            }
	},
	template: ` 
<div>
	<table border="1">
		<tr bgcolor="lightgrey">
			<th><p>Status</p>
				<select v-model="currentFilterStatus"  style="height: 30px;" name="currentFilterStatus">
					<option value="">All</option>
					<option value="Delivered">Not Delivered</option>
				</select>
			</th>
			<th>Vrijeme kreiranja</th>
			<th>Ukupna cena</th></tr>
			<tr v-for="(p, index) in filteredList" >
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
				<td v-if="userRole == 'Deliverer' && p.orderStatus == 'WaitingDeliverer'"><button @click='sendRequest(p)'>Preuzmi</button></td>
				<td v-if="userRole == 'Deliverer' && p.orderStatus == 'Transport'"><button @click='finishOrder(p)'>Dostavljeno</button></td>
				<td v-if="userRole == 'Buyer' && p.orderStatus == 'Processing'"><button @click='cancelOrder(p)'>Otkazi</button></td>
			</tr>
		</table>
		<br /> 
</div>		  
`
	, 
	methods : {
		init : function() {
			this.orders = {};
		},
		sendRequest(order){
			axios.post('/rest/deliver-request',order).then(response=>{
				alert("Success.")
			})
		},
		finishOrder(order){
			order.orderStatus='Delivered';
			axios.put('/rest/orders/'+order.id,order).then(response=>{alert("Completed")})
		},
		cancelOrder(order){
			order.orderStatus='Canceled';
			axios.put('/rest/orders/'+order.id,order).then(response=>{alert("Order canceled")})
		}
	},
	computed:{
		filteredList() {
			if (this.orders == null) return;
			return this.orders.filter(order => {
				if(this.currentFilterStatus == ''){
					return true;
				}else{
					return order.orderStatus != this.currentFilterStatus
				}
			})
		}
	},
	mounted () {
		axios.get('/rest/users/me').then(response => {
			this.userRole=response.data.userRole; 
			axios.get('/rest/orders/'+this.userRole.toLowerCase()+'/me').then(response => {
				this.orders = response.data;
			})
		})

        
    }
});