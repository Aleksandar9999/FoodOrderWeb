Vue.component("orders", {
	data: function () {
		    return {
                orders:null,
            }
	},
	template: ` 
<div>
	<table border="1">
		<tr bgcolor="lightgrey">
			<th>Status</th><th>Vrijeme kreiranja</th><th>Ukupna cena</th></tr>
			<tr v-for="i in sc.articles">
			<td> {{i.article.name}}</td>
			<td> {{i.article.price}}</td>
			<td> <input v-model="i.quantity" type="number"/> </td>
			<td> {{i.quantity * i.article.price}} </td>
			</tr>
		</table>
		<br /> 
</div>		  
`
	, 
	methods : {
		init : function() {
			this.orders = {};
		}
	},
	mounted () {
        axios
          .get('/rest/restaurants/'+this.$route.params.id+'/orders')
          .then(response => {
        	  this.orders = response.data;
              console.log(this.orders)
          });
    }
});