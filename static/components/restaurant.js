Vue.component("restaurant", {
	data: function () {
		return {
			restaurant: null,
		}
	},
	template: ` 
	<div class="restaurantInfo">
            <div class="hederInfo">
                <div style="display: inline-block;">
                    <h2>Osnovne informacije</h2>
                    <div style="margin-top: 18px; " >
                        <img width="90px" height="90px" style="overflow : visible;" src="../files/images/pizza.jpg">
                        <input type="button" id="changelogo"  value="Izmeni logo">
                    </div>
                </div>
            </div>
            <div class="hederInfo" style="margin-top: 10px;">
                <form>
                    <table style="border-spacing: 10px; border-collapse: separate;">
                        <tr>
                            <td style=" padding: 10px 30px 10px 0px; ">
                                <p>Naziv</p>
                                <div class="field">
                                    <input type="text" v-model=restaurant.name>
                                </div>
                            </td>
                            <td>
                                <p>Tip</p>
                                <div class="field">
                                    <select style="height: 30px;" v-model=restaurant.restaurantType>
                                        <option value="Italian">Italian</option>
                                        <option value="Chinese">Chinese</option>
                                        <option value="Grill">Grill</option>
                                        <option value="Other">Other</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <h2>Lokacija</h2>
                            </td>
                        </tr>
                        <tr style="padding-top: 10px;">
                            <td style=" padding: 10px 30px 10px 0px; ">
                                <p>Ulica</p>
                                <div class="field">
                                    <input type="text" v-model=restaurant.location.address.street >
                                </div>
                            </td>
                            <td>
                                <p>Broj</p>
                                <div class="field">
                                    <input type="text"  v-model=restaurant.location.address.number>
                                </div>
                            </td>
                        </tr>

                        <tr style="padding-top: 10px;">
                            <td style=" padding: 10px 30px 10px 0px; ">
                                <p>Grad</p>
                                <div class="field">
                                    <input type="text"  v-model=restaurant.location.address.city >
                                </div>
                            </td>
                            <td>
                                <p>ZIP</p>
                                <div class="field">
                                    <input type="text" v-model=restaurant.location.address.zipCode>
                                </div>
                            </td>
                        </tr>
                        <tr style="padding-top: 10px;">
                            <td>
                            </td>
                            <td>
                                <div class="field">
                                    <input type="submit" value="Potvrdi" @click=updateRestaurant>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
`
	,
	mounted() {
        
		axios
			.get('rest/restaurants/'+this.$route.params.id)
			.then(response => (this.restaurant = response.data))
	},
	methods: {
		updateRestaurant:function(){
            axios.put('/rest/restaurants/'+this.$route.params.id,this.restaurant).
            then(response=>(alert("uspjesno")))
        }
	}
});
