Vue.component("restaurant-settings", {
    data: function () {
        return {
            url: '',
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
    <div><custom-header></custom-header>
	<div class="restaurantInfo">
            <div class="hederInfo">
                <div style="display: inline-block;">
                    <h2>Osnovne informacije</h2>
                    <div style="margin-top: 18px; " >
                        <img width="90px" height="90px" style="overflow : visible;"  :src="'../files/images/'+restaurant.logoUrl">
                        <input type="file" id="changelogo" name="logo" accept="image/png, image/jpeg" value="Izaberi logo" @change="setLogo">
                    </div>
                    <div style="margin-top: 18px; "  v-if="restaurant.id === '' ">
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
                                    <button @click="updateRestaurant"> Potvrdi</button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="hederInfo" style="margin-top: 10px;" v-if="restaurant.id !== ''">
            <h2>Artikli</h2>
            <div class=field>
                <input type="button" value="Dodaj artikal" @click='openModel'>
            </div>
            <table  border="0" CELLSPACING=0>
                <tr v-for="(p) in articles" @click=updateArticle(p) >
                    <td>
                        <img width="50px" height="50px" style="overflow : visible;" :src="'../files/images/'+p.imageUrl">
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
        <restaurant-map :location=restaurant.location></restaurant-map>
        <restaurant-comments  type='settings'  v-if="restaurant.id !== ''"></restaurant-comments>
        

        <add-new-article :show='myModel' :actionButton='actionButton' :dynamicTitle='dynamicTitle' :articleOperation='articleOperation' :article='article'></add-new-article>

        </div>
  </div>
  </div>
`
    ,

    mounted() {
        axios
            .get('rest/restaurants/' + this.$route.params.id + '/settings')
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
        setLogo(event) {
            const formData = new FormData();
            formData.append("logo", event.target.files[0]);
            formData.append("id", 7878);
            axios.post("/rest/upload", formData)
                .then(function (result) {
                    console.log(result);
                }, function (error) {
                    console.log(error);
                });
            this.restaurant.logoUrl = event.target.files[0].name;
        },
        updateRestaurant() {
            if (this.$route.params.id === "-1") {
                if (!this.restaurant.name) {
                    alert("Unesite ime restorana");
                    return;
                }
                axios.post('/rest/restaurants', this.restaurant).
                    then(response => {
                        if (!this.selectedManager) {
                            this.$router.push('/restaurants/' + response.data.id + '/manager');
                        }
                        else {
                            axios.put('/rest/restaurants/' + response.data.id + '/managers/' + this.selectedManager)
                                .then(response => (this.$router.push('/restaurants')))
                        }

                    });

            } else {
                axios.put('/rest/restaurants/' + this.$route.params.id, this.restaurant).
                    then(response => (alert("uspjesno azuriran")))
            }
        },
        
        openModel() {
            this.article = {
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
