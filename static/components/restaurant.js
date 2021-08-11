Vue.component("restaurant", {
    data: function () {
        return {
            restaurant: null,
            articles:null,
            comments:null
        }
    },
    template: ` 
	<div class="restaurantInfo">
            <div class="hederInfo">
                <div style="display: inline-block;">
                    <h2>Osnovne informacije</h2>
                    
                    <div style="margin-top: 18px; " >
                        <img width="90px" height="90px" style="overflow : visible;" src="../files/images/pizza.jpg">
                    </div>
                
                    <p>{{restaurant.name}}</p>
                    <p>{{restaurant.restaurantType}}</p>
                    <p>{{restaurant.status}}</p>
                    <p>{{restaurant.location.address.street}} {{restaurant.location.address.number}}</p>
                    <p>{{restaurant.location.address.city}} {{restaurant.location.address.zipCode}}</p>
                    <p>Ocena</p>
                </div>
            </div>

            <div class="hederInfo" style="margin-top: 10px;">
                <table id="userList" border="0" CELLSPACING=0>
                    <tr v-for="(p) in articles" >
                        <td>
                            <img width="50px" height="50px" style="overflow : visible;" src="../files/images/pizza.jpg">
                        </td>    
                        <td>
                            <p>{{p.article.name}}</p>
                            <p style="color:gray;">{{p.article.comment}}</p>
                        </td>
                        <td>
                            <p>{{p.article.price}} RSD</p>
                        </td>
                        <td>
                            <input v-model="p.quantity" type="number"/>
                        </td>
                        <td>
                            <input type="button" @click="addToCart(p)" value="DODAJ"/>
                        </td>
                    </tr>
		        </table>
            </div>
            <div class="hederInfo" style="margin-top: 10px;">
                <table id="userList" border="0" CELLSPACING=0>
                    <tr v-for="(p) in comments" >
                        <td>
                            <p>{{p.comment}}</p>
                            <p>{{p.mark}}</p>
                        </td>
                        <td>
                            <p>{{p.buyerUsername}}</p>
                        </td>
                    </tr>
		        </table>
            </div>
        </div>
`
    ,
    mounted() {
        axios
            .get('rest/restaurants/' + this.$route.params.id)
            .then(response => (this.restaurant = response.data))
        
        axios
            .get('rest/restaurants/' + this.$route.params.id+'/articles')
            .then(response => {
                console.log(response.data)
                this.articles = response.data
            })
        
        axios
            .get('/rest/restaurants/' + this.$route.params.id+'/comments')
            .then(response => {
                this.comments = response.data;
            })
        
    },
    methods: {
        addToCart(p){
            axios.post('/rest/cart/articles',{article:p.article,quantity:p.quantity}).
                then(response=> (console.log("DODAO u Korpu")));
        }
        
    }
});
