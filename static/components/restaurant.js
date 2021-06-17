Vue.component("restaurant", {
    data: function () {
        return {
            restaurant: null,
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
                    <tr v-for="(p) in restaurant.articles" >
                        <td>
                            <img width="50px" height="50px" style="overflow : visible;" src="../files/images/pizza.jpg">
                        </td>    
                        <td>
                            <p>{{p.name}}</p>
                            <p style="color:gray;">{{p.comment}}</p>
                        </td>
                        <td>
                            <p>{{p.price}} RSD</p>
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
                            <p>{{p.buyer.username}}</p>
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
            .get('rest/restaurants/' + this.$route.params.id+'/comments')
            .then(response => (this.comments = response.data))
        
    },
    methods: {
        
    }
});
