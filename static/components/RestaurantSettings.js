Vue.component("restaurant-settings", {
    data: function () {
        return {
            restaurant: {},
            articles: null,
            managers: null,
            selectedManager: null,
            myModel: false,
            article: {
                name: '',
                price: '',
                articleType: '',
                amount: '',
                comment: '',
                imageUrl: '',
            },
            actionButton: "Dodaj",
            dynamicTitle: "Dodavanje artikla",
            articleOperation: '',
        }
    },
    template: ` 
	<div class="restaurantInfo">
            <div class="hederInfo">
                <div style="display: inline-block;">
                    <h2>Osnovne informacije</h2>
                    <div style="margin-top: 18px; " >
                        <img width="90px" height="90px" style="overflow : visible;" src="../files/images/pizza.jpg">
                        <input type="button" id="changelogo" value="Izmeni logo">
                    </div>
                    <div style="margin-top: 18px; " >
                        <p>Menadzer</p>
                        <select style="height: 30px;" v-model="selectedManager">
                            <option v-for="manager in managers">{{manager.username}}</option>  
                        </select>
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
                                    <input type="submit" value="Potvrdi" @click="updateRestaurant">
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="hederInfo" style="margin-top: 10px;">
            <h2>Artikli</h2>
            <div class=field>
                <input type="button" value="Dodaj artikal" @click=openModel>
            </div>
            <table  border="0" CELLSPACING=0>
                <tr v-for="(p) in articles" @click=updateArticle(p) >
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
        <restaurant-comments type='settings'></restaurant-comments>
        <div v-if="myModel">
		<transition name="model">
		 <div class="modal-mask">
		  <div class="modal-wrapper">
		   <div class="modal-dialog" style="color: black;" >
			<div class="modal-content" style="background-color: white; width: 80%; margin: 0 auto;">
				<div class="modal-header">
					<button type="button" class="close" @click="myModel=false"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" >{{dynamicTitle}}</h4>
				</div>
				
				<div class="modal-body">
					<div class="form-group">
					 <label>Naziv</label> 
					 <input type="text" class="form-control" v-model="article.name"/>
					</div>
					<div class="form-group">
					 <label>Cena</label>
					 <input type="text" class="form-control" v-model="article.price" />
					</div>
                    <div class="form-group">
					 <label>Tip</label>
                        <select v-model="article.articleType"  style="height: 30px;">
                            <option value=""></option>
                            <option value="Food">Hrana</option>
                            <option value="Drink">Piće</option>
                        </select>
					</div>
                    <div class="form-group">
					 <label>Slika</label>
					 <input type="text" class="form-control" v-model="article.imageUrl"/>
					</div>
                    <div class="form-group">
					 <label>Opis</label>
					 <input type="text" class="form-control" v-model="article.comment"/>
					</div><div class="form-group">
                    <label>Kolicina</label>
                    <input type="text" class="form-control" v-model="article.amount"/>
                   </div>
					<br />
					<div>
					 <input type="hidden"  />
					 <input type="button" class="btn btn-success btn-xs" v-model="actionButton" @click="submitData" />
					</div>
				   </div>
			</div>
		   </div>
		  </div>
		 </div>
		</transition>
	   </div>

        </div>
  </div>
`
    ,
    mounted() {
        axios
            .get('rest/restaurants/' + this.$route.params.id+'/settings')
            .then(response => { this.restaurant = response.data; console.log(this.restaurant) })
            .catch(function (error) {
                alert(error.response.data, "Greska")
            })

        axios
            .get('rest/restaurants/' + this.$route.params.id + '/articles')
            .then(response => {
                console.log(response)
                this.articles = response.data
            })

        if (this.$route.params.id === '-1')
            axios.get('rest/users/managers?restaurantId=-1')
                .then(response => (this.managers = response.data))

    },
    methods: {
        updateRestaurant() {
            if (this.$route.params.id === "-1") {
                axios.post('/rest/restaurants', this.restaurant).
                    then(response => {
                        if (!this.selectedManager) {
                            this.$router.push('/restaurants/' + response.data.id + '/manager');
                        }
                        else {
                            axios.put('/rest/restaurants/' + response.data.id + '/managers/' + this.selectedManager)
                            .then(response => { this.$router.push('/restaurants'); })
                        }
                    })
            } else {
                axios.put('/rest/restaurants/' + this.$route.params.id, this.restaurant).
                    then(response => (alert("uspjesno azuriran")))
            }
        },
        submitData() {
            if (this.articleOperation === 'Create') {
                axios
                    .post('rest/restaurants/' + this.$route.params.id + '/articles', this.article)
                    .then(response => {
                        this.restaurant = response.data;
                        axios
                            .get('rest/restaurants/' + this.$route.params.id + '/articles')
                            .then(response => {
                                this.articles = response.data;
                                console.log("getovvao")
                            })
                        alert("Uspijesno ste dodali artikal");
                    })
                    .catch(function (error) {
                        alert(error.response.data, "Greska")
                    })
            }
            else {
                axios
                    .put('rest/restaurants/' + this.$route.params.id + '/articles/' + this.article.id, this.article)
                    .then(response => {
                        this.restaurant = response.data;
                        alert("Success.")
                    })
                    .catch(function (error) {
                        alert(error.response.data, "Greska")
                    })
            }
            

        },
        openModel() {
            this.article={
                name: '',
                price: '',
                articleType: '',
                amount: '',
                comment: '',
                imageUrl: '',
            },
            this.articleOperation = 'Create'
            this.actionButton = "Dodaj"
            this.dynamicTitle = "Dodavanje artikla"
            this.myModel = true
        },
        updateArticle(p) {
            this.articleOperation = 'Update'
            this.article = JSON.parse(JSON.stringify(p));
            console.log(JSON.parse(JSON.stringify(p)))
            this.actionButton = "Izmijeni"
            this.dynamicTitle = "Izmijena artikla"
            this.myModel = true
        }

    }
});
