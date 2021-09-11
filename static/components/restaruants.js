Vue.component("restaurants", {
	data: function () {
		return {
			isAdmin: false,
			restaurants: null,
			searchName: '',
			searchLocation: '',
			searchAvgRate: '',
			currentSort: 'name',
			currentSortDir: "desc",
			currentFilterType: '',
			currentFilterTypeStatus: '',
			noRestaurats: false,
		}
	},
	template: ` 
	<div><custom-header></custom-header>
	<div id="itemslist">
		<h3 id="title">Restorani <span v-if="isAdmin"><button @click="addNewPage">Dodaj novi</button></span></h3> 
		<p v-if="noRestaurats">Nema restorana u ponudi</p>
		<div v-if="!noRestaurats">
		<table id="searchtabe" border="0" cellspacing=0>
			<tr>
				<td><div class="field"><input v-model="searchName" type="text" placeholder="Naziv" name="name"></div></td>
				<td><div class="field"><input v-model="searchLocation" type="text" placeholder="Lokacija" name="surname"></div></td>
				<td><div class="field"><input v-model="searchAvgRate" type="text" placeholder="Prosjecna ocjena" name="username"></div></td>
				<td>
				<div class="field">
					<select v-model="currentFilterType"  style="height: 30px;" name="currentFilterType">
						<option value="">All</option>
						<option value="Italian">Italian</option>
						<option value="Chinese">Chinese</option>
						<option value="Grill">Grill</option>
						<option value="Other">Other</option>
					</select>
				</div>
				<div class="field">
					<select v-model="currentFilterTypeStatus"  style="height: 30px;" name="currentFilterTypeStatus">
						<option value="">All</option>
						<option value="true">Open</option>
						<option value="false">Closed</option>
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
				
			<tr v-for="(p, index) in sortedList" @click=showRestaurant(p.id)  :class="p.status ? 'open-restaurant' : 'closed-restaruant'">
				<td>
					<img width="90px" height="90px"  :src="'../files/images/'+p.logoUrl">
					<p>{{p.name}}</p>
				</td>
				<td>
					<p>{{p.location.address.street}} {{p.location.address.number}}</p> 
				
					<p>{{p.location.address.city}} {{p.location.address.zipCode}}</p>
				</td>
				<td>
					<p id="usernamep">{{p.restaurantType}}</p>
				</td>
				<td><p>{{p.avgRate}}</p></td>
			</tr>
		</table>
		</div>
	 </div>
  </div>
`
	,
	mounted() {
		axios
			.get('rest/restaurants')
			.then(response => {
				this.restaurants = response.data;
				if (this.restaurants.length == 0) this.noRestaurats = true;
			});
		axios.get('/rest/users/me').then(response => (this.isAdmin = response.data?.userRole === 'Administrator'))
	},
	computed: {
		sortedList() {
			if (this.searchList == null) return;
			return this.searchList.sort((a, b) => {
				let modifier = 1;
				if (this.currentSortDir === 'desc') modifier = -1;
				if(this.currentSort!=='location'){
					if (a[this.currentSort] < b[this.currentSort] && b.status) return -1 * modifier
					if (a[this.currentSort] > b[this.currentSort] && a.status) return modifier 
					return 0
				}else{
					if (a[this.currentSort].address.zipCode < b[this.currentSort].address.zipCode) return -1 * modifier
					if (a[this.currentSort].address.zipCode > b[this.currentSort].address.zipCode) return modifier 
					return 0
				}
			})
		},
		searchList() {
			if (this.filteredList == null) return;
			return this.filteredList.filter(restaurant => {
				return restaurant.name.toLowerCase().includes(this.searchName.toLowerCase())
					&& restaurant.location.address.street.toLowerCase().includes(this.searchLocation.toLowerCase())
					&& restaurant.avgRate >= this.searchAvgRate
			})
		},
		filteredList() {
			if (this.restaurants == null) return;
			return this.restaurants.filter(restaurant => {
				return restaurant.restaurantType.toLowerCase().includes(this.currentFilterType.toLowerCase())
					&& restaurant.status.toString().includes(this.currentFilterTypeStatus)
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
		showRestaurant: function (s) {
			router.push('restaurants/' + s)
		},
		addNewPage() {
			this.$router.push('/restaurants/-1/settings');
		}
	}
});
