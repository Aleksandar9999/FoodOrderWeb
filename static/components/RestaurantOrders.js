Vue.component("restaurant-orders", {
	data: function () {
		return {
			requests: null,
			buttonDisplay: true,
			priceMin: 0,
			priceMax: 99999,
			dateMin: new Date().toJSON(),
			dateMax: new Date().toJSON(),
			currentSelectedOrderStatus: '',
			currentSortDir: 'asc',
			currentSort: 'orderStatus'
		}
	},
	template: ` 
	<div id="itemslist">
		<h3 id="title">Narudzbe</h3>
		<table id="userList" border="0" CELLSPACING=0>
			<tr id="headertable">
				<th><p>Status</p>
					<select v-model="currentSelectedOrderStatus"  style="height: 30px;" name="currentSelectedOrderStatus">
						<option value="Processing">Processing</option>
						<option value="InPreparation">In Preparation</option>
						<option value="WaitingDeliverer">Waiting Deliverer</option>
						<option value="Transport">Transport</option>
						<option value="Delivered">Delivered</option>
						<option value="Canceled">Canceled</option>
					</select>
				</th>
				<th @click="sort('price')">
					<p>Cena</p> 
					<p>Od:  <span> <input v-model='priceMin' type='number' min="0"/></span></p>
					<p>Od:  <span> <input v-model='priceMax' type='number' min="0"/></span></p>	
				</th>
				<th @click="sort('timestamp')">
					<p>Datum</p>
					<p>Od:  <span> <input type='date' :value='dateMin.substring(0,10)' @input='setDateMin($event.target.value)'></span></p>
					<p>Od:  <span><input type='date' :value='dateMax.substring(0,10)' @input='setDateMax($event.target.value)'/></span></p>
				</th>
			</tr>
			<tr v-for="(request, index) in sortedList" >
				<td>
					<p v-if="request.order.orderStatus == 'Transport' || request.order.orderStatus == 'Delivered' || request.order.orderStatus == 'Canceled'">{{request.order.orderStatus}}</p>
					<select v-if="!(request.order.orderStatus == 'Transport' || request.order.orderStatus == 'Delivered' || request.order.orderStatus == 'Canceled')" v-model="request.order.orderStatus"  style="height: 30px;" name="porderStatus" @change="submitData(request.order)">
						<option v-if="request.order.orderStatus =='Processing'" value="Processing">Processing</option>
						<option v-if="request.order.orderStatus =='Processing' || request.order.orderStatus =='InPreparation' " value="InPreparation">In Preparation</option>
						<option v-if="request.order.orderStatus =='InPreparation' || request.order.orderStatus =='WaitingDeliverer' " value="WaitingDeliverer">Waiting Deliverer</option>
						
					</select>
				</td>
				
				<td>
					<p>{{request.order.price}}</p>
				</td>
				
				<td>
					<p>{{request.order.timestamp.date.day}}.{{request.order.timestamp.date.month}}.{{request.order.timestamp.date.year}}
					{{request.order.timestamp.time.hour}}:{{request.order.timestamp.time.minute}}</p> 
				</td>
				<td> 
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
				this.requests = response.data;
			}).catch(function (error) {
				alert(error.response.data, "Greska")
			})
	},
	computed: {
		sortedList() {
			if (this.searchList == null) return;
			return this.searchList.sort((a, b) => {
				let modifier = 1;
				if (this.currentSortDir === 'desc') modifier = -1;
				if (this.currentSort === 'timestamp') {
					let timestampA = a.order.timestamp.date.year + '-' + a.order.timestamp.date.month + '-' + a.order.timestamp.date.day;
					let timestampB = b.order.timestamp.date.year + '-' + b.order.timestamp.date.month + '-' + b.order.timestamp.date.day;
					let dateA = new Date(timestampA).withoutTime().getTime();
					let dateB = new Date(timestampB).withoutTime().getTime();
					if (dateA < dateB) return -1 * modifier;
					if (dateA > dateB) return modifier;
					return 0;
				} else {
					if (a.order[this.currentSort] < b.order[this.currentSort]) return -1 * modifier
					if (a.order[this.currentSort] > b.order[this.currentSort]) return modifier
					return 0;
				}
			})
		},
		searchList() {
			if (this.filteredList == null) return;
			return this.filteredList.filter(request => {
				var orderDateString = request.order.timestamp.date.year + '-' + request.order.timestamp.date.month + '-' + request.order.timestamp.date.day;
				console.log(new Date(orderDateString).withoutTime().getTime() >= new Date(this.dateMin.substring(0, 10)).withoutTime().getTime())
				return request.order.price >= this.priceMin && request.order.price <= this.priceMax
					&& new Date(orderDateString).withoutTime().getTime() >= new Date(this.dateMin.substring(0, 10)).withoutTime().getTime()
					&& new Date(orderDateString).withoutTime().getTime() <= new Date(this.dateMax.substring(0, 10)).withoutTime().getTime()
			})
		},
		filteredList() {
			if (this.requests == null) return;
			return this.requests.filter(request => {
				return request.order.orderStatus.toLowerCase().includes(this.currentSelectedOrderStatus.toLowerCase())
			})
		}


	},
	methods: {
		sort(s) {
			if (s === this.currentSort) {
				this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
			}
			this.currentSort = s
		},
		submitData(order) {
			axios.put('/rest/orders/' + order.id, order).then(response => {
				alert("Uspijesno");
			})
		},
		submitDeliverer(request) {
			request.status = 'Confirmed';
			axios.put('/rest/deliver-request/' + request.id, request).then(response => { alert("Succes") })
		},
		setDateMin(val) {
			this.dateMin = new Date(val).toJSON();
		},
		setDateMax(val) {
			this.dateMax = new Date(val).toJSON();
		}
	}
});
