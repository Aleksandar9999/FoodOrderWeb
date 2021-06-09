Vue.component("users", {
	data: function () {
		return {
			idAdmin: -1,
			users: null,
			searchName: '',
			searchUsername: '',
			searchSurname: '',
			currentSort:'name',
			currentSortDir:"asc"
		}
	},
	template: ` 
	<div id="loginForm">
		<h3 id="title">Korisnici</h3>
		<table id="searchtabe" border="0" cellspacing=0>
			<tr>
				<td><div class="field"><input v-model="searchName" type="text" placeholder="Ime" name="name"></div></td>
				<td><div class="field"><input v-model="searchSurname" type="text" placeholder="Prezime" name="surname"></div></td>
				<td><div class="field"><input v-model="searchUsername" type="text" placeholder="Korisnicko ime" name="username"></div></td>
			</tr>
		</table>

		<table id="userList" border="0" CELLSPACING=0>
			<tr id="headertable">
				<th @click="sort('name')">Ime</th>
				<th @click="sort('surname')">Prezime</th>
				<th @click="sort('username')">Korisnicko ime</th>
				<th></th><th></th>
				<th colspan="3">Uloga</th>
			</tr>
				
			<tr v-for="(p, index) in sortedList" >
				<td><div>
						<p>{{p.name + p.surname}}</p> 
						<p id="usernamep">@{{p.username}}</p>
					</div>
				</td>
				<td></td><td></td>
				<td><p id="userrolep" colspan="3">{{p.userRole}}</p></td>
			</tr>
		</table>
</div>
  
`
	,
	mounted() {
		axios
			.get('rest/user')
			.then(response => (this.users = response.data))
	},
	computed: {
		filteredList() {
			if (this.users == null) return;
			if (this.searchName == "" && this.searchSurname == "" && this.searchUsername == "")
				return this.users
			return this.users.filter(user => {
				return user.name.toLowerCase().includes(this.searchName.toLowerCase()) 
						&& user.surname.toLowerCase().includes(this.searchSurname.toLowerCase()) 
						&& user.username.toLowerCase().includes(this.searchUsername.toLowerCase())
					})
		},
		sortedList() {
			if(this.filteredList==null) return;
			return this.filteredList.sort((a,b)=>{
				let modifier=1;
				if(this.currentSortDir==='desc') modifier=-1;
				if(a[this.currentSort]< b[this.currentSort]) return -1*modifier
				if(a[this.currentSort]> b[this.currentSort]) return modifier
				return 0
			})
		}, 
		mainList(){
			return this.mainList;
		}


	},

	methods: {
		sort: function (s) {
			if (s === this.currentSort) {
				this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
			}
			this.currentSort = s
		}
	}
});
