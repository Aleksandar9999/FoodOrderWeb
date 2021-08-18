Vue.component("users", {
	data: function () {
		return {
			idAdmin: -1,
			users: null,
			searchName: '',
			searchUsername: '',
			searchSurname: '',
			currentSort: 'name',
			currentSortDir: "asc",
			currentFilterRole:''
		}
	},
	template: ` 
	<div id="itemslist">
		<h3 id="title">Korisnici</h3>
		<table id="searchtabe" border="0" cellspacing=0>
			<tr>
				<td><div class="field"><input v-model="searchName" type="text" placeholder="Ime" name="name"></div></td>
				<td><div class="field"><input v-model="searchSurname" type="text" placeholder="Prezime" name="surname"></div></td>
				<td><div class="field"><input v-model="searchUsername" type="text" placeholder="Korisnicko ime" name="username"></div></td>
				<td>
					<select v-model="currentFilterRole" name="currentFilterRole">
						<option value=""></option>
						<option value="Manager">Manager</option>
						<option value="Buyer">Buyer</option>
						<option value="Administrator">Administrator</option>
						<option value="Deliverer">Deliverer</option>
					</select>
			    </td>
			</tr>
		</table>

		<table id="userList" border="0" CELLSPACING=0>
			<tr id="headertable">
				<th @click="sort('name')">Ime</th>
				<th @click="sort('surname')">Prezime</th>
				<th @click="sort('username')">Korisnicko ime</th>
				
				<th>Uloga</th>
			</tr>
				
			<tr v-for="(p) in sortedList" >
				<td>
						<p>{{p.name}}</p>
				</td>
				<td>
					<p>{{p.surname}}</p> 
				</td>
				<td>
					<p id="usernamep">@{{p.username}}</p>
				</td>
				<td><p id="userrolep">{{p.userRole}}</p></td>
			</tr>
		</table>
</div>
  
`
	,
	mounted() {
		axios
			.get('/rest/users')
			.then(response => (this.users = response.data)).catch((error)=>{alert(error.response.data,"greska")})
	},
	computed: {
		sortedList() {
			if (this.searchList == null) return;
			return this.searchList.sort((a, b) => {
				let modifier = 1;
				if (this.currentSortDir === 'desc') modifier = -1;
				if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier
				if (a[this.currentSort] > b[this.currentSort]) return modifier
				return 0
			})
			
		},
		searchList() {
			if (this.filteredList == null) return;
			return this.filteredList.filter(user => {
				return (user.name.toLowerCase().includes(this.searchName.toLowerCase())
					&& user.surname.toLowerCase().includes(this.searchSurname.toLowerCase())
					&& user.username.toLowerCase().includes(this.searchUsername.toLowerCase()))
			})
			 
		},
		filteredList(){
			if (this.users == null) return;
			return this.users.filter(user => {
				console.log("Ss"+this.currentFilterRole.toLowerCase())
				return (user.userRole.toLowerCase().includes(this.currentFilterRole.toLowerCase()))
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
		filterRole:function(s){
			this.currentFilterRole = s
		}
	}
});
