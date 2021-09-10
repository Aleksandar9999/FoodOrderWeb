Vue.component("orders", {
	data: function () {
		return {
			orders: [],
			comments: [],
			username:'',
			userRole: '',
			currentFilterStatus: '',
			comment: '',
			mark: 0,
			myModel: false,
			orderForComment: null,
		}
	},
	template: ` 
	<div>
	<custom-header></custom-header>
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
				<td v-if="userRole == 'Buyer' && p.orderStatus == 'Delivered' && !p.commented"><button @click='openDialog(p)'>Dodaj komentar</button></td>
			</tr>
		</table>
		<br /> 

		<div v-if="myModel">
		<transition name="model">
		 <div class="modal-mask">
		  <div class="modal-wrapper">
		   <div class="modal-dialog" style="color: black;" >
			<div class="modal-content" style="background-color: white; width: 80%; margin: 0 auto;">
				<div class="modal-header">
					<button type="button" class="close" @click="myModel=false"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">Dodaj komentar</h4>
				</div>
				
				<div class="modal-body">
					<div class="form-group">
					 <label>Komentar</label> 
					 <input type="text" class="form-control" v-model="comment"/>
					</div>
					<div class="form-group">
					 <label>Ocena</label>
					 <input type="number" class="form-control" v-model="mark" max="5" min="1"/>
					</div>
                    
                    <div>
					 <input type="button" @click="sendComment()" value='Potvrdi'/>
					</div>
				   
					</div>
			</div>
		   </div>
		  </div>
		 </div>
		</transition>
	   </div>
	   </div>
</div>		  
`
	,
	methods: {
		init: function () {
			this.orders = {};
		},
		sendRequest(order) {
			axios.post('/rest/deliver-request', order).then(response => {
				alert("Success.")
			})
		},
		finishOrder(order) {
			order.orderStatus = 'Delivered';
			axios.put('/rest/orders/' + order.id, order).then(response => { alert("Completed") })
		},
		cancelOrder(order) {
			order.orderStatus = 'Canceled';
			axios.put('/rest/orders/' + order.id, order).then(response => { alert("Order canceled") })
		},
		openDialog(order) {
			this.orderForComment = order;
			this.myModel = true;
		},
		sendComment() {
			this.orderForComment.commented = true;
			axios.post('/rest/comments', { mark: this.mark, comment: this.comment, restaurantId: this.orderForComment.restaurantId, orderId: this.orderForComment.id }).then(
				response => (alert("Success."))
			);

		}
	},
	computed: {
		filteredList() {
			if (this.orders == null) return;
			return this.checkCommented.filter(order => {
				if (this.currentFilterStatus == '') {
					return true;
				} else {
					return order.orderStatus != this.currentFilterStatus
				}
			})
		},
		checkCommented(){
			this.orders.forEach(order => {
				if (this.comments.find(comment => comment.orderId === order.id))
				 order.commented = true;
			});
			return this.orders;
		}
	},
	mounted() {
		axios.get('/rest/users/me').then(response => {
			this.userRole = response.data.userRole;
			this.username=response.data.username;
			axios.get('/rest/orders/' + this.userRole.toLowerCase() + '/me').then(response => {
				this.orders = response.data;
				axios.get('/rest/user/' + this.username + '/comments').then(response => {
					this.comments = response.data;
					
				});
			});
			
		})


	}
});