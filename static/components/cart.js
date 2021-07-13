Vue.component("cart", {
	data: function () {
		    return {
		      sc: null,
		      total: 0
		    }
	},
	template: ` 
<div>
		Proizvodi u korpi:
		<table border="1">
		<tr bgcolor="lightgrey">
			<th>Naziv</th><th>Jedinicna cena</th><th>Komada</th><th>Ukupna cena</th></tr>
			<tr v-for="i in sc.articles">
			<td> {{i.article.name}}</td>
			<td> {{i.article.price}}</td>
			<td> <input v-model="i.quantity" type="number"/> </td>
			<td> {{i.quantity * i.article.price}} </td>
			</tr>
		</table>
		<br /> 
		<button v-on:click="clearSc" >Obriši korpu</button>
        <button v-on:click="createOrder" >Kreiraj narudzbinu</button>
		
		<p>
		Ukupno: {{totalPriceCalculator}} dinara.
		</p>
	<p>
		<a href="#/">Proizvodi</a>
	</p>
	
</div>		  
`
	, 
	methods : {
		init : function() {
			this.sc = {};
			this.total = 0.0;
		}, 
		clearSc : function () {
			if (confirm('Da li ste sigurni?') == true) {
				axios
		          .post('rest/proizvodi/clearSc')
		          .then(response => (this.init()))
			}
		},
        createOrder:function(){
			console.log(this.sc)
            axios.post('/rest/orders',this.sc).then(response=>alert("Uspijesno kreirana narudzba"))
        }
	},
    computed:{
        totalPriceCalculator:function(){
            let total=0;
            this.sc.articles.forEach(element => {
                total+=element.article.price*element.quantity;
            });
			this.sc.total=total;
            return total;
        }
    },
	mounted () {
        axios
          .get('rest/cart')
          .then(response => {
        	  this.sc = response.data;
              console.log(this.sc)
          });
    }
});