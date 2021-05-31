Vue.component("administrator", {
	data: function () {
		    return {
		      idAdmin : -1,
		      users:null
		    }
	},
	template: ` 
	<div>
	<h3>Korisnici</h3>
	<table border="1">
		<tr bgcolor="lightgrey">
			<th>Korisnicko ime</th>
			<th>Ime</th>
		</tr>
			
		<tr v-for="(p, index) in users" >
			<td>{{p.username}}</td>
			<td>{{p.name}}</td>
		</tr>
	</table>
</div>
  
`
	, 
	mounted () {
        axios
          .get('rest/user')
          .then(response => (this.users = response.data))
    },
	methods : {
		
		}
});