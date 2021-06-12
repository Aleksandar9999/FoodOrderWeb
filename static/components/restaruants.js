Vue.component("restaurants", {
	data: function () {
		return {
			idAdmin: -1,
			restaurants: null,
			searchName: '',
			searchLocation: '',
			searchAvgRate: '',
			currentSort:'name',
			currentSortDir:"asc",
			currentFilterType:''
		}
	},
	template: ` 
	<div id="itemslist">
		<h3 id="title">Restorani</h3>
		<table id="searchtabe" border="0" cellspacing=0>
			<tr>
				<td><div class="field"><input v-model="searchName" type="text" placeholder="Naziv" name="name"></div></td>
				<td><div class="field"><input v-model="searchLocation" type="text" placeholder="Lokacija" name="surname"></div></td>
				<td><div class="field"><input v-model="searchAvgRate" type="text" placeholder="Prosjecna ocjena" name="username"></div></td>
				<td>
				<div class="field">
					<select v-model="currentFilterType"  style="height: 30px;" name="currentFilterType">
						<option value="Italian">Italian</option>
						<option value="Chinese">Chinese</option>
						<option value="Grill">Grill</option>
						<option value="Other">Other</option>
					</select>
				</div>
			    </td>
			</tr>
		</table>

		<table id="userList" border="0" CELLSPACING=0>
			<tr id="headertable">
				<th @click="sort('name')">Naziv</th>
				<th @click="sort('location')">Lokacija</th>
				<th @click="sort('restaurantType')">Tip restorana</th>
				<th @click="sort('avgRate')">Prosecna ocjena</th>
			</tr>
				
			<tr v-for="(p, index) in sortedList" @click=showRestaurant(p.id) >
				<td>
					<img width="90px" height="90px"  src="../files/images/pizza.jpg">
					<p>{{p.name}}</p>
				</td>
				<td>
					<p>{{p.location.address.street}} {{p.location.address.number}}</p> 
				
					<p>{{p.location.address.city}} {{p.location.address.zipCode}}</p>
					</td>
				<td>
					<p id="usernamep">@{{p.restaurantType}}</p>
				</td>
				<td></td>
			</tr>
		</table>
</div>
  
`
	,
	mounted() {
		axios
			.get('rest/restaurants')
			.then(response => (this.restaurants = response.data))
	},
	computed: {
		sortedList() {
			if(this.searchList==null) return;
			return this.searchList.sort((a,b)=>{
				let modifier=1;
				if(this.currentSortDir==='desc') modifier=-1;
				if(a[this.currentSort]< b[this.currentSort]) return -1*modifier
				if(a[this.currentSort]> b[this.currentSort]) return modifier
				return 0
			})
		},
		searchList() {
			if (this.filteredList == null) return;
			return this.filteredList.filter(restaurant => {
				return restaurant.name.toLowerCase().includes(this.searchName.toLowerCase()) 
						&& restaurant.location.address.street.toLowerCase().includes(this.searchLocation.toLowerCase()) 
					})
		},
		filteredList(){
			if (this.restaurants == null) return;
			return this.restaurants.filter(restaurant => {
					return restaurant.restaurantType.toLowerCase().includes(this.currentFilterType.toLowerCase())
			})
		}
	},

	methods: {
		sort: function (s) {
			if (s === this.currentSort) {
				this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
			}
			this.currentSort = s
		},
		showRestaurant:function(s){
			router.push('restaurants/'+s)
		}
	}
});
