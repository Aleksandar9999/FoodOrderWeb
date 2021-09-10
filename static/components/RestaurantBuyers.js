Vue.component("restaurant-buyers", {
	data: function () {
		return {
            buyers:[]
		}
	},
	template: `
	<div><custom-header/> 
	<div id="itemslist">
		<h3 id="title">Kupci</h3>
		<table id="userList" border="0" CELLSPACING=0>
			<tr id="headertable">
				<th >Ime</th>
				<th >Prezime</th>
				<th >Korisnicko ime</th>
				
				<th>Uloga</th>
			</tr>
				
			<tr v-for="(p, index) in buyers" >
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
  </div>
`
	,
	mounted() {
		axios
			.get('/rest/restaurants/'+this.$route.params.id+'/buyers')
			.then(response => {this.buyers = response.data;
				console.log(response.data);
			})
			.catch(function (error) {
				alert(error.response.data, "Greska")
			})
	},
	methods: {
	
	}
});
